Feature OSGi example
====================================
This example deploys three of our existing examples using a feature file.  The file also encompasses all of their dependencies. 

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Because this Feature file installs several of the bundles for the other quickstarts, you should first build the following other quickstarts using the command `mvn clean install`.

 * rest_dsl
 * properties
 * soap


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

     karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf/feature/6.3/xml/features
       
     karaf@root> features:install -v my-services 

 The -v here stands for verbose and is optional. 

Results
-----------------------
After running the above commands you should see your services started:

     [ 291] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: Camel REST DSL (6.3)
     [ 292] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Config Admin Service (6.3)
     [ 293] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: Camel-CXF SOAP (Code First) (6.3)
     
Troubleshooting
-----------------------

**Error resolving artifact com.redhat.consulting.fusequickstarts.karaf**

If you get this error it means that you need to build the missing quickstart so that it will be installed into your local maven repository. Otherwise the Feature will not be able to locate the bundle to install it.


