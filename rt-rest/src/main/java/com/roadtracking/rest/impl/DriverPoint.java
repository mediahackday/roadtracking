package com.roadtracking.rest.impl;


import com.roadtracking.mapper.api.IDriverMapper;
import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.persistence.entity.Driver;
import com.roadtracking.rest.api.IDriverPoint;
import com.roadtracking.rest.dto.DriverDto;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;

public class DriverPoint implements IDriverPoint {

    @Inject
    private Logger logger;

    @Inject
    private IDriverMapper mapper;

    @Inject
    private IDao dao;

    @Override
    public DriverDto put(DriverDto driverDto, HttpServletRequest request) {
        logger.info("user rest point");
        Driver driver = mapper.map(driverDto);
        String customerName = format("%s:GOOGLE", request.getAttribute("EMAIL").toString());
        driver.setName(customerName);
        dao.put(driver);
        return mapper.map(driver);
    }

    @Override
    public DriverDto get(HttpServletRequest request) {
        String customerName = format("%s:GOOGLE", request.getAttribute("EMAIL").toString());
        Driver driver = dao.getEntity(Driver.class, customerName);
        return mapper.map(driver);
    }
}
