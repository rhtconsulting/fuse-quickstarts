package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.apache.camel.builder.RouteBuilder;

public class RemoteCamelProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://remoteCamelProducer?fixedRate=true&period=5000")
            // Set Headers for Infinispan Put
            .setHeader("CamelInfinispanKey", simple("${exchangeProperty.CamelTimerCounter}"))
            .setHeader("CamelInfinispanValue", simple("${exchangeProperty.CamelTimerCounter}"))
            
            // Call Infinispan
            .to("infinispan://localhost")
            
            // Read the Value Back out
            .setHeader("CamelInfinispanKey", simple("${exchangeProperty.CamelTimerCounter}"))
            .setHeader("CamelInfinispanOperation", constant("CamelInfinispanOperationGet"))
            
            // Call Infinispan
            .to("infinispan://localhost")
            
            // Log
            .to("remoteCamelProducer?showAll=true");
    }

}
