package com.redhat.consulting.fusequickstarts.eap.amq.mdb;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and sends it to an activemq queue.
 */

@Startup
@ApplicationScoped
@ContextName("amq-mdb")
public class ProducerRoute extends RouteBuilder {
	@Resource(mappedName ="java:/activemq/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
    @Override
    public void configure() throws Exception {
    	ActiveMQComponent component = ActiveMQComponent.activeMQComponent();
    	component.setConnectionFactory(connectionFactory);
    	
    	getContext().addComponent("activemq", component);
    	
        from("timer://myTimer?fixedRate=true&period=5000")
    	    .routeId("producer")
            .setBody().simple("Sample AMQ Message")
            .log("Created Message: ${body}")
            .to("activemq:queue:queue1");
        
    }

}