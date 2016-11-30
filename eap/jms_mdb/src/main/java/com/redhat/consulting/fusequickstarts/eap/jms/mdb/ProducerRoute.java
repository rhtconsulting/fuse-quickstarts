package com.redhat.consulting.fusequickstarts.eap.jms.mdb;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;

/*
 * This route runs based on a timer.  It creates a message with 
 * a simple text body and sends it to a queue.
 */

@Startup
@ApplicationScoped
@ContextName("jms-mdb")
public class ProducerRoute extends RouteBuilder {
	@Resource(mappedName ="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
    @Override
    public void configure() throws Exception {
    	JmsComponent component = new JmsComponent();
    	component.setConnectionFactory(connectionFactory);
    	
    	getContext().addComponent("jms", component);
    	
        from("timer://myTimer?fixedRate=true&period=5000")
    	    .routeId("producer")
            .setBody().simple("Sample JMS Message")
            .log("Created Message: ${body}")
            .to("jms:queue:queue1");
        
    }

}