package com.roadtracking.web.guice;

import com.google.apphosting.utils.remoteapi.RemoteApiServlet;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.roadtracking.web.guice.jersey.JerseyServlet;
import com.roadtracking.web.security.AuthServlet;


import java.util.HashMap;
import java.util.Map;

public class DispatchServletModule extends ServletModule {

    @Override
    public void configureServlets() {
        Map<String, String> params = new HashMap<>();
        params.put("javax.ws.rs.Application", "com.roadtracking.web.guice.jersey.JerseyApplication");
        serve("/rest/*").with(JerseyServlet.class, params);

        serve("/remote_api").with(RemoteApiServlet.class);
        serve(AuthServlet.URL).with(AuthServlet.class);

        filter("/*").through(ObjectifyFilter.class);
    }
}
