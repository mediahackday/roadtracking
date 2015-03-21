package com.roadtracking.web.guice;

import com.google.apphosting.utils.remoteapi.RemoteApiServlet;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.googlecode.objectify.ObjectifyFilter;
import com.roadtracking.util.guice.provider.logger.DefaultLoggerProvider;
import com.roadtracking.util.guice.provider.logger.Slf4JTypeListener;
import org.slf4j.Logger;

import javax.inject.Singleton;

/**
 * Module which binds the handlers and configurations.
 */
public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        //@formatter:off
		bindListener(Matchers.any(), new Slf4JTypeListener());
		bind(Logger.class).toProvider(DefaultLoggerProvider.class);


        bind(RemoteApiServlet.class).in(Singleton.class);
		bind(ObjectifyFilter.class).in(Singleton.class);
		//@formatter:on
    }
}
