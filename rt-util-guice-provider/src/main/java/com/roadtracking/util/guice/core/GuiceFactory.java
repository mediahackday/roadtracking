package com.roadtracking.util.guice.core;

import com.google.inject.*;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class GuiceFactory {

	private static Injector injector;

	private static StageModule module;

	private static Logger logger = LoggerFactory.getLogger(GuiceFactory.class);

    public static void init(final AbstractModule module) {
        injector = Guice.createInjector(module);
    }

    public static void init(final StageModule stageModule, String[] args) {
        Stage stage = Stage.DEVELOPMENT;
        if (args.length == 1) {
            if ("prod".equals(args[0])) {
                stage = Stage.PRODUCTION;
            }
        }
        if (!Stage.PRODUCTION.equals(stage)) {
            logger.warn("Development environment!");
		}
		injector = Guice.createInjector(stage, stageModule);
		module = stageModule;
	}

	@SuppressWarnings("UnusedDeclaration")
	public static Injector getInjector() {
		return injector;
	}

	public static <T> T getInstance(Class<T> type) {
		return injector.getInstance(type);
	}

	public static <T> T getInstance(Class<T> type, Class<? extends Annotation> option) {
		final Key<T> key = Key.get(type, option);
		return injector.getInstance(key);
	}

	public static <T> T getInstance(final Class<T> type, final String name) {
		final Key<T> key = Key.get(type, Names.named(name));
		return injector.getInstance(key);
	}

	public static <T, G> T getGenericInstance(Class<T> type, Class<G> genericType) {
		return injector.getInstance(Key.get(module.getTypeLiteral(type, genericType)));
	}
}