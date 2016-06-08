package com.redhat.consulting.fusequickstarts.eap.jaxrs.service.route;

import com.redhat.consulting.fusequickstarts.eap.jaxrs.repository.CustomerRepository;
import com.redhat.consulting.fusequickstarts.eap.jaxrs.util.BeanInvocationProcessor;
import com.redhat.consulting.fusequickstarts.eap.jaxrs.util.ResponseBuilder;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/*
 * The Annotations below are required for EAP to find and start the Camel Route.
 */
@Startup
@ApplicationScoped
@ContextName("jaxrs-proxy")
public class JAXRSRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // Processor to Pull Method Name from Incoming Exchange and Set it on Header
        Processor methodNameProcessor = new BeanInvocationProcessor();

        // Customer Repo with Dummy Data
        CustomerRepository customerRepo = new CustomerRepository();
        
        // Utility for Building JAX-RS Responses
        ResponseBuilder responseBuilder = new ResponseBuilder();
        
        // Receives Request from Endpoint
        from("direct:customerEndpoints")
            .routeId("customerEndpoint")
            .log("Received REST Call to Customer Endpoint")
            .process(methodNameProcessor)
            .choice()
                .when(header(BeanInvocationProcessor.HEADER_METHOD_NAME).isEqualTo("getCustomers"))
                    .to("direct:getCustomers")
                .when(header(BeanInvocationProcessor.HEADER_METHOD_NAME).isEqualTo("getCustomer"))
                    .to("direct:getCustomer")
                .otherwise()
                    .setBody().constant(Response.status(Status.NOT_FOUND).build())
                    .log("Invalid Operation Request ${headers."+BeanInvocationProcessor.HEADER_METHOD_NAME+"}")
                .end();
                  
        // Process Get All Customers Request
        from("direct:getCustomers")
            .routeId("getCustomers")
            .log("Getting Customers")
            .bean(customerRepo,"getCustomers")
            .bean(responseBuilder);
        
        // Process Get a Specific Customer Request
        from("direct:getCustomer")
            .routeId("getCustomer/id")
            .log("Getting Customer ${headers."+BeanInvocationProcessor.HEADER_METHOD_ARGS+"[0]}")
            .bean(customerRepo,"getCustomer(${headers."+BeanInvocationProcessor.HEADER_METHOD_ARGS+"[0]})")
            .bean(responseBuilder);

    }
}
