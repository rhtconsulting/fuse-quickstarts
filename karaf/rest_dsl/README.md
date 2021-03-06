Rest DSL Camel OSGi example
====================================
 This project demonstrates how to publish a GET and POST REST Webservice using the DSL as well as how to work with JSON Objects and marshall them to a Pojo. It also has seperate routes that demonstrate how to call a REST endpoint using the HTTP4 Component. 
 
### Requirements:
 * Red Hat Fuse 7.6.0 
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
 
	karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/rest-dsl/7.6/rest-dsl-7.6.jar
        OR
	karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/rest-dsl/7.6
 
The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run
    
	karaf@root> osgi:start <bundleId>

Testing
-----------------------
After you deploy the route you can confirm that the endpoints are deployed and working by checking the log for the following output

	2015-11-05 12:25:37,138 | INFO  | imer://restTimer | route57    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Calling REST Endpoint at http://localhost:8182/rest/message with Method GET and Body:
	2015-11-05 12:25:37,144 | INFO  | stlet-2117440026 | route59    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Successful GET Request: com.redhat.consulting.fusequickstarts.karaf.rest.dsl.model.Note@44101b7c
	2015-11-05 12:25:37,150 | INFO  | imer://restTimer | route57    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | HTTP Resonse Code: 200 and Body: {"message":"REST is Awesome","to":"User","from":"Developer"}
	2015-11-05 12:25:37,154 | INFO  | imer://restTimer | route58    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Calling REST Endpoint at http://localhost:8182/rest/message with Method POST and Body: {"message":"REST is Awesome","to":"User","from":"Developer"}
	2015-11-05 12:25:37,160 | INFO  | stlet-2117440026 | route60    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Successful POST Request: com.redhat.consulting.fusequickstarts.karaf.rest.dsl.model.Note@114360cc
	2015-11-05 12:25:37,164 | INFO  | imer://restTimer | route58    | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | HTTP Resonse Code: 201

There is also a set of Unit Tests that run during the build process to test the Routes logic using the camel-test framework in addition to PaxExam based Integrated tests in the Integration Test Suite.

Additional Reading
-----------------------
Additional information about using the REST DSL with Fuse can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Binding.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Binding.html)

Information about Calling a REST Endpoint using HTTP4 can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-HTTP4.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-HTTP4.html)

Troubleshooting
-----------------------
If you run into any problems starting the Quick Start, check out some of the solutions below to get them working.

### No bean could be found in the registry for: restlet
If you encounter errors similar to the one below when starting the route it is because you do not have the `camel-restlet` features installed.

	Error occurred during starting Camel: CamelContext(fusequickstart-restdsl-camel) due No bean could be found in the registry for: restlet of type: org.apache.camel.spi.RestConsumerFactory
	org.apache.camel.NoSuchBeanException: No bean could be found in the registry for: restlet of type: org.apache.camel.spi.RestConsumerFactory

To resolve this error, install the `camel-restlet` feature in Fuse with the following command.

	features:install camel-restlet

### Not getting JSON returned from Endpoint
If you are not getting JSON returned from the REST endpoints, then it is because no JSON Converters can be found on the classpath. You can resolve this by installing the `camel-jackson` feature using the following command.

	features:install camel-jackson

### HTTP4 Wiring Error
If you are seeing the following error

	Unresolved constraint in bundle com.redhat.consulting.fusequickstarts.karaf.rest-dsl [268]: Unable to resolve 268.12: missing requirement [268.12] osgi.wiring.package; (&(osgi.wiring.package=org.apache.camel.component.http4)(version>=2.17.0)(!(version>=3.0.0)))

then you need to install the `camel-http4` feature in Fuse with the following command.

	features:install camel-http4
