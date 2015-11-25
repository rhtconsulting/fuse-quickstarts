Camel JBoss Data Grid Local Producer
====================================
This bundle exposes a single route that demonstrates the Producer functionality of the JBoss Data Grid component.

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
  features:addUrl mvn:org.apache.camel/camel-jbossdatagrid/6.5.0.Final-redhat-5/xml/features
  features:install camel-jbossdatagrid
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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/infinispan/local-camel-producer/1.0.0/local-camel-producer-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-camel-producer/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

    TODO: ADD OUTPUT HERE

Troubleshooting
-----------------------

### Cant Add Feature URL ###
If you are getting the following error or a similar error when trying to deploy the Infinispan features

    	Error resolving artifact org.infinispan:infinispan-embedded:xml:features:6.3.1.Final-redhat-1: Could not find artifact

You Need to add the Red Hat GA Maven Repo to the Fuse Configuration file `org.ops4j.pax.url.mvn.cfg`. ATo do so append the URL `https://maven.repository.redhat.com/ga/` to the property `org.ops4j.pax.url.mvn.repositories`.
