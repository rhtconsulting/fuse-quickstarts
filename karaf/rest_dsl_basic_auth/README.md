Rest DSL w/ Basic Auth Example
====================================
This project demonstrates how to publish a GET and POST REST Webservice using the DSL with Basic Authentication using Fuse's JAAS Realms. It uses basic authentication from the karaf context.  It also shows how to work with JSON Objects and marshall them to a Pojos. In addition it has seperate route that demonstrate how to call a REST endpoint using the HTTP4 Component and Basic Auth. 
 
### Requirements:
 * JBoss Fuse 6.2.0 
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
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file.  Then you can use the mvn syntax below.
 
	karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/rest-dsl-basic-auth/1.0.0/rest-dsl-basic-auth-1.0.0.jar
        OR
	karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/rest-dsl-basic-auth/1.0.0
 
The -s here indicates to also start the bundle. Alternatively you can omit the -s and after the install run
    
	karaf@root> osgi:start <bundleId>

Testing
-----------------------
After you deploy the route you can confirm that the endpoints are deployed and working by checking the log for the following output

	2015-11-22 16:28:24,256 | INFO  | imer://restTimer | route21 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Calling REST Endpoint at http://localhost:8183/rest/message with Method GET and Body: 
	2015-11-22 16:28:24,263 | INFO  | qtp935634874-242 | route23 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Successful GET Request: com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.model.Note@75bd53b6
	2015-11-22 16:28:24,265 | INFO  | imer://restTimer | route21 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | HTTP Response Code: 200 and Body: {"message":"REST is Awesome","to":"User","from":"Developer"}
	2015-11-22 16:28:24,267 | INFO  | imer://restTimer | route22 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Calling REST Endpoint at  with Method POST and Body: {"message":"REST is Awesome","to":"User","from":"Developer"}
	2015-11-22 16:28:24,288 | INFO  | qtp935634874-243 | route24 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Successful POST Request: com.redhat.consulting.fusequickstarts.karaf.rest.auth.basic.dsl.model.Note@46779d43
	2015-11-22 16:28:24,290 | INFO  | imer://restTimer | route22 | ?          ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | HTTP Response Code: 201


There is also a set of Unit Tests that run during the build process to test the Routes logic using the camel-test framework in addition to PaxExam based Integrated tests in the Integration Test Suite.

Additional Reading
-----------------------
Additional information about using the REST DSL with Fuse can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Binding.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Binding.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Security_Guide/CamelJetty.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Security_Guide/CamelJetty.html)

Information about Calling a REST Endpoint using HTTP4 can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-HTTP4.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-HTTP4.html)

Troubleshooting
-----------------------
If you run into any problems starting the Quickstart, check out some of the solutions below to get them working.

### Not getting JSON returned from Endpoint
If you are not getting JSON returned from the REST endpoints, then it is because no JSON Converters can be found on the classpath. You can resolve this by installing the `camel-jackson` feature using the following command.

	features:install camel-jackson

### HTTP4 Wiring Error
If you are seeing the following error

	Unresolved constraint in bundle com.redhat.consulting.fusequickstarts.karaf.rest-dsl [268]: Unable to resolve 268.12: missing requirement [268.12] osgi.wiring.package; (&(osgi.wiring.package=org.apache.camel.component.http4)(version>=2.15.0)(!(version>=3.0.0)))

then you need to install the `camel-http4` feature in Fuse with the following command.

	features:install camel-http4
