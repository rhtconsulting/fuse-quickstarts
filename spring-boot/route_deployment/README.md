# Camel on Spring Boot - Basic Route Deployment #
This Quick Start demonstrates basic route deployment using different styles of deployment

## Requirements ##
 * Red Hat Fuse 7.6.0
 * Maven 3.0 or greater (http://maven.apache.org)
 * Java 8

## Notes About Quick Start Implementation ##

Routes are deployed using the following methods:

 * From [route-one.xml](src/main/resources/camel-routes/route-one.xml) in [classpath:camel-routes/](src/main/resources/camel-routes) (configurable in [application.yaml](src/main/resources/config/application.yaml))
 * As a Spring `@Component` bean extending `RouteBuilder` in [RouteTwo.java](src/main/java/com/redhat/consulting/fusequickstarts/springboot/routedeployment/RouteTwo.java)

## Building ##
To build the project, run the command:

    mvn clean verify

This will compile, package, and test a .JAR file.

## Automated Testing ##
Integration tests are included in this project and are run during the appropriate phase of
the `mvn verify` command; namely the `integration-test` lifecycle phase. Unit tests would also
be run, but none are included in this Quick Start.

Because the tests rely upon starting the full Spring context and Camel context, they are marked
as integration tests.

## Running ##
To run the project, follow any standard methods of running a Spring Boot project.
For example:

    mvn spring-boot:run

This will launch the application. Once the application is ready, you should beginn to see log messages similar to the following:

    23:33:00.892 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: routeTwo started and consuming from: timer://routeTwoTimer
    23:33:00.892 [main] INFO  o.a.camel.spring.SpringCamelContext - Route: routeOne started and consuming from: timer://routeOneTimer?fixedRate=true&period=5s
    23:33:00.894 [main] INFO  o.a.camel.spring.SpringCamelContext - Total 2 routes, of which 2 are started
    23:33:00.894 [main] INFO  o.a.camel.spring.SpringCamelContext - Apache Camel 2.23.2.fuse-760030-redhat-00001 (CamelContext: Basic Route Deployment) started in 0.165 seconds
    23:33:00.897 [main] INFO  c.r.c.f.s.r.RouteDeploymentApplication - Started RouteDeploymentApplication in 2.009 seconds (JVM running for 4.693)
    23:33:01.900 [Camel (Basic Route Deployment) thread #3 - timer://routeOneTimer] INFO  routeOne - Timer triggered in routeOne (from route-one.xml)
    23:33:01.900 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
    23:33:02.894 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
    23:33:03.895 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
    23:33:04.895 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
    23:33:05.895 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
    23:33:06.894 [Camel (Basic Route Deployment) thread #3 - timer://routeOneTimer] INFO  routeOne - Timer triggered in routeOne (from route-one.xml)
    23:33:06.895 [Camel (Basic Route Deployment) thread #2 - timer://routeTwoTimer] INFO  routeTwo - Timer triggered in routeTwo (from RouteTwo.java)
