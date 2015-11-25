Using JBoss Data Grid (Infinispan) with OSGi
====================================
This project demonstrates multiple ways to utilize JBoss Data Grid and Infinispan with JBoss Fuse.

### Requirements:
 * JBoss Fuse 6.2.0
 * JBoss Data Grid 6.5
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
The following bundles are included in this project.

 * **local-cache** - Embeds Infinispan as a local cache in Fuse and exposes a Cache Manager service via OSGi.
 * **local-camel-consumer** - Local Camel route that consumes events from the local Infinispan cache.
 * **local-camel-producer** - Stores and retrieves data from the local infinispan cache using Camel.
 * **local-client** - Connects to the local Infinispan cache using a bean with the Hot Rod client.
 * **remote-client** - Connects to a remote Infinispan cache using a beah with the Hot Rod client.
 * **remote-camel-producer** - Connects to a remove Infinispan cache using the camel component.
 * **features** - Builds the various features needed to create and run the Demos described below.

Setup
-----------------------
Before you can get started with this project, you need to install the Infinispan features. To do run the following commands in the Fuse console.

	features:addUrl mvn:org.infinispan/infinispan-embedded/6.3.1.Final-redhat-1/xml/features
	features:addUrl mvn:org.infinispan/infinispan-remote/6.3.1.Final-redhat-1/xml/features
	features:install infinispan-embedded
	features:install infinispan-remote

Building
-----------------------
To build the project.

     mvn clean install

This will build all of the bundles and features needed for the examples.

Running the Demos
-----------------------
TODO


Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Infinispan features

    	Error resolving artifact org.infinispan:infinispan-embedded:xml:features:6.3.1.Final-redhat-1: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
