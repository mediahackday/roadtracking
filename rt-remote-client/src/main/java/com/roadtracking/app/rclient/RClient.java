package com.roadtracking.app.rclient;

import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;
import com.roadtracking.app.rclient.guice.GuiceModule;
import com.roadtracking.util.credential.FileCredential;
import com.roadtracking.util.credential.ICredential;
import com.roadtracking.util.guice.core.GuiceFactory;

import java.io.IOException;

import static com.roadtracking.util.guice.core.GuiceFactory.getInstance;

public class RClient {

    public static void main(String[] args) {
        GuiceFactory.init(new GuiceModule(), args);
        final RClient centralUnit = getInstance(RClient.class);
        centralUnit.run();
    }

    private void run() {
        ICredential credential = new FileCredential();
        RemoteApiOptions options = new RemoteApiOptions().server("roadtracking-pro.appspot.com", 443)
                .credentials("schmidt.immonet@gmail.com", credential.get("schmidt.immonet@gmail.com"));

        RemoteApiInstaller installer = new RemoteApiInstaller();
        try {
            installer.install(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            new Initializer().init();
        } finally {
            installer.uninstall();
        }
    }

}
