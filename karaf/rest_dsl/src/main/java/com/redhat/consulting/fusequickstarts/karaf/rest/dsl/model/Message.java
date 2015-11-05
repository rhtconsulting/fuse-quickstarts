package com.redhat.consulting.fusequickstarts.karaf.rest.dsl.model;

/**
 * Simple Message POJO for use with REST DSL.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class Message {

    private String message;
    private String to;
    private String from;
    
    /**
     * Get the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }
    /**
     * Set the message.
     * 
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Get the to.
     * 
     * @return the to
     */
    public String getTo() {
        return this.to;
    }
    /**
     * Set the to.
     * 
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    /**
     * Get the from.
     * 
     * @return the from
     */
    public String getFrom() {
        return this.from;
    }
    /**
     * Set the from.
     * 
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }
}
