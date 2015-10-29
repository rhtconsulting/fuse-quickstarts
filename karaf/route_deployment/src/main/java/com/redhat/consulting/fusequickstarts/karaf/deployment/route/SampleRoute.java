package com.redhat.consulting.fusequickstarts.karaf.deployment.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class SampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://myTimer?fixedRate=true&period=2000").log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.karaf.deployment.route", "Hellow World").to(
            "log:HelloWorldLog?level=INFO");

    }

}
