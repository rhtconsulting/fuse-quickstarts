package com.redhat.consulting.fusequickstarts.eap.jms.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ConsumerRouteTest extends CamelTestSupport {

    /**
     * Mock all the Endpoints
     */
    @Override
    public String isMockEndpoints() {
        return "*";
    }

    /**
     * Create Routes
     */
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new ConsumerRoute();
    }

    /**
     * Replace the incoming JMS URI with a Direct URI
     */
    @Override
    public void setUp() throws Exception {
        replaceRouteFromWith("eapJmsConsumer", "direct:eapJmsTestQueue");
        super.setUp();
    }

    /**
     * Tests Message Consumer Route by replacing the jms component with a direct route, and then mocking the other endpoints to
     * assert that they received the proper messages.
     */
    @Test
    public void testConsumeMessageRoute() throws Exception {
        // Get Mocks
        MockEndpoint incomingMsg = getMockEndpoint("mock:direct:eapJmsConsumer");
        MockEndpoint logEndpoint = getMockEndpoint("mock:log:eapJmsConsumerLog");

        // Set Assertions
        incomingMsg.expectedMessageCount(1);
        incomingMsg.expectedBodiesReceived("Test JMS Message");
        logEndpoint.expectedMessageCount(1);
        logEndpoint.expectedBodiesReceived("Received Message: Test JMS Message");

        // Send Test Message to Endpoint that replaced the JMS Queue
        template.sendBody("direct:eapJmsTestQueue", "Test JMS Message");

        // Set Mocks Satisfied
        incomingMsg.assertIsSatisfied();
        logEndpoint.assertIsSatisfied();
    }

}
