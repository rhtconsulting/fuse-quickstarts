package com.redhat.consulting.fusequickstarts.karaf.shared_component.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("sharedamq:queue:mySharedQueue")
            .routeId("sharedAmqConsumer")
            .setBody().simple("Received Message: ${body}")
            .to("log:amqConsumerLog");
    }

}
