package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
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
