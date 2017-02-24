
Camel-CXF SOAP Client Example
====================================
This project creates a simple Camel route that calls an external SOAP Web Service. It uses wsdl2java to generate the required classes from the WSDL.

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------

To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Deploying to JBoss Fuse
-----------------------

To start up Fuse Karaf browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2  repository in fuse in the `fuse/etc/org.ops4j.pax.url.mvn.cfg` file.  Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/soap_client/6.3/soap_client-6.3.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/soap_client/6.3

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Testing
--------

Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

    2015-11-19 15:12:00,841 | INFO  | /soapClientTimer | route9   | ?     ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Making SOAP Call: <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <ns2:Envelope xmlns:ns2="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns3="http://www.webserviceX.NET/">
        <ns2:Body>
            <ns3:ConversionRate>
                <ns3:FromCurrency>USD</ns3:FromCurrency>
                <ns3:ToCurrency>EUR</ns3:ToCurrency>
            </ns3:ConversionRate>
        </ns2:Body>
    </ns2:Envelope>

    2015-11-19 15:12:01,081 | INFO  | ault-workqueue-2 | route9   | ?  v  ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | SOAP Response: <?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><ConversionRateResponse xmlns="http://www.webserviceX.NET/"><ConversionRateResult>0.9316</ConversionRateResult></ConversionRateResponse></soap:Body></soap:Envelope>
