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

    22:48:04.250 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8080 (http) with context path ''
    22:48:04.366 [main] INFO  io.undertow.servlet - Initializing Spring embedded WebApplicationContext
    22:48:04.366 [main] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 111 ms
    22:48:04.406 [main] INFO  o.s.b.a.e.web.EndpointLinksResolver - Exposing 4 endpoint(s) beneath base path '/actuator'
    22:48:04.468 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8081 (http) with context path ''
    22:48:04.471 [main] INFO  c.r.c.f.s.s.c.Application - Started Application in 5.686 seconds (JVM running for 9.193)

## Manual Testing ##
Manual testing may be performed while running the application. The list of deployed SOAP Web Services
can be found by viewing the CXF Web Service List:

 * http://localhost:8080/soap/

Additionally, Spring Boot Actuators are available for listing information about the Camel context and routes:

 * http://localhost:8081/actuator/info
 * http://localhost:8081/actuator/health
 * http://localhost:8081/actuator/camelroutes
 * http://localhost:8081/actuator/camelroutecontroller
