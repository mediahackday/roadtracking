package com.roadtracking.web.security.oauth.manager;

import com.roadtracking.web.security.oauth.model.OauthToken;

public interface ISecurityManager {

	/**
	 * Compose initial oauth url.
	 * @param state client state, actually angular location, e.g /channels.
	 * @param loginHint suggested login user name
	 * @return oauth url
	 */
	String getInitialOauthUrl(String state, String loginHint);

	/**
	 * Get oauth token, aka access token, from oauth provider.
	 *
	 * @param oauthCode the code was received from the user, e.g 4/U9dYec2uSJ9J3hFIWM2olhNZVgDv.cvEIro83sFEcOl05ti8ZT3ZVxkgxjAI
	 * @return oauth token structure
	 */
	OauthToken getOauthToken(String oauthCode);

	/**
	 * Get user email from cache or from oauth api provider service response, e.g from Google+ API.
	 *
	 * @param authHeader authentication header, e.g "Bearer ya29.HAA5JUlKBPlTxyAAAABne5nbPd8xrHBcxvy8wYbyt0rik0SzSmY8QkFi0tQE4w".
	 * @return user email
	 */
	String getUserEmail(String authHeader);

	/**
	 * Checks if user is allowed.
	 *
	 * @param userEmail user email
	 * @return checking result
	 */
	boolean isUserAllowed(final String userEmail);
}