package com.delta.rm.offer.api.services.flight;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.iata.iata._2015._00._2018_1.airshoppingrq.*;
@Path("/flight")
@Consumes(
{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces(
{ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class FlightService {
	@POST
	public Response post() {
		Map<String, Object> responseMap = new HashMap();
		responseMap.put("1", "abc");
		return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/search")
	public Response search(AirShoppingRQ airShoppingRQ) {
		RequestType req = airShoppingRQ.getRequest();
		FlightRequestType flightReq = req.getFlightRequest();
		flightReq.getShoppingResponse();
		return Response.ok(airShoppingRQ, MediaType.APPLICATION_XML).build();
	}
}
