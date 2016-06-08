package com.redhat.consulting.fusequickstarts.eap.jaxrs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public interface CustomerService {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomers();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomer(@PathParam("id") String id);

}
