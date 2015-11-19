package com.redhat.consulting.fusequickstarts.karaf.soap_client.route.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.SoapJaxbDataFormat;

public class CxfClientRoute extends RouteBuilder{
    
    private static final String ENDPOINT = "http://www.webservicex.net/CurrencyConvertor.asmx";
    private static final String WSDL = "http://www.webservicex.net/CurrencyConvertor.asmx?WSDL";
    private static final String SERVICE_NAME = "{http://www.webserviceX.NET/}CurrencyConvertor";
    private static final String ENDPOINT_NAME = "{http://www.webserviceX.NET/}CurrencyConvertorSoap";

    @Override
    public void configure() throws Exception {
        
        SoapJaxbDataFormat jaxbDataFormat = new SoapJaxbDataFormat("net.webservicex");
        
        // Process the Requests coming in to the Endpoint
        from("timer://soapClientTimer?fixedRate=true&period=5000&repeatCount=2")
            .setHeader("fromCurrency",simple("USD"))
            .setHeader("toCurrency",simple("EUR"))
            .bean(CurrencyConversionRequestFactory.class, "createNewRequest")
            .marshal(jaxbDataFormat)
            .log("Making SOAP Call: ${body}")
            //.to("cxf:bean:cxfClientEndpoint?dataFormat=MESSAGE")
            .to("cxf://" + ENDPOINT + "?dataFormat=MESSAGE&wsdlURL=" + WSDL + "&serviceName="
                   + SERVICE_NAME + "&portName=" + ENDPOINT_NAME)
            .log("SOAP Response: ${body}");
        
    }

}
