OSGi Infinispan Embedded Cache
====================================
This bundle embeds a local instance of Infinispan into the OSGi container and exposes an Embedded Cache Manager as an OSGi service for other bundles to take advantage of. It also registers an Infinispan Event Listener for the Cache.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Before you can get started with this example, you need to install the Infinispan features. To do run the following commands in the Fuse console.


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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/infinispan/local-cache/1.0.0/local-cache-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-cache/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    2015-11-24 21:36:09,811 | INFO  | rint Extender: 1 | LocalCacheListener               | ?     ? | 276 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-cache - 1.0.0 | Registering event listener
    2015-11-24 21:36:10,176 | INFO  | rint Extender: 1 | JGroupsTransport                 | ?     ? | 274 - org.infinispan.embedded - 6.3.1.Final-redhat-1 | ISPN000078: Starting JGroups Channel
    2015-11-24 21:36:10,501 | INFO  | rint Extender: 1 | UFC| ?     ? | 274 - org.infinispan.embedded - 6.3.1.Final-redhat-1 | UFC is not needed (and can be removed) as we're running on a TCP transport
    2015-11-24 21:36:13,541 | INFO  | rint Extender: 1 | JGroupsTransport                 | ?     ? | 274 - org.infinispan.embedded - 6.3.1.Final-redhat-1 | ISPN000094: Received new cluster view: [localhost-22369|0] (1) [localhost-22369]
    2015-11-24 21:36:13,894 | INFO  | rint Extender: 1 | JGroupsTransport                 | ?     ? | 274 - org.infinispan.embedded - 6.3.1.Final-redhat-1 | ISPN000079: Cache local address is localhost-22369, physical addresses are [127.0.0.1:7800]
    2015-11-24 21:36:13,901 | INFO  | rint Extender: 1 | GlobalComponentRegistry          | ?     ? | 274 - org.infinispan.embedded - 6.3.1.Final-redhat-1 | ISPN000128: Infinispan version: Infinispan 'Infinium' 6.3.1.CR1-redhat-1
    2015-11-24 21:36:14,434 | INFO  | rint Extender: 1 | LocalCacheListener               | ?     ? | 276 - com.redhat.consulting.fusequickstarts.karaf.infinispan.local-cache - 1.0.0 | Registered event listener

Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Infinispan features

    	Error resolving artifact org.infinispan:infinispan-embedded:xml:features:6.3.1.Final-redhat-1: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
