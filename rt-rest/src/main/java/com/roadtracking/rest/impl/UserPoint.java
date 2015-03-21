package com.roadtracking.rest.impl;


import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.rest.api.IUserPoint;
import com.roadtracking.rest.dto.UserDto;
import org.slf4j.Logger;

import javax.inject.Inject;

public class UserPoint implements IUserPoint {

    @Inject
    private Logger logger;

//    @Inject
//    private IDao dao;

    @Override
    public UserDto put(UserDto userDto) {
        logger.info("user rest point");
        UserDto dto = new UserDto();
        dto.setName("test name");
        return null;
    }

    @Override
    public UserDto get() {
        logger.info("user rest point get");
        UserDto dto = new UserDto();
        dto.setName("test name");
        return dto;
    }
}
