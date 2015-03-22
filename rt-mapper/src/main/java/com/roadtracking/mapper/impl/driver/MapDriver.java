package com.roadtracking.mapper.impl.driver;

import com.roadtracking.persistence.entity.Driver;
import com.roadtracking.rest.dto.DriverDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class MapDriver extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(Driver.class, DriverDto.class)
                .byDefault()
                .register();
    }
}