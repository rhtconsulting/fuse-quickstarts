package com.redhat.consulting.fusequickstarts.karaf.osgi_service.service;

public class HelloServiceImpl implements IHelloService {
    
    public String sayHello(String pName) {
        return "Hello " + pName + ", How Are you?";
    }    

}
