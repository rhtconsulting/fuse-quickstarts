package com.redhat.consulting.fusequickstarts.eap.jms.route;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and send it to an activemq queue.
 */

@Startup
@ApplicationScoped
@ContextName("eap-jms")
public class ProducerRoute extends RouteBuilder {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void configure() throws Exception {
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);

        // @formatter:off
        from("timer://myEapJmsTimer?fixedRate=true&period=2000")
            .routeId("eapJmsProducer")
            .to("direct:eapJmsProducer");
        
        from("direct:eapJmsProducer")
            .routeId("eapDirectJmsProducer")
            .transform().simple("Sample JMS Message")
            .log("Created Message: ${body}")
            .to("jms:queue:eapJmsTestQueue?clientId=eapJmsProducerRoute");
    }

}
