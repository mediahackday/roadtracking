package com.roadtracking.web.guice;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;

public class GuiceFactory {

	public static <T> T getInstance(TypeLiteral typeLiteral) {
		final Key<T> key = Key.get(typeLiteral);
		return GuiceServletConfig.injector.getInstance(key);
	}
}