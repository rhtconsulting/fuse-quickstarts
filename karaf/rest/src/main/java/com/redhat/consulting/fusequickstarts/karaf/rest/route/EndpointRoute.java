package com.redhat.consulting.fusequickstarts.karaf.rest.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;

import com.redhat.consulting.fusequickstarts.karaf.rest.processor.MyResourceImpl;

/*
 * The route for receiving requests from out cxf endpoints.
 */

public class EndpointRoute extends RouteBuilder {

    private MyResourceImpl myResourceImpl = new MyResourceImpl();

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
            .routeId("restEndpointConsumer")
            .choice() //selects what to do based on the request
                .when(header(CxfConstants.OPERATION_NAME).isEqualTo("getSampleUser"))
                    .bean(myResourceImpl, "getSampleUser")
                    .log(LoggingLevel.INFO, "Request Recieved") 
                .otherwise()
                    .log(LoggingLevel.WARN, "Unknown Method");

    }

}
