package com.redhat.consulting.fusequickstarts.karaf.soap.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.redhat.consulting.fusequickstarts.karaf.soap.model.CustomerOrder;
import com.redhat.consulting.fusequickstarts.karaf.soap.model.CustomerOrderResponse;

public class CxfCodeFirstRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        // Process the Requests coming in to the Endpoint
        from("cxf:bean:customerOrderEndpoint")
        	.routeId("customerOrderEndpoint")
            .log("Got a SOAP Message: ${body}")
            .choice()
                .when(simple("${in.header.operationName} == 'placeOrder'"))
                    .to("direct:placeOrder")
                .when(simple("${in.header.operationName} == 'getOrder'"))
                    .to("direct:getOrder")
            .endChoice();
        
        // Processing Route for Place Order Operation
        from("direct:placeOrder")
        	//.routeId("placeOrder")
            .log("Executing Place Order Operation")
            .log("Order: ${body}")
            // Inline Processor to Create a Response Object
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    // Get Order from Body
                    CustomerOrder order = exchange.getIn().getBody(CustomerOrder.class);
                    
                    // Create Response
                    CustomerOrderResponse response = new CustomerOrderResponse();
                    response.setCompleted(true);
                    response.setOrder(order);
                    
                    // Set Response on Message Body
                    exchange.getOut().setBody(response);
                }
            });
        
        // Processing Route for Get Order Operation
        from("direct:getOrder")
        	//.routeId("getOrder")
            .log("Executing Get Order Operation")
            .log("Order: ${body}")
            // Inline Processor to Create a Response Object
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    // Get Order from Body
                    String orderId = exchange.getIn().getBody(String.class);
                    
                    // Create Response
                    CustomerOrder response = new CustomerOrder();
                    response.setOrderId(orderId);
                    response.setItem("Widget");
                    response.setQuantity(5);
                    
                    // Set Response on Message Body
                    exchange.getOut().setBody(response);
                }
            });
    }

}
