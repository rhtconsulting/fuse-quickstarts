Publishing and Consuming an OSGi Service
====================================
This project demonstrates how to expose a class as an OSGi Service and then reference it and use it in another Bundle.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
This project includes the following bundles:

 * **osgi_service**
  * This bundle exposes a simple OSGi service for other bundles to use.
 * **osgi_consumer**
  * This bundle includes a Camel route that utilize the shared OSGi Service.
 * **osgi_service_feature**
  * This bundle builds the Feature file used to install the project.

Setup
------------------------
To setup and run the example you need to build all 3 child projects and then deploy the Feature file. Additional information on building and deploying each of the modules can be found in their respective directories.
