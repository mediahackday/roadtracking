package com.roadtracking.persistence.entity;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

@Entity
public class GeoPoint {

    @Id
    private Long id;

    private Ref<Driver> driver;

    private Date date;

    private GeoPt geo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ref<Driver> getDriver() {
        return driver;
    }

    public void setDriver(Ref<Driver> driver) {
        this.driver = driver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GeoPt getGeo() {
        return geo;
    }

    public void setGeo(GeoPt geo) {
        this.geo = geo;
    }
}
