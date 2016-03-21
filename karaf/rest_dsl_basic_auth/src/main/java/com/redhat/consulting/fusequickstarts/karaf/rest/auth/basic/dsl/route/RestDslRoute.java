package com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.route;

import com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.model.Note;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;

public class RestDslRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // Create a Sample Message to Return from the GET Endpoint
        final Note sampleMessage = new Note();
        sampleMessage.setFrom("Developer");
        sampleMessage.setTo("User");
        sampleMessage.setMessage("REST is Awesome");
        
        // Configure the Rest DSL to use Jetty
        restConfiguration().component("jetty")
            .host("localhost").port(8183)
           .bindingMode(RestBindingMode.json)
            .endpointProperty("handlers", "securityHandler"); // Specifying This is Critical to get the Security Working

        // Configure the Root URL of the Endpoint
        rest("/rest")
            .consumes("application/json") // Consume JSON Only
            .produces("application/json") // Return JSON Only
            .get("/message") // GET request
                .outType(Note.class) // Outgoing Type
                .to("direct:get") 
            .post("/message") // POST request
                .type(Note.class) // Incoming Type
                .to("direct:post"); 

        // Casts the Simple POJO to JSON and Returns it
        from("direct:get")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(sampleMessage);
                }
            })
            .log("Successful GET Request: ${body}");

        // Logs the Request and Returns a 201 HTTP Status Code.
        from("direct:post")
                .log("Successful POST Request: ${body}")
                .setBody(constant(""))
                .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("201"));

    }
}
