package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.apache.camel.builder.RouteBuilder;


public class LocalClientRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
               
        from("timer://localClient?fixedRate=true&period=5000")
            .to("bean:localClient?method=process('PUT', ${property.CamelTimerCounter}, ${property.CamelTimerFiredTime})")
            .to("log:localClient?showAll=true")
            .to("bean:localClient?method=process('GET', ${property.CamelTimerCounter}, '')")
            .to("log:localClient?showAll=true");
        
    }

}
