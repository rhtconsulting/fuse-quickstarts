package com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.resource;

import javax.validation.constraints.Pattern;
import javax.ws.rs.core.Response;

public class MyResourceImpl implements MyResource {

    @Override
    public Response getSampleUser(@Pattern(regexp = "^1[0-9]*$") String id) {
        //XXX: you should never reach here
        throw new UnsupportedOperationException("You should never reach here!");
    }
}
