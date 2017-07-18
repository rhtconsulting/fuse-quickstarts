package com.redhat.consulting.fusequickstarts.karaf.red.route;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Assert;
import org.junit.Test;

import com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.route.EndpointRoute;

public class EndpointRouteTest extends CamelTestSupport {

    /**
     * Mock all the Endpoints
     */
    @Override
    public String isMockEndpoints() {
        return "*";
    }

    /**
     * Set Routes for Testing
     */
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new EndpointRoute();
    }

    @Override
    public void setUp() throws Exception {
        replaceRouteFromWith("restEndpointConsumer", "direct:restTestEndpoint");
        super.setUp();
    }

    /**
     * Test GET Direct Route
     */
    @Test
    public void testDirectGet() {

        // Send Test Message to
        Exchange responseExchange = template.send("direct:restTestEndpoint", new Processor() {
            public void process(Exchange exchange) {
                exchange.setPattern(ExchangePattern.InOut);
                Message inMessage = exchange.getIn();
                // set the operation name
                inMessage.setHeader(CxfConstants.OPERATION_NAME, "getSampleUser");
                // using the proxy client API
                inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.FALSE);
                // creating the request
                MessageContentsList req = new MessageContentsList();
                req.add("1");
                inMessage.setBody(req);
            }
        });

        // Test Response
        Assert.assertNotNull("Response was Null", responseExchange);

        Message responseMessage = responseExchange.getIn();
        Assert.assertNotNull("Response Message was Null", responseMessage);
    }

}
