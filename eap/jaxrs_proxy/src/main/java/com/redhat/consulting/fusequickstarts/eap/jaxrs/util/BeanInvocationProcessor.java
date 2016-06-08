package com.redhat.consulting.fusequickstarts.eap.jaxrs.util;

import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.bean.BeanInvocation;

/**
 * Custom Processor that Removes the Invoked Method Name and Arguments and sets
 * them on the Exchange as a Header.
 */
public class BeanInvocationProcessor implements Processor {

    /**
     * Name of Method Invoked as a String.
     */
    public static String HEADER_METHOD_NAME = "ServiceMethodName";

    /**
     * Arguments passed into the Method as an Object[].
     */
    public static String HEADER_METHOD_ARGS = "ServiceMethodArguments";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
     */
    public void process(Exchange exchange) throws Exception {
        BeanInvocation beanInvocation = exchange.getIn().getBody(BeanInvocation.class);

        String methodName = beanInvocation.getMethod().getName();
        Object[] arguments = beanInvocation.getArgs();

        exchange.getIn().setHeader(HEADER_METHOD_NAME, methodName);
        exchange.getIn().setHeader(HEADER_METHOD_ARGS, arguments);
    }

}
