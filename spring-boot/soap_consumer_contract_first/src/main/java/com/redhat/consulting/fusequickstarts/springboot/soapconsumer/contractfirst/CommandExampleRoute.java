package com.redhat.consulting.fusequickstarts.springboot.soapconsumer.contractfirst;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommandExampleRoute extends RouteBuilder {

    @Override
    public void configure() {
        //@formatter:off
        from("cxf:bean:commandExampleWebService?dataFormat=POJO")
            .routeId("commandExampleEndpoint")
            .routeGroup("commandExample")
            .routeDescription("A route to handle incoming requests via the command-example SOAP WebService")
            .transform()
                .simple("${body[0]}")
            .choice()
                .when(simple("${header.operationName} == 'executeNow'"))
                    .to("direct:commandExampleExecuteNow")
                    .endChoice()
                .otherwise()
                    .throwException(UnsupportedOperationException.class, "Unsupported SOAP operation: ${header.operationName}")
                    .endChoice()
            .end()
            .removeHeaders("*", Exchange.BREADCRUMB_ID);

        from("direct:commandExampleExecuteNow")
            .routeId("commandExampleExecuteNow")
            .routeGroup("commandExample")
            .routeDescription("A route to handle the 'executeNow' operation from the command-example SOAP WebService")
            .log("Execution initiated.")
            .to("stub:direct:execute-command-now");
        //@formatter:on
    }

}
