package com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.processor;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;

public class MyServiceImpl {

    public void getSampleUser(Exchange exchange) throws Exception {
        exchange.getOut().setBody(Response.status(Response.Status.OK).entity("Sample User").build());
    }
}
