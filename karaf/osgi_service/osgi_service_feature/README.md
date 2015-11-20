Shared OSGi Service Feature
====================================
This builds the feature file for deploying the Shared OSGi Service and Route Bundles.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Because this Feature file installs several other bundles, you should first build the following other projects using the command `mvn clean install`.

 * osgi_service
 * osgi_consumer


Building
-----------------------
To build the project.

     mvn clean install

This will build the feature file.

Deploying to JBoss Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

     karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service/osgi_service_feature/1.0.0/xml/features
       
     karaf@root> features:install -v sharedOsgiService

 The -v here stands for verbose and is optional. 

Results
-----------------------
After running the above commands you should see your services started:

	[ 265] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Service :: Shared Service (1.0.0)
	[ 266] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Service :: Consumer (1.0.0)


Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

2015-11-20 15:36:13,257 | INFO  | r://serviceTimer | helloServiceLog | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default Message]
2015-11-20 15:36:13,258 | INFO  | r://serviceTimer | helloServiceLog | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Hello Jim, How Are you?]

     
Troubleshooting
-----------------------

**Error resolving artifact com.redhat.consulting.fusequickstarts.karaf**

If you get this error it means that you need to build the missing quickstart so that it will be installed into your local maven repository. Otherwise the Feature will not be able to locate the bundle to install it.


