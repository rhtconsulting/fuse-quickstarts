package com.redhat.consulting.fusequickstarts.karaf.rest.route;

import org.apache.camel.builder.RouteBuilder;

public class EndpointRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("direct:test1")
        .to("direct:testend1");
        
//        from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
//            .choice()
//                .when(header(CxfConstants.OPERATION_NAME).isEqualTo("getSampleUser"))
//                    .log(LoggingLevel.INFO, "Request Recieved ${body}")
//                .otherwise()
//                    .log(LoggingLevel.WARN, "Unknown Method");

    }

}
