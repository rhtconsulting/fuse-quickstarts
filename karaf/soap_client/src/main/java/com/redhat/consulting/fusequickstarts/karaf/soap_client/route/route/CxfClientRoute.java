package com.redhat.consulting.fusequickstarts.karaf.soap_client.route.route;

import org.apache.camel.builder.RouteBuilder;

public class CxfClientRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        // Process the Requests coming in to the Endpoint
        from("cxf:bean:customerOrderEndpoint")
            .log("Got a SOAP Message: ${body}")
            .choice()
                .when(simple("${in.header.operationName} == 'placeOrder'"))
                    .to("direct:placeOrder")
                .when(simple("${in.header.operationName} == 'getOrder'"))
                    .to("direct:getOrder")
            .endChoice();
        
    }

}
