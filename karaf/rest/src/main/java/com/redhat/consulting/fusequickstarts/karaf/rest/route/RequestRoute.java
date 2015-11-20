package com.redhat.consulting.fusequickstarts.karaf.rest.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.redhat.consulting.fusequickstarts.karaf.rest.processor.RequestProcessor;

public class RequestRoute extends RouteBuilder {

    private final RequestProcessor requestProcessor = new RequestProcessor();

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("timer://restRequestTimer?period=5000") //period in milliseconds
            .log(LoggingLevel.INFO, "Starting request")
            .setHeader("type", simple("user"))
            .setBody(simple("1"))
            .process(requestProcessor)
            .to("cxfrs://bean://rsClient");
            //.log(LoggingLevel.INFO, "Message request sent");
    }
}
