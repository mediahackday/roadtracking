package com.roadtracking.util.credential;

/**
 * Base to get saved password from user directory file.
 */
public interface ICredential {

	String get(String key);

	void set(String key, String value);
}
