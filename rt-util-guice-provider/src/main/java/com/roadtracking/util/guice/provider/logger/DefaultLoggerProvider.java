package com.roadtracking.util.guice.provider.logger;

import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLoggerProvider implements Provider<Logger> {

	@Override
	public Logger get() {
		return LoggerFactory.getLogger(DefaultLoggerProvider.class);
	}
}