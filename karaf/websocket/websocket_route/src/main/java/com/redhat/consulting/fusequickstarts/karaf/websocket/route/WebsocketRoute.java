package com.redhat.consulting.fusequickstarts.karaf.websocket.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;


public class WebsocketRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("websocket://0.0.0.0:9090/websocket-chat").routeId("websocketChat")
            .log(LoggingLevel.INFO,">> Message received : ${body}")
            .to("websocket://0.0.0.0:9090/websocket-chat?sendToAll=true&staticResources=classpath:webapp");
    }

}
