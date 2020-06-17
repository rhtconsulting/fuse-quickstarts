package com.redhat.consulting.fusequickstarts.springboot.restconsumer.restjavadsl;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UserManagementRoute extends RouteBuilder {
    @Override
    public void configure() {
        //@formatter:off
        rest("/user")
            .bindingMode(RestBindingMode.json)
            .get()
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                    .routeId("listUsers")
                    .routeGroup("userManagement")
                    .log("GET /user/ request received!")
                    .bean("userService", "getUsers")
                .endRest()
            .get("/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                    .routeId("getUser")
                    .routeGroup("userManagement")
                    .log("GET /user/${header.id} request received!")
                    .transform()
                        .simple("${header.id}")
                    .bean("userService", "getUser")
                .endRest()
            .post()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .type(User.class)
                .route()
                    .routeId("createUser")
                    .routeGroup("userManagement")
                    .log("POST /user/ request received!")
                    .bean("userService", "createUser")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE)
                        .constant(HttpStatus.CREATED.value())
                .endRest()
            .put()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(User.class)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                    .routeId("updateUser")
                    .routeGroup("userManagement")
                    .log("PUT /user/ request received!")
                    .bean("userService", "updateUser")
                .endRest()
            .delete("/{id}")
                .route()
                    .routeId("deleteUser")
                    .routeGroup("userManagement")
                    .log("DELETE /user/${header.id} request received!")
                    .transform()
                        .simple("${header.id}")
                    .bean("userService", "deleteUser")
                .endRest();
        //@formatter:on
    }
}
