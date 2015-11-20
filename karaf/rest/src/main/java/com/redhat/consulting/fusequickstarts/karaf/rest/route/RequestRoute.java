package com.redhat.consulting.fusequickstarts.karaf.rest.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.redhat.consulting.fusequickstarts.karaf.rest.processor.RequestProcessor;

/*
 * The route for sending requests to our cxf endpoint
 */
public class RequestRoute extends RouteBuilder {

    private final RequestProcessor requestProcessor = new RequestProcessor();

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("timer://restRequestTimer?period=5000") //period in milliseconds
            .log(LoggingLevel.INFO, "Starting request")
            .process(requestProcessor) //set the appropriate headers
            .to("cxfrs://bean://rsClient");
    }
}
