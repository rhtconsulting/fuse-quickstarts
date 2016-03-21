package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and header and sends it to an activemq queue.
 */

public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("timer://myTimer?fixedRate=true&period=15000")
            .routeId("amqIdempotentProducer")
            .transform()
                .simple("Sample AMQ Message")
            .setHeader("appId", constant("5"))
            .log("Created Message: ${body}")
            .to("activemq:queue:myIdempotentQueue");
    }

}
