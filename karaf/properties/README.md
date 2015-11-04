
OSGi Config Admin Service example
====================================

 This project is a simple Hello World Camel route the pulls properties from the OSGi Config Admin Service. It is an osgi bundle that can be install on Fuse Karaf. It is very minimal and configured with Blueprint.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.2.3 (http://maven.apache.org/)
 * Java 8

Building
-----------------------

To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Setup
-----------------------
In order for the Bundle to read your properties file after you start it, you need to first move it to the correct folder. Copy the file `src\test\resources\com.redhat.consulting.fusequickstarts.karaf.cfg` to `$FUSE_ROOT\etc`. 

This is where all of the Fuse configuration files are stored and will allow Fuse to read your file into the Config Admin Service.

Deploying to JBoss Fuse
-----------------------

To start up Fuse Karaf browse to your fuse install directory. Then run

     /bin/fuse or /bin/karaf

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2  repository in fuse in the `fuse/etc/org.ops4j.pax.url.mvn.cfg` file.  Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/properties/1.0.0/route-deployment-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/properties/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Testing
--------

Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

    2015-11-03 15:15:36,947 | INFO  |  timer://myTimer | PropertiesLog                    | ?                                   ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading Property 'test.foo': Hello]
	2015-11-03 15:15:36,948 | INFO  |  timer://myTimer | PropertiesLog                    | ?                                   ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading Property 'test.bar': World]

You can test the Auto Reload for the properties by making changes to the property file either directly or through the fuse console. The following commands can be used to edit the property inside the console:

	JBossFuse:karaf@root> config:edit com.redhat.consulting.fusequickstarts.karaf
	JBossFuse:karaf@root> config:proplist
	   felix.fileinstall.filename = file:/C:/Installs/jboss-fuse-6.2.0.redhat-143-p1/etc/com.redhat.consulting.fusequickstarts.karaf.cfg
	   service.pid = com.redhat.consulting.fusequickstarts.karaf
	   test.bar = World
	   test.foo = Hello
	JBossFuse:karaf@root> config:propset test.bar Bryan
	JBossFuse:karaf@root> config:update

After making the change, you should see the updated values in your log output.