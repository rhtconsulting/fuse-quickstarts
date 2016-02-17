package org.redhat.consulting.fusequickstarts.karaf.jpa.route;

import org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person;
import org.apache.camel.builder.RouteBuilder;

public class JpaRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://jpaTimer?fixedRate=true&period=2000").routeId("timerToJpa")
        .log("Generating New Person")
        .setBody(constant(new Person(Person.generateName())))
        .log("Saving Person")
        .to("jpa://org.redhat.consulting.fusequickstarts.karaf.jpa.model.Person")
        .log("Saved Person: ${body}");
    }

}
