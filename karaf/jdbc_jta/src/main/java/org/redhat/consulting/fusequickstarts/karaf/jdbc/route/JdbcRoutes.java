package org.redhat.consulting.fusequickstarts.karaf.jdbc.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class JdbcRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {

     onException(java.lang.Exception.class)
             .setBody(simple("${header.key} - ${header.value}"))
             .to("file:ERRORS")
             .maximumRedeliveries(1)
             .handled(true)
             .markRollbackOnly();


    // following route should be persisted
    from("timer://jdbcTimer?fixedRate=true&period=5000&repeatCount=1")
            .transacted("propagation_required")
            .setHeader("key", constant("ASF"))
            .setHeader("value", constant("this should be PERSISTED"))
            .setBody(simple("INSERT INTO my_table (key, value) VALUES ( :?key, :?value )"))
            .to("jdbc:datasource?useHeadersAsParameters=true&resetAutoCommit=false");

    // following route should be rollbacked
    from("timer://jdbcTimer?fixedRate=true&period=5000&repeatCount=1")
            .transacted("propagation_required")
            .setHeader("key", constant("ASF"))
            .setHeader("value", constant("this should be ROLLBACKED"))
            .setBody(simple("INSERT INTO my_table (key, value) VALUES ( :?key, :?value )"))
            .to("jdbc:datasource?useHeadersAsParameters=true&resetAutoCommit=false")
            .rollback("manually_rollbacked");

    // also this 2 writes route route should be rollbacked
    from("timer://jdbcTimer?fixedRate=true&period=5000&repeatCount=1")
            .transacted("propagation_required")
            .setHeader("key", constant("ASF"))
            .setHeader("value", constant("[1/2] this should be ROLLBACKED"))
            .setBody(simple("INSERT INTO my_table (key, value) VALUES ( :?key, :?value )"))
            .to("jdbc:datasource?useHeadersAsParameters=true&resetAutoCommit=false")
            .setHeader("key", constant("ASF"))
            .setHeader("value", constant("[2/2] this should be ROLLBACKED"))
            .setBody(simple("INSERT INTO my_table (key, value) VALUES ( :?key, :?value )"))
            .to("jdbc:datasource?useHeadersAsParameters=true&resetAutoCommit=false")
            .bean(ExceptionThrower.class)
    ;

  }

}
