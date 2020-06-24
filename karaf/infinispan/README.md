Using JBoss Data Grid with OSGi
====================================
This project demonstrates multiple ways to utilize JBoss Data Grid with Red Hat Fuse.

### Requirements:
 * Red Hat Fuse 7.6.0
 * JBoss Data Grid 6.5
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
The following bundles are included in this project.

 * **local-cache** - Embeds JBoss Data Grid as a local cache in Fuse and exposes a Cache Manager service via OSGi.
 * **local-camel-consumer** - Local Camel route that consumes events from the local JBoss Data Grid cache.
 * **local-camel-producer** - Stores and retrieves data from the local JBoss Data Grid cache using Camel.
 * **local-client** - Connects to the local JBoss Data Grid cache using a bean with the Hot Rod client.
 * **remote-client** - Connects to a remote JBoss Data Grid cache using a beah with the Hot Rod client.
 * **remote-camel-producer** - Connects to a remove JBoss Data Grid cache using the camel component.

Setup
-----------------------
Before you can get started with this project, you need to install the JBoss Data Grid features. To do run the following commands in the Fuse console.

    features:addurl mvn:org.apache.camel/camel-jbossdatagrid/6.5.0.Final-redhat-5/xml/features
    features:install camel-jbossdatagrid
    
### Setup Note ###
When you are installing the features, DO NOT install the `infinispan-embedded` and `infinispan-remote` features as stated in the JBoss Data Grid documentation. Doing so will lead deployment and runtime errors related to incompatible dependencies.

Building
-----------------------
To build the project.

     mvn clean install

This will build all of the bundles and features needed for the examples.

Additional Reading
-----------------------
If you are looking for more information about integrating JBoss Data Grid and Red Hat Fuse, check out the following documentation.

- [Running Red Hat JBoss Data Grid in Apache Karaf (OSGi)](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Data_Grid/6.5/html/Getting_Started_Guide/sect-Running_Red_Hat_JBoss_Data_Grid_in_Karaf_OSGi.html)
- [Running Red Hat JBoss Data Grid with Apache Camel](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Data_Grid/6.5/html/Getting_Started_Guide/chap-Running_Red_Hat_JBoss_Data_Grid_with_Apache_Camel.html)

Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Data Grid features

    	Error resolving artifact org.apache.camel:camel-jbossdatagrid:xml:features:6.5.0.Final-redhat-5: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.

### Waiting for Dependency: component=jbossdatagrid ###
If you are getting errors like the one below when you deploy the bundles it is because you are missing the camel-jbossdatagrid component.

    Bundle com.redhat.consulting.fusequickstarts.karaf.infinispan.local-camel-producer is waiting for dependencies [(&(component=infinispan)(objectClass=org.apache.camel.spi.ComponentResolver))]

To resolve the issue, run the following commands to install the missing features.

    features:addUrl mvn:org.apache.camel/camel-jbossdatagrid/6.5.0.Final-redhat-5/xml/features
    features:install camel-jbossdatagrid
