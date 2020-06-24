package com.redhat.consulting.fusequickstarts.springboot.soapconsumer.contractfirst;

import com.redhat.consulting.jaxws.customerorderexample.model.*;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"camel.springboot.java-routes-include-pattern=**/CustomerOrderExampleRoute"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CustomerOrderExampleRouteIntegrationTest {

    private static final ObjectFactory CUSTOMER_ORDER_FACTORY = new ObjectFactory();

    @LocalServerPort
    private int localServerPort;

    private WebServiceTemplate webServiceTemplate;

    @Before
    public void setUp() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CUSTOMER_ORDER_FACTORY.getClass().getPackage().getName());
        marshaller.afterPropertiesSet();

        webServiceTemplate = new WebServiceTemplateBuilder()
                .setMarshaller(marshaller)
                .setUnmarshaller(marshaller)
                .setDefaultUri(UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host("localhost")
                        .port(localServerPort)
                        .pathSegment("soap")
                        .pathSegment("customerOrderExample")
                        .toUriString())
                .build();
        webServiceTemplate.afterPropertiesSet();
    }

    @Test
    public void testPlaceOrder_Success() {
        Date beginDate = new Date();

        PlaceOrder placeOrder = CUSTOMER_ORDER_FACTORY.createPlaceOrder();
        placeOrder.setItem("test item");
        placeOrder.setQuantity(BigInteger.TEN);

        PlaceOrderResult placeOrderResult = (PlaceOrderResult) webServiceTemplate.marshalSendAndReceive(placeOrder);

        assertThat("response should return a result", placeOrderResult, notNullValue());
        assertThat("response should echo the item back", placeOrderResult.getItem(), equalTo("test item"));
        assertThat("response should echo the quantity back", placeOrderResult.getQuantity(), equalTo(BigInteger.TEN));
        assertThat("response should indicate a time after the begin time", DateUtils.truncatedCompareTo(beginDate, placeOrderResult.getOrderTime(), Calendar.SECOND), lessThanOrEqualTo(0));
        assertThat("response should indicate a time before the current time ", DateUtils.truncatedCompareTo(placeOrderResult.getOrderTime(), new Date(), Calendar.SECOND), lessThanOrEqualTo(0));
        assertThat("response should indicate a valid UUID for the order ID", placeOrderResult.getOrderId(), notNullValue());
    }

    @Test
    public void testGetOrder_Success() {
        Date beginDate = new Date();

        GetOrder getOrder = CUSTOMER_ORDER_FACTORY.createGetOrder();
        getOrder.setOrderId(UUID.randomUUID());

        GetOrderResult placeOrderResult = (GetOrderResult) webServiceTemplate.marshalSendAndReceive(getOrder);

        assertThat("response should return a result", placeOrderResult, notNullValue());
        assertThat("response should return the expected item", placeOrderResult.getItem(), equalTo("Widget"));
        assertThat("response should return the expected quantity", placeOrderResult.getQuantity(), equalTo(BigInteger.valueOf(5)));
        assertThat("response should indicate a time after the begin time", DateUtils.truncatedCompareTo(beginDate, placeOrderResult.getOrderTime(), Calendar.SECOND), lessThanOrEqualTo(0));
        assertThat("response should indicate a time before the current time ", DateUtils.truncatedCompareTo(placeOrderResult.getOrderTime(), new Date(), Calendar.SECOND), lessThanOrEqualTo(0));
        assertThat("response should echo the order ID back", placeOrderResult.getOrderId(), equalTo(getOrder.getOrderId()));
    }

}
