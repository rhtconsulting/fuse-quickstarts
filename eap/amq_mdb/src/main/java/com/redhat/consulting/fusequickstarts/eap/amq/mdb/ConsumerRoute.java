package com.redhat.consulting.fusequickstarts.eap.amq.mdb;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/*
 * This consumer route receives messages sent from the Producer Template in the MDB 
 */

@Startup
@ApplicationScoped
@ContextName("amq-mdb")
public class ConsumerRoute extends RouteBuilder {
		
    @Override
    public void configure() throws Exception {    	
        from("direct:consumer")
            .routeId("consumer")
            .log("Received Message: ${body.text}");
    }
    
}