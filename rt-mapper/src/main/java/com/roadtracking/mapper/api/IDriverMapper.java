package com.roadtracking.mapper.api;

import com.roadtracking.persistence.entity.Driver;
import com.roadtracking.rest.dto.DriverDto;

public interface IDriverMapper {

    Driver map(DriverDto driverDto);

    DriverDto map(Driver driver);
}
