OSGi Service Consumer
====================================
This bundle creates a simple route that consumes the exposed Hello World OSGi Service.

### Requirements:
 * Red Hat Fuse 7.6.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/osgi_service/osgi_consumer/7.6/osgi_consumer-7.6.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_service/osgi_consumer/7.6

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
After running the above commands and installing the `osgi_consumer` bundle you should see your services started:

	[ 265] [Active     ] [Created     ] [       ] [   80] Fuse Quick Start :: Karaf :: Blueprint :: OSGi Service :: Shared Service (7.6)
	[ 266] [Active     ] [Created     ] [       ] [   80] Fuse Quick Start :: Karaf :: Blueprint :: OSGi Service :: Consumer (7.6)


Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

	2015-11-19 17:40:32,106 | INFO  | r://serviceTimer | helloServiceLog   | ?   ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Hello Jim, How Are you?]
