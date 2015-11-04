package com.redhat.consulting.fusequickstarts.karaf.properties.encrypted.route;

import org.apache.camel.builder.RouteBuilder;

public class EncryptedPropertiesRoute extends RouteBuilder {

    /**
     * Message that Will be overridden by the Injected Property that Jasypt Decrypts.
     */
    String message = "You shouldnt see this";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {

        // Logs Value of Property every 2000 milliseconds
        from("timer://myTimer?fixedRate=true&period=2000").routeId("encryptedPropertiesRoute")
                .setBody(simple("Displaying Injected Property 'message': " + message))
                .to("log:EncryptedPropertiesLog?level=INFO")
                .setBody(simple("Reading Property 'test.message.enc': {{test.message.enc}}"))
                .to("log:EncryptedPropertiesLog?level=INFO");

    }

    /**
     * Get the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message.
     * 
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
