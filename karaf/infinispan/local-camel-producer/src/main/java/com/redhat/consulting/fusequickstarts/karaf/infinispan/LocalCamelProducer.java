package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.apache.camel.builder.RouteBuilder;

public class LocalCamelProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://localCamelProducer?fixedRate=true&period=5000")
            // Set Headers for Infinispan
            .setHeader("CamelInfinispanKey", simple("${exchangeProperty.CamelTimerCounter}"))
            .setHeader("CamelInfinispanValue", simple("${exchangeProperty.CamelTimerCounter}"))
            
            // Call Infinispan
            .to("infinispan://localhost?cacheContainer=#cacheManager")
            
            // Log
            .to("log:localCamelProducer?showAll=true");
    }

}
