Camel-CXF SOAP Secure (Code First) example
====================================
This project creates a simple Customer Order Secure SOAP service with 2 operations and exposes them via CXF. It uses a code-first approach that allows the WSDL to be automatically generated at run-time.  It uses basic authentication from the karaf context. 
 
### Requirements:
 * Red Hat Fuse 7.6.0 
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8
 
Building
-----------------------
To build the project run the following Maven command: 
 
	mvn clean install
 
This will build the bundle including all of the manifest information. 

Deploying to Red Hat Fuse
-----------------------
To start up Fuse browse to your fuse install directory. Then run
     
	/bin/fuse

This will bring up the Fuse console and from there you can install your bundle. To install the bundle, use one of the following commands:
 
	karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/soap_secure/7.6/soap_secure-7.6.jar
        OR
	karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/soap_secure/7.6
 
The `-s` indicates that the bundle should be started after it is installed. If you leave it off you can start the bundle using the following command:
    
	karaf@root> osgi:start <bundleId>

Testing
-----------------------
Once the bundle is deployed you can validate that Web Service was created and started by checking the CXF Service List at [http://localhost:8181/cxf](http://localhost:8181/cxf). Here you should see the Customer Order service listed under **Available SOAP services**. You may view the generated WSDL at:

- [http://localhost:8181/cxf/fusequickstarts-camelcxf-codefirst/secure/customerorder?wsdl](http://localhost:8181/cxf/fusequickstarts-camelcxf-codefirst/secure/customerorder?wsdl)

You can test the services using the following SOAP Requests using a tool such as [SoapUI](http://www.soapui.org/).

For Place Order use 

	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.service.soap.karaf.fusequickstarts.consulting.redhat.com/">
	   <soapenv:Header/>
	   <soapenv:Body>
	      <ws:placeOrder>
	         <arg0>
	            <item>Widget</item>
	            <orderId>ORD56X</orderId>
	            <quantity>34</quantity>
	         </arg0>
	      </ws:placeOrder>
	   </soapenv:Body>
	</soapenv:Envelope>

or for Get Order use

	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.service.soap.karaf.fusequickstarts.consulting.redhat.com/">
	   <soapenv:Header/>
	   <soapenv:Body>
	      <ws:getOrder>
	         <arg0>ORD34B</arg0>
	      </ws:getOrder>
	   </soapenv:Body>
	</soapenv:Envelope>

You can test first without any headers to see the authentication is working.  You should see a response like the following:

       <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
         <soap:Body>
            <soap:Fault>
              <faultcode>soap:Server</faultcode>
              <faultstring>Authentication required but no user or password was supplied</faultstring>
            </soap:Fault>
         </soap:Body>
       </soap:Envelope>

Now create your authentication header. The header name will be 'Authorization'.  Its value should be 'Basic base64encode(username:password)' where the username and password are taken from the karaf users.  If you are using a base fuse install you should be able to use admin:admin.  You can check your users by looking at the /opt/fuse/etc/users.properties file. 

Additional Reading
-----------------------
More information about creating a Code First SOAP Web Service can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/ImplWs-JavaFirst.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/ImplWs-JavaFirst.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-CXF.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-CXF.html)

Troubleshooting
-----------------------
If you run into any problems starting the Quick Start, check out some of the solutions below to get them working.

### javax.jws Wiring Error
If you are seeing the following error

	 Unable to start bundle mvn:com.redhat.consulting.fusequickstarts.karaf/soap: Unresolved constraint in bundle com.redhat.consulting.fusequickstarts.karaf.soap [275]: Unable to resolve 275.0: missing requirement [275.0] osgi.wiring.package; (&(osgi.wiring.package=javax.jws)(version>=2.0.0)(!(version>=3.0.0)))

then you need to install the JAX-WS 2.0 annotation classes into Fuse as a Dependency. You can do that with the following command

	install wrap:mvn:org.apache.geronimo.specs/geronimo-ws-metadata_2.0_spec/1.1.3
