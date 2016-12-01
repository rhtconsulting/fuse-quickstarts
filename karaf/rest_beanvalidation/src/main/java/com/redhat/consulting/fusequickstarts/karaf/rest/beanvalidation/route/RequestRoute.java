package com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.route;

import com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.processor.InvalidFiftyPercentOfTimesRequestProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/*
 * The route for sending requests to our cxf endpoint
 */
public class RequestRoute extends RouteBuilder {

    private final InvalidFiftyPercentOfTimesRequestProcessor requestProcessor = new InvalidFiftyPercentOfTimesRequestProcessor();

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("timer://restRequestTimer?period=5000") //period in milliseconds
            .routeId("restEndpointTestProducer")
            .log(LoggingLevel.INFO, "Starting request")
            .process(requestProcessor) //set the appropriate headers
            .to("cxfrs://bean://rsClient");
    }
}
