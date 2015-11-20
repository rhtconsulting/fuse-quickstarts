package com.redhat.consulting.fusequickstarts.karaf.osgi_service.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://serviceTimer?fixedRate=true&period=5000")
            .routeId("serviceConsumer")
            .beanRef("helloService", "sayHello(Jim)")
            .to("log:helloServiceLog");
    }

}
