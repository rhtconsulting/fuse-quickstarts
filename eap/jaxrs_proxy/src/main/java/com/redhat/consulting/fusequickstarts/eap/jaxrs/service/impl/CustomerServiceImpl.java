package com.redhat.consulting.fusequickstarts.eap.jaxrs.service.impl;

import com.redhat.consulting.fusequickstarts.eap.jaxrs.service.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.cdi.ContextName;

public class CustomerServiceImpl implements CustomerService {
    @Inject
    @ContextName("jaxrs-proxy")
    private CamelContext context;

    @Produce(uri = "direct:customerEndpoints")
    private CustomerService customerServiceProxy;

    public Response getCustomers() {
        return customerServiceProxy.getCustomers();
    }

    public Response getCustomer(String id) {
        return customerServiceProxy.getCustomer(id);
    }

}
