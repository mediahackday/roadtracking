package com.roadtracking.util.guice.provider.logger;

import com.google.inject.MembersInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Logger injector.
 */
public final class Slf4JMembersInjector<T> implements MembersInjector<T> {

	/**
	 * Logger field for injection.
	 */
	private final Field field;

	/**
	 * Logger instance.
	 */
	private final Logger logger;

	/**
	 * Constructor of injector.
	 *
	 * @param field Logger field for injection
	 */
	Slf4JMembersInjector(final Field field) {
		this.field = field;
		this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
		field.setAccessible(true);
	}

	@Override
	public void injectMembers(final T t) {
		try {
			this.field.set(t, this.logger);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
