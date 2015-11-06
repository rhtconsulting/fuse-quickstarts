package com.redhat.consulting.fusequickstarts.karaf.deployment.route.scan;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Another Sample Route. This one will be picked up via Package Scanning.
 */
public class AnotherSampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // Logs Goodbye World every 2000 milliseconds
        from("timer://myOtherTimer?fixedRate=true&period=2000")
            .log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.karaf.deployment.route.scan", "Goodbye World")
            .to("log:GoodbyeWorldLog?level=INFO");

    }

}
