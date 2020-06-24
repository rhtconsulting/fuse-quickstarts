package com.redhat.consulting.fusequickstarts.springboot.routedeployment;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.Set;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
// Prevent camel context from starting before routes have been altered with AdviceWith
@UseAdviceWith
// Convert all stub endpoints to mock endpoints
@MockEndpoints("stub:*")
// Mark the context as dirty after this class, because the routes were altered with AdviceWith. This will
// cause the context to be torn down and recreated after the tests in this class complete.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RouteTwoIntegrationTest {

    private static final Set<String> INCLUDED_ROUTE_GROUPS = Collections.singleton("javaRouteBuilderRoutes");

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producer;

    @EndpointInject(uri = "mock:stub:routeEnd")
    private MockEndpoint routeEndEndpoint;

    @Before
    public void setUp() {
        // Set autoStartup to true on routes within the groups specified by INCLUDED_ROUTE_GROUPS
        // Note: You could similarly filter on routeDefinition.getId() for the route ID.
        camelContext.getRouteDefinitions()
                .forEach(routeDefinition -> routeDefinition.autoStartup(INCLUDED_ROUTE_GROUPS.contains(routeDefinition.getGroup())));
    }

    @Test
    public void testRouteTwo_Success() throws Exception {
        // Replace the timer with a direct component "from"
        camelContext.getRouteDefinition("routeTwo")
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() {
                        replaceFromWith("direct:mock-timer-trigger");
                    }
                });

        // Set up an expectation for exactly one message
        routeEndEndpoint.expectedMessageCount(1);

        // Start the context -- @UseAdviceWith will prevent the camel context from starting on its own
        camelContext.start();

        // Send a message into the route
        producer.sendBody("direct:mock-timer-trigger", null);

        // Verify that the routeEndEndpoint received a message
        routeEndEndpoint.assertIsSatisfied();
    }
}
