JBoss Data Grid Local Client
====================================
This bundle exposes a single route that uses a Bean and the Hot Rod client to connect to a local JBoss Data Grid cache.

### Requirements: ###

 * JBoss Fuse 6.2.0
 * JBoss Data Grid 6.5.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Before you can get started with this example, you need to install the JBoss Data Grid features. To do run the following commands in the Fuse console.

	features:addUrl mvn:org.infinispan/infinispan-embedded/6.3.1.Final-redhat-1/xml/features
	features:addUrl mvn:org.infinispan/infinispan-remote/6.3.1.Final-redhat-1/xml/features
	features:install infinispan-embedded
	features:install infinispan-remote

Building
-----------------------
To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Deploying to JBoss Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/infinispan/local-client/1.0.0/local-client-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-client/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    2015-11-25 13:45:28,149 | INFO  | r://remoteClient | LocalClient | ?              ? | 285 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-client - 1.0.0 | Local PUT 2 : Wed Nov 25 13:45:28 EST 2015
    2015-11-25 13:45:28,150 | INFO  | r://remoteClient | LocalCacheListener               | ?              ? | 284 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-cache - 1.0.0 | Infinispan event received: CACHE_ENTRY_MODIFIED with key: 2
    2015-11-25 13:45:28,151 | INFO  | r://remoteClient | localClient | ?              ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[Id: ID-localhost-localdomain-38106-1448474644887-12-4, ExchangePattern: InOnly, Properties: {CamelCreatedTimestamp=Wed Nov 25 13:45:28 EST 2015, CamelMessageHistory=[DefaultMessageHistory[routeId=route4, node=to8], DefaultMessageHistory[routeId=route4, node=to9]], CamelTimerCounter=2, CamelTimerFiredTime=Wed Nov 25 13:45:28 EST 2015, CamelTimerName=remoteClient, CamelTimerPeriod=5000, CamelToEndpoint=log://localClient?showAll=true}, Headers: {breadcrumbId=ID-localhost-localdomain-38106-1448474644887-12-3, firedTime=Wed Nov 25 13:45:28 EST 2015}, BodyType: String, Body: Wed Nov 25 13:43:51 EST 2015, Out: null: ]
    2015-11-25 13:45:28,153 | INFO  | r://remoteClient | LocalClient | ?              ? | 285 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-client - 1.0.0 | Local GET 2 :  
    2015-11-25 13:45:28,154 | INFO  | r://remoteClient | LocalCacheListener               | ?              ? | 284 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-cache - 1.0.0 | Infinispan event received: CACHE_ENTRY_VISITED with key: 2
    2015-11-25 13:45:28,155 | INFO  | r://remoteClient | localClient | ?              ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[Id: ID-localhost-localdomain-38106-1448474644887-12-4, ExchangePattern: InOnly, Properties: {CamelCreatedTimestamp=Wed Nov 25 13:45:28 EST 2015, CamelMessageHistory=[DefaultMessageHistory[routeId=route4, node=to8], DefaultMessageHistory[routeId=route4, node=to9], DefaultMessageHistory[routeId=route4, node=to10], DefaultMessageHistory[routeId=route4, node=to11]], CamelTimerCounter=2, CamelTimerFiredTime=Wed Nov 25 13:45:28 EST 2015, CamelTimerName=remoteClient, CamelTimerPeriod=5000, CamelToEndpoint=log://localClient?showAll=true}, Headers: {breadcrumbId=ID-localhost-localdomain-38106-1448474644887-12-3, firedTime=Wed Nov 25 13:45:28 EST 2015}, BodyType: String, Body: Wed Nov 25 13:45:28 EST 2015, Out: null: ]


Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Infinispan features

    	Error resolving artifact org.infinispan:infinispan-embedded:xml:features:6.3.1.Final-redhat-1: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
