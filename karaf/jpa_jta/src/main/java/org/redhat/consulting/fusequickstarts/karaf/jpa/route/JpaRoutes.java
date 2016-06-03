package org.redhat.consulting.fusequickstarts.karaf.jpa.route;

import org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person;
import org.apache.camel.builder.RouteBuilder;
import org.redhat.consulting.fusequickstarts.karaf.jpa.processor.ExceptionProcessor;

public class JpaRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("timer://jpaTimer?fixedRate=true&period=5000&repeatCount=1")
        .routeId("Success Route")
        .transacted()
        .log(">>> Generating New Person")
        .setBody(constant(new Person(1, Person.generateName())))
        .log(">>> Saving Person")
        .to("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
        .log(">>> Saved Person: ${body}!");

    from("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
        .routeId("JPA Poll")
        .log("Retrieved Person: ${body}");
    
    /*from("timer://jpaTimer?fixedRate=true&period=5000&repeatCount=1")
        .routeId("Failure Route")
        .transacted()
        .log(">>> Generating New Person")
        .setBody(constant(new Person(2, "Rolled_Back_" + Person.generateName())))
        .log(">>> Saving Person")
        .to("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
        .log(">>> Saved Person: ${body}!")
        .beanRef("dummy","crash")
        .log(">>> Rolled back");*/

  }

}
