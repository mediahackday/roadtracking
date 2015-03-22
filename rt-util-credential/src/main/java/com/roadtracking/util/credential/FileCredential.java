package com.roadtracking.util.credential;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static java.lang.String.format;

public class FileCredential implements ICredential {

	private static final String USER_HOME_DIR = System.getProperty("user.home");

	private static final String APP_NAME = "rt-app";

	private static final String FILE_NAME = format("%s/.credentials/%s/credential", USER_HOME_DIR, APP_NAME);

	private Properties properties = new Properties();

	private Coder coder = new Coder();

	public FileCredential() {
		try {
			properties.load(new FileInputStream(FILE_NAME));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String get(String key) {
		try {
			return coder.decrypt(properties.getProperty(key));
		} catch (GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void set(String key, String value) {
		try {
			properties.setProperty(key, coder.encrypt(value));
			properties.store(new FileOutputStream(FILE_NAME), "");
		} catch (GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
