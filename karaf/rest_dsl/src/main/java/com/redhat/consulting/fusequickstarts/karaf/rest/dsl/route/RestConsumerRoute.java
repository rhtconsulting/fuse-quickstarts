package com.redhat.consulting.fusequickstarts.karaf.rest.dsl.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.redhat.consulting.fusequickstarts.karaf.rest.dsl.model.Note;

public class RestConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // Create a Sample Message to Post to the POST Endpoint
        final Note sampleMessage = new Note();
        sampleMessage.setFrom("Developer");
        sampleMessage.setTo("User");
        sampleMessage.setMessage("REST is Awesome");
        
        // Route that calls the GET Endpoint
        from("timer://restTimer?fixedRate=true&period=1000&repeatCount=1")
            .log("Calling REST Endpoint at http://localhost:8182/rest/message with Method GET and Body: ${body}")
            .to("http4://localhost:8182/rest/message")
            .log("HTTP Response Code: ${header."+Exchange.HTTP_RESPONSE_CODE+"} and Body: ${body}");
        
        // Route that calls the POST Endpoint
        from("timer://restTimer?fixedRate=true&period=2000&repeatCount=1")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    exchange.getOut().setBody(sampleMessage);
                }
            })
            .marshal().json(JsonLibrary.Jackson) // Convert to JSON
            .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST)) // Set to POST Call
            .setHeader(Exchange.HTTP_URI, simple("http://localhost:8182/rest/message")) // Override URI on Endpoint Below. Used when you need to set Dynamic Properties in a URI
            .log("Calling REST Endpoint at ${header."+Exchange.HTTP_URI+"} with Method ${header."+Exchange.HTTP_METHOD+"} and Body: ${body}")
            .to("http4://postrequest")
            .log("HTTP Response Code: ${header."+Exchange.HTTP_RESPONSE_CODE+"}");
    }

}
