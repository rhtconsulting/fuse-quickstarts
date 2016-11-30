package com.redhat.consulting.fusequickstarts.eap.jms.mdb;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.ResourceAdapter;

/*
 * This MDB uses a producer template to send messages from the queue to the consumer end point.
 */

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue1"),
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageDrivenBean implements MessageListener {
	
	Logger logger = Logger.getLogger(MessageDrivenBean.class.getName());;
	CamelContext context;
	ProducerTemplate template;
	
	@PostConstruct
	public void initialize() throws NamingException{		
		InitialContext ic = new InitialContext();
		context = (CamelContext) ic.lookup("java:jboss/camel/context/jms-mdb");
		template = context.createProducerTemplate();
	}

	public void onMessage(Message message) {		
		try {
			String text = ((TextMessage) message).getText();						
			logger.info("Message rec'd: " + text);
						
			template.sendBody("direct:consumer",message);

		} catch (JMSException e) {
			throw new EJBException("Error in JMS operation", e);
		}
	}
		
}