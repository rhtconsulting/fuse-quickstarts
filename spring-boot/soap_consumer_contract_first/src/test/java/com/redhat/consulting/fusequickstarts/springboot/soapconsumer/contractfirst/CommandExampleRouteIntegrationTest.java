package com.redhat.consulting.fusequickstarts.springboot.soapconsumer.contractfirst;

import com.redhat.consulting.jaxws.commandexample.model.ExecuteNow;
import com.redhat.consulting.jaxws.commandexample.model.ObjectFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"camel.springboot.java-routes-include-pattern=**/CommandExampleRoute"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@MockEndpoints("stub:direct:execute-command-now")
public class CommandExampleRouteIntegrationTest {

    private static final ObjectFactory COMMAND_FACTORY = new ObjectFactory();

    @EndpointInject(uri = "mock:stub:direct:execute-command-now")
    MockEndpoint mockEndpoint;

    @LocalServerPort
    private int localServerPort;
    private WebServiceTemplate webServiceTemplate;

    @Before
    public void setUp() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(COMMAND_FACTORY.getClass().getPackage().getName());
        marshaller.afterPropertiesSet();

        webServiceTemplate = new WebServiceTemplateBuilder()
                .setMarshaller(marshaller)
                .setUnmarshaller(marshaller)
                .setDefaultUri(UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host("localhost")
                        .port(localServerPort)
                        .pathSegment("soap")
                        .pathSegment("commandExample")
                        .toUriString())
                .build();
        webServiceTemplate.afterPropertiesSet();
    }

    @Test
    public void testExecuteNow_Success() throws Exception {
        // Even with no response to this one-way SOAP, the target endpoint should still be invoked
        mockEndpoint.expectedMessageCount(1);

        ExecuteNow executeNow = COMMAND_FACTORY.createExecuteNow();

        Object executeNowResult = webServiceTemplate.marshalSendAndReceive(executeNow);

        assertThat("there should be no response", executeNowResult, nullValue());

        mockEndpoint.assertIsSatisfied();
    }

}
