package com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.model.Note;

public class RestDslRouteTest extends CamelTestSupport {

    /**
     * Set Routes for Testing
     * @throws Exception 
     */
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
    	return new RestDslRoute();
    }
    
    @Override
    protected JndiRegistry createRegistry() throws Exception{
    	JndiRegistry jr = new JndiRegistry(createJndiContext());
    	
        Constraint constraint = new Constraint(Constraint.__BASIC_AUTH, "user");
        constraint.setAuthenticate(true);

        ConstraintMapping cm = new ConstraintMapping();
        cm.setPathSpec("/*");
        cm.setConstraint(constraint);
        
    	ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
    	BasicAuthenticator basicAuthenticator=new BasicAuthenticator();
    	securityHandler.setAuthenticator(basicAuthenticator);
    	securityHandler.addConstraintMapping(cm);
    	securityHandler.setStrict(false);
      	HashLoginService loginService = new HashLoginService("TestRealm", "src/test/resources/testRealm.properties");
        securityHandler.setLoginService(loginService);
        
    	jr.bind("securityHandler", securityHandler);
    	return jr;
    }
    
    @Override
    public boolean isUseAdviceWith() {
        return true;
    }
    
    @Before
    public void mockEndpoints() throws Exception {
        //do nothing
     }

    /**
     * Test GET Direct Route
     * @throws Exception 
     */
    @Test
    public void testDirectGet() throws Exception {
    	context.start();
        // Send Test Message to
        Exchange responseExchange = template.send("direct:get", new Processor() {
            public void process(Exchange exchange) {
                Message in = exchange.getIn();
                in.setHeader("authUsername", "admin");
                in.setHeader("authPassword", "admin");
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
     * @throws Exception 
     */
    @Test
    public void testDirectPost() throws Exception {
    	context.start();
        final Note sampleMessage = new Note();
        sampleMessage.setFrom("Developer");
        sampleMessage.setTo("User");
        sampleMessage.setMessage("REST is Awesome");

        // Send Test Message to
        Exchange responseExchange = template.send("direct:post", new Processor() {
            public void process(Exchange exchange) {
                Message in = exchange.getIn();
                in.setHeader("authUsername", "admin");
                in.setHeader("authPassword", "admin");
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
