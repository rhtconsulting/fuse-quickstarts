package org.redhat.consulting.fusequickstarts.karaf.jpa.route;

import org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person;
import org.apache.camel.builder.RouteBuilder;
import org.redhat.consulting.fusequickstarts.karaf.jpa.processor.ExceptionProcessor;

public class JpaRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("timer://jpaTimer?fixedRate=true&period=5000")
      .routeId("=== expect_success ===")
      .transacted()
      .log("Generating New Person")
      .setBody(constant(new Person(Person.generateName())))
      .log("Saving Person")
      .to("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
      .log("Saved Person: ${body}!");
      //.beanRef("dummy","crash");

      from("timer://jpaTimer?fixedRate=true&period=5000")
        .routeId("=== expected_failure ===")
        .transacted()
        .log("Generating New Person")
        .setBody(constant(new Person("this_should_be_rolled_back_" + Person.generateName())))
        .log("Saving Person")
        .to("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
        .log("Saved Person: ${body}!")
        .beanRef("dummy","crash")
        .log("------------ Rolled back");


//    from("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
//      .routeId("jpaToLog")
//      .log("Retrieved Person: ${body}");
  }

}
