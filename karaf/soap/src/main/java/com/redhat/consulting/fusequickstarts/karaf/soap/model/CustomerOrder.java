package com.redhat.consulting.fusequickstarts.karaf.soap.model;

/**
 * Basic Customer Order Object.
 */
public class CustomerOrder {

    private String item;
    private Integer quantity;
    private String orderId;

    /**
     * Get the item.
     * 
     * @return the item
     */
    public String getItem() {
        return this.item;
    }

    /**
     * Set the item.
     * 
     * @param item
     *            the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Get the quantity.
     * 
     * @return the quantity
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Set the quantity.
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the orderId.
     * 
     * @return the orderId
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Set the orderId.
     * 
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
