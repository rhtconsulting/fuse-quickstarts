package com.redhat.consulting.fusequickstarts.karaf.msf;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteDispatcher {

    // Config Values
    private String greeting;
    private String name;
    private String routeId;

    private RouteBuilder routeBuilder;
    private CamelContext camelContext;

    private Logger LOG = LoggerFactory.getLogger(RouteDispatcher.class);

    /**
     * Default Constructor
     */
    RouteDispatcher() {
        // Do Nothing
    }

    /**
     * Start the Route
     */
    public void start() {
        try {
            routeBuilder = buildRoute();
            if (routeBuilder != null) {
                LOG.info("Route " + routeBuilder + " starting...");
                camelContext.start();
                camelContext.addRoutes(routeBuilder);
            } else {
                LOG.error("Route Builder is Null");
            }
        } catch (Exception ex) {
            LOG.error("Could Not Start Route: " + ex, ex);
        }
    }

    /**
     * Build the Route
     */
    protected RouteBuilder buildRoute() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("timer://helloTimer?fixedRate=true&period=10000").routeId(routeId)
                    .log(greeting + " " + name);
            }
        };

    }

    /**
     * Stop the Route
     */
    public void stop() {
        if (routeBuilder != null) {
            try {
                camelContext.stopRoute(routeId);
                camelContext.removeRoute(routeId);
            } catch (Exception e) {
                LOG.error("Could not remove route " + routeBuilder + " " + e);
            }
        }
    }

    public void setCamelContext(CamelContext cc) {
        this.camelContext = cc;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
