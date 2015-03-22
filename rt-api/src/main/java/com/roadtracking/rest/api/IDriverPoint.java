package com.roadtracking.rest.api;

import com.roadtracking.rest.dto.DriverDto;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface IDriverPoint {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    DriverDto put(DriverDto driverDto, @Context HttpServletRequest request);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    DriverDto get(@Context HttpServletRequest request);
}
