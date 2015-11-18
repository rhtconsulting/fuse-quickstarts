package com.redhat.consulting.fusequickstarts.karaf.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/")
public interface MyResource {

    @GET
    @Path(value = "/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String getSampleUser();

}
