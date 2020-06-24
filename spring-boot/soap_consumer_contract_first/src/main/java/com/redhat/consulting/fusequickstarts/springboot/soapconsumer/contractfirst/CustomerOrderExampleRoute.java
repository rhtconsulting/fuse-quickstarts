package com.redhat.consulting.fusequickstarts.springboot.soapconsumer.contractfirst;

import com.redhat.consulting.jaxws.customerorderexample.CustomerOrderExampleService;
import com.redhat.consulting.jaxws.customerorderexample.model.GetOrderResult;
import com.redhat.consulting.jaxws.customerorderexample.model.ObjectFactory;
import com.redhat.consulting.jaxws.customerorderexample.model.PlaceOrder;
import com.redhat.consulting.jaxws.customerorderexample.model.PlaceOrderResult;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CustomerOrderExampleRoute extends RouteBuilder {

    private static final ObjectFactory CUSTOMER_ORDER_FACTORY = new ObjectFactory();

    @Override
    public void configure() {
        //@formatter:off
        from("cxf:bean:customerOrderExampleWebService?dataFormat=POJO")
            .routeId("customerOrderExampleEndpoint")
            .routeGroup("customerOrder")
            .routeDescription("A route to handle incoming requests via the customer-order-example SOAP WebService")
            .transform()
                .simple("${body[0]}")
            .choice()
                .when(simple("${header.operationName} == 'placeOrder'"))
                    .to("direct:customerOrderExamplePlaceOrder")
                    .endChoice()
                .when(simple("${header.operationName} == 'getOrder'"))
                    .to("direct:customerOrderExampleGetOrder")
                    .endChoice()
                .otherwise()
                    .throwException(UnsupportedOperationException.class, "Unsupported SOAP operation: ${header.operationName}")
                    .endChoice()
            .end()
            .removeHeaders("*", Exchange.BREADCRUMB_ID);

        // Generate response using inline processor
        from("direct:customerOrderExamplePlaceOrder")
            .routeId("customerOrderExamplePlaceOrder")
            .routeGroup("customerOrder")
            .routeDescription("A route to handle the 'placeOrder' operation from the customer-order-example SOAP WebService")
            .process(exchange -> {
                PlaceOrder request = exchange.getMessage().getBody(PlaceOrder.class);
                PlaceOrderResult result = CUSTOMER_ORDER_FACTORY.createPlaceOrderResult();

                result.setOrderId(UUID.randomUUID());
                result.setOrderTime(new Date());
                result.setItem(request.getItem());
                result.setQuantity(request.getQuantity());

                exchange.getMessage().setBody(result);
            });

        // Generate response directly as XML (making up an order time of "right now" with other sample order data)
        from("direct:customerOrderExampleGetOrder")
            .routeId("customerOrderExampleGetOrder")
            .routeGroup("customerOrder")
            .routeDescription("A route to handle the 'getOrder' operation from the customer-order-example SOAP WebService")
            .transform()
                .simple("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<coes:getOrderResult xmlns:coes=\"" + CustomerOrderExampleService.SERVICE.getNamespaceURI() + "\">\n" +
                        "  <orderId>${body.orderId}</orderId>\n" +
                        "  <orderTime>${date:now:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern() + "}</orderTime>\n" +
                        "  <item>Widget</item>\n" +
                        "  <quantity>5</quantity>\n" +
                        "</coes:getOrderResult>")
            .unmarshal()
                .jaxb(GetOrderResult.class.getPackage().getName());
        //@formatter:on
    }

}
