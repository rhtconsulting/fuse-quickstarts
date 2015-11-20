package com.redhat.consulting.fusequickstarts.karaf.osgi_service.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route is triggered by a Timer and called the Hello Service that is exposed from the other bundle.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://serviceTimer?fixedRate=true&period=5000")
            .beanRef("helloService", "sayMessage")
            .to("log:helloServiceLog")
            .beanRef("helloService", "sayHello(Jim)")
            .to("log:helloServiceLog");
    }

}
