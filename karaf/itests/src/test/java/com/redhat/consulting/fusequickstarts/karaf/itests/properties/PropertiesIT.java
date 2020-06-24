package com.redhat.consulting.fusequickstarts.karaf.itests.properties;

import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.karaf.features.FeaturesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.util.Filter;
import org.osgi.framework.BundleContext;

import com.redhat.consulting.fusequickstarts.karaf.itests.support.FuseTestUtil;

@RunWith(PaxExam.class)
public class PropertiesIT extends CamelTestSupport {

    /**
     * Executor Service for Executing Karaf Shell Commands
     */
    // Since we are not Executing any Commands in this test, this commented out.
    // private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Features Service for Working with Features
     */
    @Inject
    private FeaturesService featuresService;

    /**
     * Bundle Context for working with Bundles
     */
    @Inject
    private BundleContext bundleContext;

    /**
     * Inject the Camel Context we are going to be Testing. This should match the name of the Context in the Bundle
     * under Test.
     */
    @Inject
    @Filter("(camel.context.name=fusequickstart-properties-camel)")
    protected CamelContext camelContext;

    /**
     * Configure the Container
     */
    @Configuration
    public Option[] config() {
        return FuseTestUtil.container();
    }

    /**
     * Tell Camel we are using AdviceWith so it will not Automatically Start our Camel Context
     */
    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    /**
     * Force Camel Test to use our Camel Context, Otherwise it will create its own
     */
    @Override
    protected CamelContext createCamelContext() throws Exception {
        return camelContext;
    }

    /**
     * Test that the Container is properly setup for our test and that everything was properly injected.
     * 
     * @throws Exception
     */
    @Before
    public void testSetup() throws Exception {
        // Assert Camel Features Installed
        assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-core")));
        assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-blueprint")));

        // Assert Bundle is Activated
        FuseTestUtil.assertBundleActive("com.redhat.consulting.fusequickstarts.karaf.properties", bundleContext);

        // Assert Camel Context Injected
        Assert.assertNotNull("Camel Context was Null", camelContext);
        Assert.assertEquals("Injected Incorrect Camel Context", "fusequickstart-properties-camel",
                camelContext.getName());
    }

    /**
     * Test that the Properties are Properly Read from the cfg File and Logged.
     * 
     * @throws Exception
     */
    @Test
    public void testPropertyValues() throws Exception {

        // Mock Endpoints using Route Advice With
        camelContext.getRouteDefinition("propertiesRoute").adviceWith(camelContext.adapt(ModelCamelContext.class),
                new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        // Mock All Endpoints
                        mockEndpoints("*");
                    }
                });

        // Set Assertions on Mock Endpoint
        getMockEndpoint("mock:log:PropertiesLog").expectedBodiesReceived(
                "Reading Property 'test.foo': Hello",
                "Reading Property 'test.bar': World");

        // Start Context
        // Since we are using AdviceWith, Starting/Stopping the Context must be done Manually.
        camelContext.start();

        // Assert Conditions Met -- endpoints will wait and become satisfied immediately upon receiving the last message
        assertMockEndpointsSatisfied();

        // Stop Context
        camelContext.stop();
    }

}
