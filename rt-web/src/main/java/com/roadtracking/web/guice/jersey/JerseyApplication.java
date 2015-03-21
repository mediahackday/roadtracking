package com.roadtracking.web.guice.jersey;

import com.roadtracking.rest.impl.UserPoint;
import com.roadtracking.web.guice.GuiceServletConfig;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.gae.GaeFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class JerseyApplication extends ResourceConfig {

	@Inject
	public JerseyApplication(ServiceLocator serviceLocator) {
        register(JacksonFeature.class);
        register(GaeFeature.class);

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GuiceServletConfig.injector);

        register(UserPoint.class);
	}
}
