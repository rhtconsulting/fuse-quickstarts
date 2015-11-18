package com.redhat.consulting.fusequickstarts.karaf.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.consulting.fusequickstarts.karaf.rest.model.User;

@Path(value = "/")
public interface MyResource {

    @GET
    @Path(value = "/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User getSampleUser(@PathParam("id") String id);

}
