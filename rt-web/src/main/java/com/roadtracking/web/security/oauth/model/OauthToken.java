package com.roadtracking.web.security.oauth.model;

public class OauthToken {

	/**
	 * OAuth access token.
	 */
	private String accessToken;

	/**
	 * Composition of authentication scheme and access token, e.g "Bearer ya29.HAA5JUlKBPlTxyAAAABne5nbPd8xrHBcxvy8wYbyt0rik0SzSmY8QkFi0tQE4w".
	 */
	private String authHeader;

	/**
	 * Token expired date.
	 */
	private long expired;

	public OauthToken() {
	}

	public OauthToken(String accessToken, String authHeader, long expired) {
		this.accessToken = accessToken;
		this.authHeader = authHeader;
		this.expired = expired;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAuthHeader() {
		return authHeader;
	}

	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}

	public long getExpired() {
		return expired;
	}

	public void setExpired(long expired) {
		this.expired = expired;
	}
}
