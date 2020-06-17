# Camel on Spring Boot - SOAP WebService Consumer (Contract First) #
This Quick Start deploys two SOAP WebServices handled by Camel routes while
running on Spring Boot.

## Requirements ##
 * Red Hat Fuse 7.6.0
 * Maven 3.0 or greater (http://maven.apache.org)
 * Java 8

## Building ##
To build the project, run the command:

    mvn clean verify

This will compile, package, and test a .JAR file.

## Automated Testing ##
Integration tests are included in this project and are run during the appropriate phase of
the `mvn verify` command; namely the `integration-test` lifecycle phase. Unit tests would also
be run, but none are included in this Quick Start.

Because the tests rely upon starting the full Spring context and Camel context, they are marked
as integration tests. Note that the tests will find and use a random listening port for listening
each time and are configured to test according to the configured port.

## Running ##
To run the project, follow any standard methods of running a Spring Boot project.
For example:

    mvn spring-boot:run

This will launch the application using an embedded Undertow server (as configured in
the `pom.xml`). Once the application is ready, you should see log messages similar to the following:

    19:10:35.487 [main] INFO  o.a.c.w.s.f.ReflectionServiceFactoryBean - Creating Service {http://consulting.redhat.com/wsdl/command-example-service.wsdl}CommandExampleService from WSDL: classpath:wsdl/command-example.wsdl
    19:10:35.608 [main] INFO  org.apache.cxf.endpoint.ServerImpl - Setting the server's publish address to be /commandExample
    19:10:35.667 [main] INFO  o.a.c.w.s.f.ReflectionServiceFactoryBean - Creating Service {http://consulting.redhat.com/wsdl/customer-order-example-service.wsdl}CustomerOrderExampleService from WSDL: classpath:wsdl/customer-order-example.wsdl
    19:10:35.703 [main] INFO  org.apache.cxf.endpoint.ServerImpl - Setting the server's publish address to be /customerOrderExample
    19:10:35.730 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: commandExampleEndpoint started and consuming from: cxf://bean:commandExampleWebService?dataFormat=POJO
    19:10:35.730 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: commandExampleExecuteNow started and consuming from: direct://commandExampleExecuteNow
    19:10:35.731 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: customerOrderExampleEndpoint started and consuming from: cxf://bean:customerOrderExampleWebService?dataFormat=POJO
    19:10:35.732 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: customerOrderExamplePlaceOrder started and consuming from: direct://customerOrderExamplePlaceOrder
    19:10:35.732 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: customerOrderExampleGetOrder started and consuming from: direct://customerOrderExampleGetOrder
    19:10:35.732 [main] INFO  o.a.camel.spring.SpringCamelContext - Total 5 routes, of which 5 are started
    ...
    ...
    ...
    19:10:35.785 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8080 (http) with context path ''
    19:10:35.836 [main] INFO  io.undertow.servlet - Initializing Spring embedded WebApplicationContext
    19:10:35.837 [main] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 49 ms
    19:10:35.856 [main] INFO  o.s.b.a.e.web.EndpointLinksResolver - Exposing 4 endpoint(s) beneath base path '/actuator'
    19:10:35.899 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8081 (http) with context path ''
    19:10:35.901 [main] INFO  c.r.c.f.s.s.c.Application - Started Application in 3.656 seconds (JVM running for 4.108)

## Manual Testing ##
Manual testing may be performed while running the application. The list of deployed SOAP Web Services
can be found by viewing the CXF Web Service List:

 * http://localhost:8080/soap/

Additionally, Spring Boot Actuators are available for listing information about the Camel context and routes:

 * http://localhost:8081/actuator/info
 * http://localhost:8081/actuator/health
 * http://localhost:8081/actuator/camelroutes
 * http://localhost:8081/actuator/camelroutecontroller
