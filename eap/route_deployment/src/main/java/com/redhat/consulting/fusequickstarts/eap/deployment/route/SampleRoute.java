package com.redhat.consulting.fusequickstarts.eap.deployment.route;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/*
 * Annotation below are required for EAP to pick up the camel route
 */
@Startup
@ApplicationScoped
@ContextName("cxf-camel-context")
public class SampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        // Logs Hello World every 2000 milliseconds
        from("timer://myEapTimer?fixedRate=true&period=2000")
            .log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.eap.deployment.route", "Hello World")
            .to("log:HelloWorldLog?level=INFO");

    }

}
