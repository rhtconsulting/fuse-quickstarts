JBoss Data Grid Local Client
====================================
This bundle exposes a single route that uses a Bean and the Hot Rod client to connect to a local JBoss Data Grid cache.

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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/infinispan/local-client/7.6/local-client-7.6.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-client/7.6

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    2015-11-30 20:15:54,712 | INFO  | er://localClient | LocalClient  | ?  ? | 279 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-client - 1.0.0 | Local PUT 1 : Mon Nov 30 20:15:54 EST 2015
    2015-11-30 20:15:54,713 | INFO  | er://localClient | LocalCacheListener  | ?  ? | 276 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-cache - 1.0.0 | Infinispan event received: CACHE_ENTRY_CREATED with key: 1
    2015-11-30 20:15:54,714 | INFO  | er://localClient | localClient  | ?  ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Exchange[Id: ID-localhost-localdomain-39053-1448930964732-9-2, ExchangePattern: InOnly, Properties: {CamelCreatedTimestamp=Mon Nov 30 20:15:54 EST 2015, CamelMessageHistory=[DefaultMessageHistory[routeId=route10, node=to19], DefaultMessageHistory[routeId=route10, node=to20]], CamelTimerCounter=1, CamelTimerFiredTime=Mon Nov 30 20:15:54 EST 2015, CamelTimerName=localClient, CamelTimerPeriod=5000, CamelToEndpoint=log://localClient?showAll=true}, Headers: {breadcrumbId=ID-localhost-localdomain-39053-1448930964732-9-1, firedTime=Mon Nov 30 20:15:54 EST 2015}, BodyType: null, Body: [Body is null], Out: null: ]


Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Data Grid features

    	Error resolving artifact org.apache.camel:camel-jbossdatagrid:xml:features:6.5.0.Final-redhat-5: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
