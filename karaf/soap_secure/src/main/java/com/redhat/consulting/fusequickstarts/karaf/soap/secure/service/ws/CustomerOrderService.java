package com.redhat.consulting.fusequickstarts.karaf.soap.secure.service.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.redhat.consulting.fusequickstarts.karaf.soap.secure.model.CustomerOrder;
import com.redhat.consulting.fusequickstarts.karaf.soap.secure.model.CustomerOrderResponse;

/**
 * Customer Order Service
 */
@WebService(targetNamespace = "http://consulting.redhat.com/wsdl/CustomerOrder/", name = "CustomerOrder", serviceName = "CustomerOrderService", portName = "SOAP")
public interface CustomerOrderService {

    /**
     * Place a Customer Order
     * 
     * @param order
     * @return
     */
    public CustomerOrderResponse placeOrder(@WebParam(name = "customerOrder") CustomerOrder order);

    /**
     * Get an Order
     * 
     * @param orderId
     * @return
     */
    public CustomerOrder getOrder(@WebParam(name = "orderId") String orderId);
}
