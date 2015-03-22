package com.roadtracking.app.rclient;


import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.persistence.dao.impl.Dao;
import com.roadtracking.persistence.entity.AuthSecret;

public class Initializer {

    public void init() {
        IDao dao = new Dao();

        AuthSecret authSecret = new AuthSecret();
        authSecret.setName("GOOGLE");
        authSecret.setClientId("");
        authSecret.setClientSecret("");
        dao.put(authSecret);
    }
}
