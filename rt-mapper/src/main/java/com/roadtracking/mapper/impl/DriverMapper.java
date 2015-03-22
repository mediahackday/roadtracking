package com.roadtracking.mapper.impl;

import com.roadtracking.mapper.api.IDriverMapper;
import com.roadtracking.mapper.impl.driver.MapDriver;
import com.roadtracking.persistence.entity.Driver;
import com.roadtracking.rest.dto.DriverDto;

import javax.inject.Inject;

public class DriverMapper implements IDriverMapper {

    @Inject
    private MapDriver mapDriver;

    @Override
    public Driver map(DriverDto driverDto) {
        return mapDriver.map(driverDto, Driver.class);
    }

    @Override
    public DriverDto map(Driver driver) {
        return mapDriver.map(driver, DriverDto.class);
    }
}
