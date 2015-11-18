package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import org.apache.camel.builder.RouteBuilder;

public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("timer://myTimer?fixedRate=true&period=2000")
            .transform().simple("Produced Message")
            .to("activemq:queue:myQueue");
    }

}
