package com.redhat.consulting.fusequickstarts.eap.deployment.route.scan;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/**
 * Another Sample Route. This one will be picked up via Package Scanning.
 */
@Startup
@ApplicationScoped
@ContextName("cxf-camel-context")
public class AnotherSampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Logs Goodbye World every 2000 milliseconds
        from("timer://myOtherEapTimer?fixedRate=true&period=2000").log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.eap.deployment.route.scan", "Goodbye World").to(
            "log:GoodbyeWorldLog?level=INFO");

    }

}
