package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.apache.camel.builder.RouteBuilder;


public class RemoteClientRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
               
        from("timer://remoteClient?fixedRate=true&period=5000")
            .to("bean:remoteClient?method=process('PUT', ${property.CamelTimerCounter}, ${property.CamelTimerFiredTime})")
            .to("log:remoteClient?showAll=true");
        
    }

}
