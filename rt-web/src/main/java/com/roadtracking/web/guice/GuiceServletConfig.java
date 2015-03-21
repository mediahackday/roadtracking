package com.roadtracking.web.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

	public static Injector injector;

    @Override
    protected Injector getInjector() {
        GuiceServletConfig.injector = Guice.createInjector(new ServerModule(), new DispatchServletModule());
        return GuiceServletConfig.injector;
    }
}
