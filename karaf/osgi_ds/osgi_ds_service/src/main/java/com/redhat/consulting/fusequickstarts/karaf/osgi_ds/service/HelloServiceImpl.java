package com.redhat.consulting.fusequickstarts.karaf.osgi_ds.service;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

import com.redhat.consulting.fusequickstarts.karaf.osgi_ds.api.IHelloService;

@Component(name="com.redhat.consulting.fusequickstarts.karaf.osgi_ds.service",
		configurationPolicy=ConfigurationPolicy.OPTIONAL, 
		configurationPid="com.redhat.consulting.fusequickstarts.karaf.osgi_ds.service")
public class HelloServiceImpl implements IHelloService {
    
	private static final String DEFAULT_MESSAGE= "This is a Default Message";
	
    private String message;
    
    @Activate
    public void start(final Map<String, ?> properties) {
        if (properties.containsKey("message")) {
            message = (String)properties.get("message");
        } else {
            message = DEFAULT_MESSAGE;
        }
    }

    @Deactivate
    public void stop() {
        // Nothing to do here
    }
    
    public String sayMessage(){
        return "I Have a Message: " + message;
    }
    
    public String sayHello(String pName) {
        return "Hello " + pName + ", How Are you?";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }    
    
    

}
