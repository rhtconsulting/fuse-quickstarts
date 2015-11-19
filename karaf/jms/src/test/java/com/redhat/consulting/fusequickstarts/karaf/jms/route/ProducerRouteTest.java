package com.redhat.consulting.fusequickstarts.karaf.jms.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ProducerRouteTest extends CamelTestSupport {

    /**
     * Mock Endpoints
     */
    @Override
    public String isMockEndpoints() {
        return "direct:jmsProducer";
    }
    
    /**
     * Mock Endpoints and Skip them
     */
    @Override
    public String isMockEndpointsAndSkip() {
        return "jms:*";
    }

    /**
     * Create Routes
     */
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new ProducerRoute();
    }

    /**
     * Replace the incoming Timer URI with a Direct URI
     */
    @Override
    public void setUp() throws Exception {
        replaceRouteFromWith("jmsProducer", "direct:timer");
        super.setUp();
    }

    /**
     * Tests Message Consumer Route by replacing the timer component with a direct
     * route, and then mocking the other endpoints to assert that they received
     * the proper messages.
     */
    @Test
    public void testProduceMessageRoute() throws Exception {
        // Get Mocks
        MockEndpoint outgoingMsg = getMockEndpoint("mock:direct:jmsProducer");
        MockEndpoint jmsEndpoint = getMockEndpoint("mock:jms:queue:jmsTestQueue");

        // Set Assertions
        outgoingMsg.expectedMessageCount(1);
        outgoingMsg.expectedBodiesReceived("Timer Trigger");
        jmsEndpoint.expectedMessageCount(1);
        jmsEndpoint.expectedBodiesReceived("Sample JMS Message");

        // Send Test Message to Endpoint that replaced the Timer
        // Sending a Blank Body because the Route Sets it
        template.sendBody("direct:timer", "Timer Trigger");

        // Set Mocks Satisfied
        outgoingMsg.assertIsSatisfied();
        jmsEndpoint.assertIsSatisfied();
    }

}
