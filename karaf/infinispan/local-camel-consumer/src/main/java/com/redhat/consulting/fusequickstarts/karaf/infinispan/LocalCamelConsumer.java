package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.apache.camel.builder.RouteBuilder;

public class LocalCamelConsumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("infinispan://foo?cacheContainer=#cacheManager")
            .to("log:localCamelConsumer?showAll=true");

    }

}
