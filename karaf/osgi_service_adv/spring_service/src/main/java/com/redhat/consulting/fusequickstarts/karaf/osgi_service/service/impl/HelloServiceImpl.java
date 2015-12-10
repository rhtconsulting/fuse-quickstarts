package com.redhat.consulting.fusequickstarts.karaf.osgi_service.service.impl;

import com.redhat.consulting.fusequickstarts.karaf.osgi_service.service.api.IHelloService;

public class HelloServiceImpl implements IHelloService {
    
    private String message;
    
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
