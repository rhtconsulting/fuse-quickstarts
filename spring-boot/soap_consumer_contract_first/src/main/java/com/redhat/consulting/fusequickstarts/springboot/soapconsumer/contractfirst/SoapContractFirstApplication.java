package com.redhat.consulting.fusequickstarts.springboot.soapconsumer.contractfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:config/cxf-endpoint-beans.xml"})
public class SoapContractFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapContractFirstApplication.class, args);
    }

}
