# Camel on Spring Boot - REST Consumer (using REST Java DSL) #
This Quick Start demonstrates a REST consumer implemented using Camel's REST Java DSL.

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

    19:13:37.860 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: listUsers started and consuming from: servlet:/user?httpMethodRestrict=GET
    19:13:37.861 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: getUser started and consuming from: servlet:/user/%7Bid%7D?httpMethodRestrict=GET
    19:13:37.861 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: createUser started and consuming from: servlet:/user?httpMethodRestrict=POST
    19:13:37.862 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: updateUser started and consuming from: servlet:/user?httpMethodRestrict=PUT
    19:13:37.862 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: deleteUser started and consuming from: servlet:/user/%7Bid%7D?httpMethodRestrict=DELETE
    19:13:37.862 [main] INFO  o.a.camel.spring.SpringCamelContext - Total 5 routes, of which 5 are started
    ...
    ...
    ...
    19:13:37.915 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8080 (http) with context path ''
    19:13:37.969 [main] INFO  io.undertow.servlet - Initializing Spring embedded WebApplicationContext
    19:13:37.969 [main] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 52 ms
    19:13:37.989 [main] INFO  o.s.b.a.e.web.EndpointLinksResolver - Exposing 4 endpoint(s) beneath base path '/actuator'
    19:13:38.036 [main] INFO  o.s.b.w.e.u.UndertowServletWebServer - Undertow started on port(s) 8081 (http) with context path ''
    19:13:38.039 [main] INFO  c.r.c.f.s.r.restjavadsl.Application - Started Application in 2.708 seconds (JVM running for 3.072)

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
