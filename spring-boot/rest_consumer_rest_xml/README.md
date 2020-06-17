# Camel on Spring Boot - REST Consumer (using REST XML) #
This Quick Start demonstrates a REST consumer implemented in REST XML files that are
automatically scanned by Camel in the Spring Boot context.

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

    18:59:58.994 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: listUsers started and consuming from: servlet:/user?httpMethodRestrict=GET
    18:59:58.995 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: getUser started and consuming from: servlet:/user/%7Bid%7D?httpMethodRestrict=GET
    18:59:58.995 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: createUser started and consuming from: servlet:/user?httpMethodRestrict=POST
    18:59:58.996 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: updateUser started and consuming from: servlet:/user?httpMethodRestrict=PUT
    18:59:58.997 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: deleteUser started and consuming from: servlet:/user/%7Bid%7D?httpMethodRestrict=DELETE
    18:59:58.997 [main] INFO  o.a.camel.spring.SpringCamelContext - Total 5 routes, of which 5 are started
    ...
    ...
    ...
    18:59:59.069 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8080 (http) with context path ''
    18:59:59.124 [main] INFO  io.undertow.servlet - Initializing Spring embedded WebApplicationContext
    18:59:59.125 [main] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 52 ms
    18:59:59.145 [main] INFO  o.s.b.a.e.web.EndpointLinksResolver - Exposing 4 endpoint(s) beneath base path '/actuator'
    18:59:59.192 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8081 (http) with context path ''
    18:59:59.194 [main] INFO  c.r.c.f.s.r.restxml.Application - Started Application in 4.67 seconds (JVM running for 5.382)

## Manual Testing ##
Manual testing may be performed while running the application. The following operations are supported:

 * GET http://localhost:8080/camel-rest/user/
 * GET http://localhost:8080/camel-rest/user/{id}
 * POST http://localhost:8080/camel-rest/user/
 * PUT http://localhost:8080/camel-rest/user/
 * DELETE http://localhost:8080/camel-rest/user/{id}

Additionally, Spring Boot Actuators are available for listing information about the Camel context and routes:

 * http://localhost:8081/actuator/info
 * http://localhost:8081/actuator/health
 * http://localhost:8081/actuator/camelroutes
 * http://localhost:8081/actuator/camelroutecontroller
