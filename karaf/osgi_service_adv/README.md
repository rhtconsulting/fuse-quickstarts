
Publishing and Consuming an OSGi Service between Spring and Blueprint Bundles
====================================
This project demonstrates how to expose a class as an OSGi Service and then reference it in a seperate bundle. Specifically it demonstrates the use of services published by Blueprint or Spring and then used by both frameworks.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
This project includes the following bundles:

 * **service_api** - The Interface for the Service
 * **blueprint_service** - Blueprint Version of the Service
 * **spring_service** - Spring Version of the Service
 * **blueprint_consumer** - Blueprint Bundle consuming the Blueprint Service
 * **blueprint_sdm_consumer** - Blueprint Bundle consuming the Spring Service
 * **spring_consumer** - Spring Bundle consuming the Spring Service
 * **spring_bp_consumer** - Spring Bundle consuming the Blueprint Service

Building
-----------------------
To build the project.

       mvn clean install

This will build the feature file.

Deploying to JBoss Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

       /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundles. 

       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/service_api
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/spring_service
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/blueprint_service
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/spring_consumer
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/blueprint_consumer
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/spring_bp_consumer
       install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service_adv/blueprint_sdm_consumer

Results
-----------------------
Once you have the routes started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    2015-11-20 15:36:13,257 | INFO  | r://serviceTimer | bp2bp | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default Blueprint Message]
    2015-11-20 15:36:13,257 | INFO  | r://serviceTimer | bp2sdm | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default Blueprint Message]
    2015-11-20 15:36:13,257 | INFO  | r://serviceTimer | sdm2sdm | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default SpringDM Message]
    2015-11-20 15:36:13,257 | INFO  | r://serviceTimer | sdm2bp | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default SpringDM Message]
