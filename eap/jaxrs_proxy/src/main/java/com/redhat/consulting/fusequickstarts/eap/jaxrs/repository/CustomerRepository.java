package com.redhat.consulting.fusequickstarts.eap.jaxrs.repository;

import com.redhat.consulting.fusequickstarts.eap.jaxrs.model.Customer;
import com.redhat.consulting.fusequickstarts.eap.jaxrs.util.BeanInvocationProcessor;

import java.util.LinkedList;
import java.util.List;

import org.apache.camel.Header;

public class CustomerRepository {

    // Dummy Customer Data
    List<Customer> customers = new LinkedList<Customer>();

    public CustomerRepository() {
        customers.add(new Customer("Bob", "Winston"));
        customers.add(new Customer("Jane", "Winston"));
        customers.add(new Customer("John", "Smith"));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(Integer id) {
        return customers.get(id);
    }
}
