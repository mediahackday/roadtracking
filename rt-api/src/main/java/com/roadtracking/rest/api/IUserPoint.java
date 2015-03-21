package com.roadtracking.rest.api;

import com.roadtracking.rest.dto.UserDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface IUserPoint {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    UserDto put(UserDto userDto);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UserDto get();
}
