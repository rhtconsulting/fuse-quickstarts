package com.redhat.consulting.fusequickstarts.karaf.rest.dsl.simple.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class SampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        
        //configures the rest dsl to use the restlet component as well as defines the appropriate host, port, and binding
        restConfiguration().component("restlet").host("localhost").port(8182).bindingMode(RestBindingMode.off);
        
        //root url
        rest("/simplerest")
            .consumes("application/json") // Consume JSON Only
            .produces("application/json") // Return JSON Only
            .get("/get").to("direct:get") //GET request
            .post("/post").to("direct:post"); //POST request

        from("direct:get")
            .transform().constant("Successful GET Request"); //returns a plain string payload
        
        from("direct:post")//returns no response data and logs the success of the request
            .log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.karaf.rest.dsl.route", "Successful POST Request")
            .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("200")); 

    }
}
