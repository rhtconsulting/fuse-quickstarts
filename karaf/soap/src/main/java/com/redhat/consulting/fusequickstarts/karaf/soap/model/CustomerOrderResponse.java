package com.redhat.consulting.fusequickstarts.karaf.soap.model;

/**
 * Response Class for Customer Orders.
 *
 */
public class CustomerOrderResponse {

    private CustomerOrder order;
    private boolean completed;

    /**
     * Get the order.
     * 
     * @return the order
     */
    public CustomerOrder getOrder() {
        return this.order;
    }

    /**
     * Set the order.
     * 
     * @param order
     *            the order to set
     */
    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    /**
     * Get the completed.
     * 
     * @return the completed
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Set the completed.
     * 
     * @param completed
     *            the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
