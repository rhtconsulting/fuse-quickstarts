package com.redhat.consulting.fusequickstarts.karaf.jms.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and send it to an activemq queue.
 */

public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("timer://myTimer?fixedRate=true&period=2000")
            .routeId("jmsProducer")
            .to("direct:jmsProducer");
        
        from("direct:jmsProducer")
            .routeId("directJmsProducer")
            .transform().simple("Sample JMS Message")
            .log("Created Message: ${body}")
            .to("jms:queue:jmsTestQueue?clientId=jmsProducerRoute");
    }

}
