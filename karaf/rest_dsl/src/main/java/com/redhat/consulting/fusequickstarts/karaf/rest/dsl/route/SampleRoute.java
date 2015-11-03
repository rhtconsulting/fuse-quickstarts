package com.redhat.consulting.fusequickstarts.karaf.rest.dsl.route;

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
        rest("/rest")
            .get("/get").to("direct:get") //GET request
            .post("/post").consumes("application/json").to("direct:post"); //POST request

        from("direct:get")
            .transform().constant("Successful GET Request"); //returns a plain string payload
        
        from("direct:post")//returns no response data and logs the success of the request
            .log(LoggingLevel.INFO, "com.redhat.consulting.fusequickstarts.karaf.rest.dsl.route", "Successful POST Request"); 

    }
}
