Rest CXF RS and bean validation Camel OSGi example
====================================
 This project demonstrates how to publish a  Webservice using CXF RS and validate the input with bean validation. It also has seperate routes that demonstrate how to call a the cxf rs endpoint and also how to receive the request and process it.
 Take note of the fact that the interface and his implementation are used ONLY for service definition, not implementation (i.e. the implementation method never get called). The implementation of the rest service must be called explicitly from the route iteself.
 
### Requirements:
 * Red Hat Fuse 7.6
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8
 
Building
-----------------------
 
To build the project. 
 
	mvn clean install
 
This will build the bundle including the manifest information. 

Deploying to Red Hat Fuse
-----------------------
 
To start up Fuse Karaf browse to your fuse install directory. Then run
     
	/bin/fuse

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file.  Then you can use the mvn syntax below.

	admin@root> features:install cxf-bean-validation hibernate-validator cxf-jaxrs
    admin@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/rest-beanvalidation/7.6
        OR
    admin@root> features:install cxf-bean-validation hibernate-validator cxf-jaxrs
    admin@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/rest-beanvalidation-7.6.jar
 
The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run
    
	karaf@root> osgi:start <bundleId>

Testing
-----------------------
After you deploy the route you can confirm that the endpoints are deployed and working by checking the log for the following output:

21:12:22,118 | INFO  | tp1296672062-339 | LoggingInInterceptor             | 118 - org.apache.cxf.cxf-core - 3.0.4.redhat-620133 | Inbound Message

ID: 5897
Address: http://localhost:8183/service/1
Http-Method: GET
Content-Type: */*
Headers: {Accept=[application/json,application/xml], breadcrumbId=[ID-dhcp-192-168-141-42-40384-1447969596266-17-41], Cache-Control=[no-cache], connection=[keep-alive], content-type=[*/*], firedTime=[Thu N      ov 19 21:12:22 EST 2015], Host=[localhost:8183], Pragma=[no-cache], User-Agent=[Apache CXF 3.0.4.redhat-620133]}

21:12:22,118 | INFO  | tp1296672062-339 | route35                          | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Request Recieved
21:12:22,119 | INFO  | tp1296672062-339 | LoggingOutInterceptor            | 118 - org.apache.cxf.cxf-core - 3.0.4.redhat-620133 | Outbound Message

ID: 5897
Response-Code: 200
Content-Type: application/json
Headers: {Content-Type=[application/json], Date=[Fri, 20 Nov 2015 02:12:22 GMT]}
Payload: Sample User

you will also see some errors because not all the auto generated requests will succeed, aproxymately half of them will be rejected because of failing the validation. According to the regex validation pattern in the service interface the url parameter part should be numeric and begin with '1'. You can verify that also using Camel tab of Hawtaio console at http://localhost:8181.

