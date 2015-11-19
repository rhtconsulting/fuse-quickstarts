package com.redhat.consulting.fusequickstarts.karaf.jms.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("jms:queue:jmsTestQueue?clientId=jmsConsumerRoute")
            .log("Received Message: ${body}");
    }

}
