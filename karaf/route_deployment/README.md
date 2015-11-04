Route Deployment Camel OSGi example
====================================

 This project is a simple Hello World Camel route.  It is an osgi bundle that can be install on
 Fuse Karaf.  It is very minimal and configured with Blueprint.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------

To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Deploying to JBoss Fuse
-----------------------

To start up Fuse Karaf browse to your fuse install directory. Then run

     /bin/fuse or /bin/karaf

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one
bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file.  Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/route-deployment/1.0.0/route-deployment-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/route-deployment/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
--------

Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

     | INFO  |  timer://myTimer | route                            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Hello World
     | INFO  |  timer://myTimer | HelloWorldLog                    | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
