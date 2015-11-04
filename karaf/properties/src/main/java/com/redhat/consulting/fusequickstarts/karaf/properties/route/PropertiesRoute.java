package com.redhat.consulting.fusequickstarts.karaf.properties.route;

import org.apache.camel.builder.RouteBuilder;

public class PropertiesRoute extends RouteBuilder {

    /**
     * Milliseconds Value for Timer. Will be overridden by the Injected Property.
     */
    String milliseconds = "1000";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
        // @formatter:off

        // Logs Value of Property every 2000 milliseconds
        from("timer://myTimer?fixedRate=true&period=" + this.milliseconds)
            .routeId("propertiesRoute")
            .setBody(simple("Reading Property 'test.foo': {{test.foo}}"))
            .to("log:PropertiesLog?level=INFO")
            .setBody(simple("Reading Property 'test.bar': {{test.bar}}"))
            .to("log:PropertiesLog?level=INFO");

    }

    /**
     * Get the milliseconds.
     * 
     * @return the milliseconds
     */
    public String getMilliseconds() {
        return this.milliseconds;
    }

    /**
     * Set the milliseconds.
     * 
     * @param milliseconds
     *            the milliseconds to set
     */
    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }

}
