package com.redhat.consulting.fusequickstarts.springboot.routedeployment;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteTwo extends RouteBuilder {
    @Override
    public void configure() {
        //@formatter:off
        from("timer:routeTwoTimer")
            .routeId("routeTwo")
            .routeGroup("javaRouteBuilderRoutes")
            .routeProperty("fixedRate", "true")
            .routeProperty("period", "1s")
                .log("Timer triggered in routeTwo (from RouteTwo.java)")
                .to("stub:routeEnd");
        //@formatter:on
    }
}
