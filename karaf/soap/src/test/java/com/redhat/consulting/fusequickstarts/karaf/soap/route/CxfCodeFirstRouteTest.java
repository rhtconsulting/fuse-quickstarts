package com.redhat.consulting.fusequickstarts.karaf.soap.route;

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

import com.redhat.consulting.fusequickstarts.karaf.soap.model.CustomerOrder;
import com.redhat.consulting.fusequickstarts.karaf.soap.model.CustomerOrderResponse;

public class CxfCodeFirstRouteTest extends CamelTestSupport {
	
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
        return new CxfCodeFirstRoute();
    }
    
    @Override
    public void setUp() throws Exception {
        replaceRouteFromWith("customerOrderEndpoint", "direct:cxfTestEndpoint");
        super.setUp();
    }
    
    /**
     * Test GET Direct Route
     */
    @Test
    public void testDirectGet() {

        // Send Test Message to
        Exchange responseExchange = template.send("direct:cxfTestEndpoint", new Processor() {
            public void process(Exchange exchange) {
                exchange.setPattern(ExchangePattern.InOut);
                Message inMessage = exchange.getIn();
                // set the operation name
                inMessage.setHeader(CxfConstants.OPERATION_NAME, "getOrder");
                // using the proxy client API
                //inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.FALSE);
                // creating the request
                MessageContentsList req = new MessageContentsList();
                req.add("ORD34B");
                inMessage.setBody(req);
            }
        });

        // Test Response
        Assert.assertNotNull("Response was Null", responseExchange);

        Message responseMessage = responseExchange.getOut();
        Assert.assertNotNull("Response Message was Null", responseMessage);
        CustomerOrder order = responseMessage.getBody(CustomerOrder.class);
        Assert.assertNotNull("Order was Null", order);
        Assert.assertEquals("Order Id was Incorrect", order.getOrderId(), "ORD34B");
        Assert.assertEquals("Order quantity was Incorrect", order.getQuantity().intValue(), 5);
    }


    /**
     * Test Post Direct Route
     */
    @Test
    public void testDirectPost() {

        final CustomerOrder sampleMessage = new CustomerOrder();
        sampleMessage.setItem("Widget");
        sampleMessage.setOrderId("ORD56S");
        sampleMessage.setQuantity(34);

        // Send Test Message to
        Exchange responseExchange = template.send("direct:cxfTestEndpoint", new Processor() {
            public void process(Exchange exchange) {
                exchange.setPattern(ExchangePattern.InOut);
                Message inMessage = exchange.getIn();
                // set the operation name
                inMessage.setHeader(CxfConstants.OPERATION_NAME, "placeOrder");
                inMessage.setBody(sampleMessage);
            }
        });

        // Test Response
        Assert.assertNotNull("Response was Null", responseExchange);

        Message responseMessage = responseExchange.getOut();
        Assert.assertNotNull("Response Message was Null", responseMessage);

        CustomerOrderResponse cor = responseMessage.getBody(CustomerOrderResponse.class);
        Assert.assertNotNull("Response was null", cor);
        Assert.assertEquals("Order was not completed",cor.isCompleted(), true);
    }


}
