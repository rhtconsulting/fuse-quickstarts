Camel JBoss Data Grid Local Consumer
====================================
This bundle exposes a single route that demonstrates the Consumer functionality of the JBoss Data Grid component.

### Requirements: ###

 * Red Hat Fuse 7.6.0
 * JBoss Data Grid 6.5.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Before you can get started with this example, you need to install the JBoss Data Grid features. To do run the following commands in the Fuse console.

    features:addurl mvn:org.apache.camel/camel-jbossdatagrid/6.5.0.Final-redhat-5/xml/features
    features:install camel-jbossdatagrid

In addition, you need to have the following additional bundles built, installed, and started.

    osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-cache
    osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-camel-producer 

Building
-----------------------
To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Deploying to Red Hat Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/infinispan/local-camel-consumer/7.6/local-camel-consumer-7.6.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-camel-consumer/7.6

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    2015-11-30 20:10:25,178 | INFO  | calCamelProducer | localCamelConsumer  | ?      ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Exchange[Id: ID-localhost-localdomain-39053-1448930964732-8-4, ExchangePattern: InOnly, Properties: {CamelCreatedTimestamp=Mon Nov 30 20:10:25 EST 2015, CamelMessageHistory=[DefaultMessageHistory[routeId=route9, node=to18]], CamelToEndpoint=log://localCamelConsumer?showAll=true}, Headers: {breadcrumbId=ID-localhost-localdomain-39053-1448930964732-8-3}, BodyType: null, Body: [Body is null], OutHeaders: {CamelInfinispanCacheName=___defaultcache, CamelInfinispanEventType=CACHE_ENTRY_CREATED, CamelInfinispanIsPre=false, CamelInfinispanKey=1}, OutBodyType: null, OutBody: [Body is null]]
    2015-11-30 20:10:25,178 | INFO  | calCamelProducer | localCamelProducer    | ?     ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Exchange[Id: ID-localhost-localdomain-39053-1448930964732-7-2, ExchangePattern: InOnly, Properties: {CamelCreatedTimestamp=Mon Nov 30 20:10:25 EST 2015, CamelMessageHistory=[DefaultMessageHistory[routeId=route8, node=setHeader9], DefaultMessageHistory[routeId=route8, node=setHeader10], DefaultMessageHistory[routeId=route8, node=to16], DefaultMessageHistory[routeId=route8, node=to17]], CamelTimerCounter=1, CamelTimerFiredTime=Mon Nov 30 20:10:25 EST 2015, CamelTimerName=localCamelProducer, CamelTimerPeriod=5000, CamelToEndpoint=log://localCamelProducer?showAll=true}, Headers: {breadcrumbId=ID-localhost-localdomain-39053-1448930964732-7-1, CamelInfinispanKey=1, CamelInfinispanOperationResult=null, CamelInfinispanValue=1, firedTime=Mon Nov 30 20:10:25 EST 2015}, BodyType: null, Body: [Body is null], Out: null: ]

Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Data Grid features

    	Error resolving artifact org.apache.camel:camel-jbossdatagrid:xml:features:6.5.0.Final-redhat-5: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
