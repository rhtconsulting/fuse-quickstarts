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
            .routeId("jmsConsumer")
            .to("direct:jmsConsumer");
        
        from("direct:jmsConsumer")
            .routeId("directJmsConsumer")
            .setBody().simple("Received Message: ${body}")
            .to("log:jmsConsumerLog");
    }

}
