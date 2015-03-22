package com.roadtracking.rest.impl;


import com.google.appengine.api.datastore.GeoPt;
import com.roadtracking.mapper.api.IDriverMapper;
import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.persistence.entity.Driver;
import com.roadtracking.persistence.entity.GeoPoint;
import com.roadtracking.rest.api.IGeoRestPoint;
import com.roadtracking.rest.dto.DriverDto;
import com.roadtracking.rest.dto.GeoPointDto;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import static java.lang.String.format;

public class GeoRestPoint implements IGeoRestPoint {

    @Inject
    private Logger logger;

    @Inject
    private IDao dao;

    @Override
    public void put(GeoPointDto geoPointDto, HttpServletRequest request) {
        String customerName = format("%s:GOOGLE", request.getAttribute("EMAIL").toString());
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setDriver(dao.createRef(Driver.class, customerName));
        geoPoint.setDate(geoPointDto.getDate());
        geoPoint.setGeo(new GeoPt(geoPointDto.getLatitude(), geoPointDto.getLongitude()));
        dao.put(geoPoint);
    }
}
