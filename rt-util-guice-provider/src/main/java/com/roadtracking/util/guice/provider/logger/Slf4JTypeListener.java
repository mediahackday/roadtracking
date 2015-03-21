package com.roadtracking.util.guice.provider.logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.lang.reflect.Field;

/**
 * Inject listener for correct slf4j Logger injection.
 */
public final class Slf4JTypeListener implements TypeListener {

	@Override
	public <T> void hear(final TypeLiteral<T> typeLiteral, final TypeEncounter<T> typeEncounter) {
		for (final Field field : typeLiteral.getRawType().getDeclaredFields()) {
			if (field.getType() == Logger.class && field.isAnnotationPresent(Inject.class)) {
				typeEncounter.register(new Slf4JMembersInjector<T>(field));
			}
		}
	}
}
