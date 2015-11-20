package com.redhat.consulting.fusequickstarts.karaf.shared_component.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and send it to an activemq queue.
 */

public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://myTimer?fixedRate=true&period=2000")
            .routeId("sharedAmqProducer")
            .transform().simple("Shared Sample AMQ Message")
            .log("Created Message: ${body}")
            .to("sharedamq:queue:mySharedQueue");
    }

}
