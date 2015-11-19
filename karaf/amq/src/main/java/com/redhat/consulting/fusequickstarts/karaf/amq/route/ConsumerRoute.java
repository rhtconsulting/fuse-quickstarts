package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("activemq:queue:myQueue")
            .routeId("amqConsumer")
            .setBody().simple("Received Message: ${body}")
            .to("log:amqConsumerLog");
    }

}
