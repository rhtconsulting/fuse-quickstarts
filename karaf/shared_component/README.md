Using a Shared Camel Component
====================================
This project demonstrates how to expose a configured Camel Component (ActiveMQ in this case) as an OSGi Service and then use it in another

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
This project includes the following bundles:

 * **shared_amq**
  * This bundle configures the ActiveMQ component and exposes it as an OSGi service for other bundles to use.
 * **amq_routes**
  * This bundle includes two Camel routes that utilize the shared ActiveMQ component.
 * **shared_amq_feature**
  * This bundle builds the Feature file used to install the project.

Setup
------------------------
To setup and run the example you need to build all 3 child projects and then deploy the Feature file. Additional information on building and deploying each of the modules can be found in their respective directories.
