package com.redhat.consulting.fusequickstarts.eap.jms.route;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;

/*
 * This route picks up from the activemq queue and logs the body of
 * the message.
 */
@Startup
@ApplicationScoped
@ContextName("eap-jms")
public class ConsumerRoute extends RouteBuilder {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void configure() throws Exception {
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);

        getContext().addComponent("jms", component);
        // @formatter:off
        from("jms:queue:eapJmsTestQueue?clientId=eapJmsConsumerRoute")
            .routeId("eapJmsConsumer")
            .to("direct:eapJmsConsumer");
        
        from("direct:eapJmsConsumer")
            .routeId("eapDirectJmsConsumer")
            .setBody().simple("Received Message: ${body}")
            .to("log:eapJmsConsumerLog");
    }

}
