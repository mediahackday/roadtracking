package com.roadtracking.rest.api;

import com.roadtracking.rest.dto.DriverDto;
import com.roadtracking.rest.dto.GeoPointDto;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/geo")
public interface IGeoRestPoint {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    void put(GeoPointDto geoPointDto, @Context HttpServletRequest request);
}
