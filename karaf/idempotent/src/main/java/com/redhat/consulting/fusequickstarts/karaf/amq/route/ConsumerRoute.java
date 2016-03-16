package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route uses an Idempotent Consumer and consumes messages from the Queue.
 * It uses a Custom Idempotent Repository based on Guava to Cache the Keys and then
 * Expire them after 60 seconds. This has the effect of only removing Duplicates for a 
 * 60 second period.
 */

public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:myIdempotentQueue")
            .routeId("amqIdempotentConsumer")
            .idempotentConsumer(header("appId"), GuavaIdempotentRepository.guavaIdempotentRepository(60000))
                .skipDuplicate(true)
            .setBody().simple("Received Message: ${body}")
            .to("log:amqConsumerLog");
    }

}
