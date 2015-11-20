package com.redhat.consulting.fusequickstarts.karaf.rest.processor;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;

public class MyResourceImpl {

    public void getSampleUser(Exchange exchange) throws Exception {
        exchange.getOut().setBody(Response.status(Response.Status.OK).entity("Sample User").build());
    }
}
