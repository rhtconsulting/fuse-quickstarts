package com.redhat.consulting.fusequickstarts.karaf.rest.dsl.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

import com.redhat.consulting.fusequickstarts.karaf.rest.dsl.model.Note;

public class RestDslRouteTest extends CamelTestSupport {

    /**
     * Set Routes for Testing
     */
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RestDslRoute();
    }

    /**
     * Test GET Direct Route
     */
    @Test
    public void testDirectGet() {

        // Send Test Message to
        Exchange responseExchange = template.send("direct:get", new Processor() {
            public void process(Exchange exchange) {
                Message in = exchange.getIn();
                in.setBody("");
            }
        });

        // Test Response
        Assert.assertNotNull("Response was Null", responseExchange);

        Message responseMessage = responseExchange.getIn();
        Assert.assertNotNull("Response Message was Null", responseMessage);

        Note responseNote = responseMessage.getBody(Note.class);
        Assert.assertNotNull("Note was Null", responseNote);

        Assert.assertEquals("To was Incorrect", "User", responseNote.getTo());
        Assert.assertEquals("From was Incorrect", "Developer", responseNote.getFrom());
        Assert.assertEquals("Message was Incorrect", "REST is Awesome", responseNote.getMessage());
    }

    /**
     * Test Post Direct Route
     */
    @Test
    public void testDirectPost() {

        final Note sampleMessage = new Note();
        sampleMessage.setFrom("Developer");
        sampleMessage.setTo("User");
        sampleMessage.setMessage("REST is Awesome");

        // Send Test Message to
        Exchange responseExchange = template.send("direct:post", new Processor() {
            public void process(Exchange exchange) {
                Message in = exchange.getIn();
                in.setBody(sampleMessage);
            }
        });

        // Test Response
        Assert.assertNotNull("Response was Null", responseExchange);

        Message responseMessage = responseExchange.getIn();
        Assert.assertNotNull("Response Message was Null", responseMessage);

        Assert.assertEquals("Incoorect Response Code", "201", responseMessage.getHeader(Exchange.HTTP_RESPONSE_CODE));
    }

}
