package com.roadtracking.web.security;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;
import com.roadtracking.web.security.oauth.manager.ISecurityManager;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Singleton
public class AuthFilter implements Filter {

	private final UserService userService = UserServiceFactory.getUserService();

    List<String> whiteList = Arrays.asList( );

    @Inject
    private org.slf4j.Logger logger;

    @Inject
    private ISecurityManager securityManager;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        boolean allowed = false;
        logger.info("Filter");
        String url = null;
        try {
            if (request instanceof HttpServletRequest) {
                url = ((HttpServletRequest) request).getRequestURI();
            } else {
                logger.warn("servlet: {}", request.getClass().getName());
            }
        } catch (final Exception e) {
            logger.error("", e);
        }

        logger.info("Filter: {}", url);
        if (authRequired(url)) {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
                if (authHeader == null) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
				}
				logger.info("auth header: {}", authHeader);
				final String userEmail = securityManager.getUserEmail(authHeader);
                logger.info("mail: " + userEmail);
                if (userEmail == null) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                request.setAttribute("EMAIL", userEmail);
                chain.doFilter(request, response);
                return;

//				if (!securityManager.isUserAllowed(userEmail)) {
//					logger.warn("user {} is not authorized", userEmail);
//					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					return;
//				} else {
//					chain.doFilter(request, response);
//					return;
//				}
			}
		} else {
			chain.doFilter(request, response);
			return;
		}
	}

	private boolean authRequired(String url) {
		String[] authFree = {
				"/cron/",
				"/_ah/spi/",
				"/_ah/mail/",
                "/_ah/admin/",
				"/auth",
				"/test",
                "/remote_api"
		};
		for (String pattern : authFree) {
			if (url.startsWith(pattern)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkGoogleUser() {
		boolean allowed = false;
		final User user = userService.getCurrentUser();
		if (user != null && whiteList.contains(user.getEmail())) {
			allowed = true;
		} else {
			logger.info("user is null, not allowed");
		}
		return allowed;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

}