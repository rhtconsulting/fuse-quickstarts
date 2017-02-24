
OSGi Config Admin Service example
====================================

 This project is a simple Hello World Camel route the pulls properties from the OSGi Config Admin Service as well as System Properties. It is an osgi bundle that can be install on Fuse Karaf. It is very minimal and configured with Blueprint.

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------

To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Setup
-----------------------
In order for the Bundle to read your properties file after you start it, you need to first move it to the correct folder. Copy the file `src\test\resources\com.redhat.consulting.fusequickstarts.karaf.properties.cfg` to `$FUSE_ROOT\etc`. 

This is where all of the Fuse configuration files are stored and will allow Fuse to read your file into the Config Admin Service.

Deploying to JBoss Fuse
-----------------------

To start up Fuse Karaf browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console.  Once in the console you will be able to install your bundle.
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2  repository in fuse in the `fuse/etc/org.ops4j.pax.url.mvn.cfg` file.  Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/properties/6.3/properties-6.3.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/properties/6.3

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Testing
--------

Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

    2016-02-18 09:32:06,685 | INFO  |  timer://myTimer | PropertiesLog  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading Property 'test.foo': Hello]
	2016-02-18 09:32:06,686 | INFO  |  timer://myTimer | PropertiesLog  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading Property 'test.bar': World]
	2016-02-18 09:32:06,686 | INFO  |  timer://myTimer | PropertiesLog  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading System Property 'java.class.path': /home/bsaunder/install/j...
	2016-02-18 09:32:06,687 | INFO  |  timer://myTimer | PropertiesLog  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading System Property 'os.name': Linux]

You can test the Auto Reload for the properties by making changes to the property file either directly or through the fuse console. The following commands can be used to edit the property inside the console:

	JBossFuse:karaf@root> config:edit com.redhat.consulting.fusequickstarts.karaf.properties
	JBossFuse:karaf@root> config:proplist
	   felix.fileinstall.filename = file:/C:/Installs/jboss-fuse-6.3.0.redhat-187/etc/com.redhat.consulting.fusequickstarts.karaf.cfg
	   service.pid = com.redhat.consulting.fusequickstarts.karaf
	   test.bar = World
	   test.foo = Hello
	JBossFuse:karaf@root> config:propset test.bar Bryan
	JBossFuse:karaf@root> config:update

After making the change, you should see the updated values in your log output.

Additional Reading
-----------------------
Additional information about Externalizing Properties with Fuse can be found here:

 * [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Deploying_into_the_Container/DeployCamel-OsgiConfigProps.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Deploying_into_the_Container/DeployCamel-OsgiConfigProps.html)
 * [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Console_Reference/Consoleconfig.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Console_Reference/Consoleconfig.html)

