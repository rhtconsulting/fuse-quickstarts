Deplying Routes Dynamically with a Managed Service Factory
====================================
This example demonstrates how to deploy multiple copies of a single route based on configuration files using a Managed Service Factory.

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

To start up Fuse browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle.

     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/managed_service_factory

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Loading Test Data
-----------------------
After your route has been started, you need to add the Config file to the `etc` folder. To do so simply copy the file in `src/test/resources/etc/` into your `$FUSE_HOME/etc` directory.

Results
-----------------------
Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

       2015-12-11 11:34:33,375 | INFO  | 1.redhat-084/etc | fileinstall    | 9 - org.apache.felix.fileinstall - 3.5.0 | Creating configuration from com.rht.clt.fq.k.msf.routefactory-1.cfg
       2015-12-11 11:34:33,378 | INFO  | 56-3558afa41c22) | RouteFactory   | 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | MSF Route Update: pid=com.rht.clt.fq.k.msf.routefactory.b58acf05-f82a-4e3d-9956-3558afa41c22 props={felix.fileinstall.filename=file:/home/bsaunder/install/jboss-fuse-6.2.1.redhat-084/etc/com.rht.clt.fq.k.msf.routefactory-1.cfg, helloGreeting=Hello, helloName=Bryan, routeId=helloBryan, service.factoryPid=com.rht.clt.fq.k.msf.routefactory, service.pid=com.rht.clt.fq.k.msf.routefactory.b58acf05-f82a-4e3d-9956-3558afa41c22}
       2015-12-11 11:34:33,378 | INFO  | 56-3558afa41c22) | RouteFactory   | 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | Deleted com.rht.clt.fq.k.msf.routefactory.b58acf05-f82a-4e3d-9956-3558afa41c22
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | RouteFactory   | 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | HELLO_GREETING set to Hello
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | RouteFactory   | 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | HELLO_NAME set to Bryan
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | RouteFactory   | 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | ROUTE_ID set to helloBryan
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | RouteDispatcher| 272 - com.redhat.consulting.fusequickstarts.karaf.managed_service_factory - 6.2.0 | Route Routes: [] starting...
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | BlueprintCamelContext            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Apache Camel 2.15.1.redhat-621084 (CamelContext: msfContext) is starting
       2015-12-11 11:34:33,379 | INFO  | 56-3558afa41c22) | BlueprintCamelContext            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Total 0 routes, of which 0 is started.
       2015-12-11 11:34:33,380 | INFO  | 56-3558afa41c22) | BlueprintCamelContext            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Apache Camel 2.15.1.redhat-621084 (CamelContext: msfContext) started in 0.000 seconds
       2015-12-11 11:34:33,415 | INFO  | 56-3558afa41c22) | BlueprintCamelContext            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Route: helloBryan started and consuming from: Endpoint[timer://helloTimer?fixedRate=true&period=10000]
       2015-12-11 11:34:34,415 | INFO  | mer://helloTimer | helloBryan     | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Hello Bryan
