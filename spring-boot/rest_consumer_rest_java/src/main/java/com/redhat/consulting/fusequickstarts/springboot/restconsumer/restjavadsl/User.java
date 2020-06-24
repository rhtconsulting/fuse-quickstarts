package com.redhat.consulting.fusequickstarts.springboot.restconsumer.restjavadsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private final Integer id;
    private final String username;

    @JsonCreator
    public User(@JsonProperty("id") Integer id,
                @JsonProperty("username") String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
