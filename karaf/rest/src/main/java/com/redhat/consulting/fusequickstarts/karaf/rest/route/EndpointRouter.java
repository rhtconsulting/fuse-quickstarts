package com.redhat.consulting.fusequickstarts.karaf.rest.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class EndpointRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
            .log(LoggingLevel.INFO, "Request Recieved ${body}");

    }

}
