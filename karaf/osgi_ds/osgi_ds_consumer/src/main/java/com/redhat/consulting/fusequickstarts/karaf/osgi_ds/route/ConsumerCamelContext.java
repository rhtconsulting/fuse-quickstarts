package com.redhat.consulting.fusequickstarts.karaf.osgi_ds.route;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.consulting.fusequickstarts.karaf.osgi_ds.api.IHelloService;

/*
 * Registers a new Declarative Service component with a custom Camel Context. This allows for the consumed OSGi service
 * to be added to the Camel bean registry. A route is also defined where the it is triggered by a Timer to invoke
 * the Hello Service that is exposed from the other bundle.
 */
@Component(name="com.redhat.consulting.fusequickstarts.karaf.osgi_ds.route")
public class ConsumerCamelContext extends DefaultCamelContext {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConsumerCamelContext.class);

    private IHelloService helloService;

    
	@Activate
	public void activate() {
		LOGGER.info("Spinning up the Camel OSGi DS Consumer Route");

		try {
			this.setName("fusequickstart-osgids-consumer");
			
			SimpleRegistry registry = new SimpleRegistry();
			registry.put("helloService", helloService);
			this.setRegistry(registry);

			this.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
			        from("timer://dsTimer?fixedRate=true&period=5000")
		            .beanRef("helloService", "sayMessage")
		            .to("log:helloServiceLog")
		            .beanRef("helloService", "sayHello(Jim)")
		            .to("log:helloServiceLog");

				}
			});

			this.start();
			this.startAllRoutes();
			if (this.isStarted()) {
				LOGGER.info("CamelContext is Started");
			}

		} catch (Exception e) {
			throw new RuntimeCamelException(
					"Error Adding Route to the Camel Context", e);
		}
		LOGGER.info("Spinning up the Camel JMS Producer Route: SUCCESS");
	}

    
	@Deactivate
	public void deactivate() {
		LOGGER.info("Spinning down the Camel OSGi DS Consumer Route");
		try {
			this.stopRoute("fusequickstart-osgids-consumer");
			this.stop();
		} catch (Exception e) {
			LOGGER.error("TODO Auto-generated catch block: "
					+ e.getLocalizedMessage());
			throw new RuntimeCamelException(
					"Error Stopping Route", e);
		}
	}
	
	@Reference
	public synchronized void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}
	
	public synchronized void unsetHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}



}
