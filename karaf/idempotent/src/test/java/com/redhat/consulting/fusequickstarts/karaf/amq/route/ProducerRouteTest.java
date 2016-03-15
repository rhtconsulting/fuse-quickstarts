package com.redhat.consulting.fusequickstarts.karaf.amq.route;

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
        return "direct:amqProducer";
    }

    /**
     * Mock Endpoints and Skip them
     */
    @Override
    public String isMockEndpointsAndSkip() {
        return "activemq:*";
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
        replaceRouteFromWith("amqIdempotentProducer", "direct:timer");
        super.setUp();
    }

    /**
     * Tests Message Consumer Route by replacing the timer component with a direct route, and then mocking the other endpoints
     * to assert that they received the proper messages.
     */
    @Test
    public void testProduceMessageRoute() throws Exception {
        // Get Mocks
        MockEndpoint jmsEndpoint = getMockEndpoint("mock:activemq:queue:myIdempotentQueue");

        // Set Assertions
        jmsEndpoint.expectedMessageCount(1);
        jmsEndpoint.expectedBodiesReceived("Sample AMQ Message");

        // Send Test Message to Endpoint that replaced the Timer
        // Sending a Blank Body because the Route Sets it
        template.sendBody("direct:timer", "Timer Trigger");

        // Set Mocks Satisfied
        jmsEndpoint.assertIsSatisfied();
    }

}
