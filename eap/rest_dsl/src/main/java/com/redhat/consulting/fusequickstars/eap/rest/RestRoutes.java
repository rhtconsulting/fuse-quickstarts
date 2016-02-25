package com.redhat.consulting.fusequickstars.eap.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.model.rest.RestBindingMode;

/**
 *  @author lberetta
 *
 */

@ContextName("rest-dsl")
public class RestRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/user")
                .get().produces("application/json").to("direct:getUsers")
                .get("/{id}").produces("application/json").to("direct:getUser")
                .post().consumes("application/json").type(User.class).to("direct:createUser")
                .put().type(User.class).to("direct:updateUser")
                .delete("/{id}").to("direct:deleteUser");

        from("direct:getUsers")
                .log("GET /user request received!")
                .to("bean:usersBean?method=getUsers");

        from("direct:getUser")
                .log("GET /user/${header.id} request received!")
                .setBody(simple("${header.id}"))
                .to("bean:usersBean?method=getUser");

        from("direct:createUser")
                .log("POST /user/ request received!")
                .to("bean:usersBean?method=createUser");

        from("direct:updateUser")
                .log("PUT /user/ request received!")
                .to("bean:usersBean?method=updateUser");

        from("direct:deleteUser")
                .log("DELETE /user/${header.id} request received!")
                .setBody(simple("${header.id}"))
                .to("bean:usersBean?method=deleteUser");
    }
}

