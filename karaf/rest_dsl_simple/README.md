Simple Camel Rest DSL example
====================================
This project is a simple Rest DSL Camel route. It is an osgi bundle that can be installed on 
Fuse. It is very minimal and configured with Blueprint. 

### Requirements:
 * JBoss Fuse 6.2.0 
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
--------

To build the project. 

	mvn clean install

This will build the bundle including the manifest information. 

Deploying to JBoss Fuse
-----------------------

To start up Fuse Karaf browse to your fuse install directory. Then run
 
	/bin/fuse

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one 
bundle to install we can just install it using the file by the following command. Another option is to set up
your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file.  Then you can use the 
mvn syntax below.

	 karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/rest-dsl-simple/1.0.0/rest-dslt-1.0.0.jar
    OR
	 karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.rest.dsl.simple/rest-dsl-simple/1.0.0

The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

	karaf@root> osgi:start <bundleId>

Testing
-----------------------
You can test the Bundle by accessing the following URL's

* **[http://localhost:8182/rest/get](http://localhost:8182/rest/get)**
 * HTTP Method: GET
 * Should return "Successful GET Request"
* **[http://localhost:8182/rest/post](http://localhost:8182/rest/post)**
 * HTTP Method: POST
 * Should see a similar line to the one below in data/log/fuse.log
  * `10:16:26,669 | INFO  | stlet-1836600637 | route  | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Successful POST Request`

Additional Reading
-----------------------
Additional information about using the REST DSL with Fuse can be found here:

- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-RestDSL.html)
- [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices-Config.html)

Troubleshooting
-----------------------
If you run into any problems starting the Quickstart, check out some of the solutions below to get them working.

### No bean could be found in the registry for: restlet
If you encounter errors similar to the one below when starting the route it is because you do not have the `camel-restlet` features installed.

	Error occurred during starting Camel: CamelContext(fusequickstart-restdsl-camel) due No bean could be found in the registry for: restlet of type: org.apache.camel.spi.RestConsumerFactory
	org.apache.camel.NoSuchBeanException: No bean could be found in the registry for: restlet of type: org.apache.camel.spi.RestConsumerFactory

To resolve this error, install the `camel-restlet` feature in Fuse with the following command.

	features:install camel-restlet