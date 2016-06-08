package com.redhat.consulting.fusequickstarts.eap.jaxrs.util;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Headers;

/**
 * Builds JAX-RS Responses from the Camel Exchange Data
 */
public class ResponseBuilder {
    
    @Handler
    public Response buildResponse(@Body Object body, @Headers Map<String, Object> headers){
        Integer responseCode = 200;
        if(headers.containsKey(Exchange.HTTP_RESPONSE_CODE)){
            responseCode = Integer.valueOf(headers.get(Exchange.HTTP_RESPONSE_CODE).toString());
        }
        
        return Response.ok(body).status(responseCode).build();
    }

}
