package com.roadtracking.web.security.oauth.manager;


import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.roadtracking.web.security.oauth.model.OauthToken;
import com.roadtracking.web.security.oauth.model.Profile;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;


public class SecurityManager implements ISecurityManager {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    private static String OAUTH_INITIAL_URL =
            "https://accounts.google.com/o/oauth2/auth?client_id=%s&response_type=code&scope=%s&redirect_uri=%s&state=%s";

    private static String OAUTH_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";

    private static String USER_SERVICE_URL = "https://www.googleapis.com/plus/v1/people/me?fields=emails";

    private static String OAUTH_SCOPE = "email";

    private static String REDIRECT_URL = "http://roadtracking-pro.appspot.com/auth";

    private static String CLIENT_ID     = "821276603152-scfllveg1j7ckd81jcbd3o45ajurfotq.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "gJi9oWWwPwbzsqB4UqBsp91S";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new GsonFactory();

    private List<String> allowedUsers =
            Arrays.asList(
                    "");

    /**
     * Cache authentication token -> user mail.
     */
    private LoadingCache<String, String> auths = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return getUserEmailViaApi(key);
                }
            });

    @Inject
    private Logger logger;


    @Override
    public String getInitialOauthUrl(String state, String loginHint) {
        return format(OAUTH_INITIAL_URL, CLIENT_ID, OAUTH_SCOPE, REDIRECT_URL, state, loginHint);
    }

    @Override
    public OauthToken getOauthToken(String oauthCode) {
        OauthToken oauthToken = null;
        logger.info("Sending of oauth access token request ...");
        try {
            AuthorizationCodeFlow codeFlow = new AuthorizationCodeFlow.Builder(
                    BearerToken.authorizationHeaderAccessMethod(),
                    HTTP_TRANSPORT,
                    JSON_FACTORY,
                    new GenericUrl(OAUTH_TOKEN_URL),
                    new ClientParametersAuthentication(CLIENT_ID, CLIENT_SECRET),
                    CLIENT_ID,
                    OAUTH_INITIAL_URL
            ).build();
            TokenResponse tokenResponse = codeFlow.newTokenRequest(oauthCode)
                    .setRedirectUri(REDIRECT_URL).execute();
            oauthToken = new OauthToken(tokenResponse.getAccessToken(),
                    format("%s %s", AUTHENTICATION_SCHEME, tokenResponse.getAccessToken()),
                    new Date().getTime() + tokenResponse.getExpiresInSeconds() * 1000);
        } catch (Exception e) {
            logger.error("", e);
        }
        return oauthToken;
    }

    @Override
    public String getUserEmail(String authHeader) {
        String userMail = null;
        if (authHeader != null) {
            try {
                userMail = auths.getUnchecked(authHeader);
            } catch (CacheLoader.InvalidCacheLoadException e) {
                logger.error("Auth header {} could not be authorized", authHeader);
            }
        }
        return userMail;
    }

    private String getUserEmailViaApi(String authHeader) {
        String userMail = null;
        try {
            logger.info("Sending of user api request ...");

            Client client = ClientBuilder.newBuilder()
                    .register(JacksonFeature.class).build();
            WebTarget target = client.target(USER_SERVICE_URL);
            Response response = target.request().accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, authHeader).get();
            if (response.getStatus() == 200) {
                //				logger.info(response.readEntity(String.class));
                Profile value = response.readEntity(Profile.class);
                userMail = value.getEmails().get(0).getValue();
            } else {
                logger.warn("User info retrieving error: \n" + response.readEntity(String.class));
            }
            response.close();
        } catch (Exception e) {
            logger.error("", e);
        }
        return userMail;
    }

    @Override
    public boolean isUserAllowed(String username) {
        return true;//allowedUsers.contains(username);
    }
}
