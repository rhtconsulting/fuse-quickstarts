Shared AMQ Component Feature
====================================
This builds the feature file for deploying the Shared AMQ Component and Route Bundles.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Because this Feature file installs several other bundles, you should first build the following other projects using the command `mvn clean install`.

 * shared_amq
 * amq_routes


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

     karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf.shared_component/shared_amq_feature/1.0.0/xml/features
       
     karaf@root> features:install -v sharedAmqExample

 The -v here stands for verbose and is optional. 

Results
-----------------------
After running the above commands you should see your services started:

	[ 286] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: Shared Component :: OSGI Shared A-MQ (ActiveMQ) Routes (1.0.0)
	[ 287] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: Shared Component :: OSGI Shared A-MQ (ActiveMQ) Component (1.0.0)

Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

	2015-11-19 11:53:44,712 | INFO  |  timer://myTimer | sharedAmqProducer   | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Created Message: Shared Sample AMQ Message
	2015-11-19 11:53:44,718 | INFO  | r[mySharedQueue] | amqConsumerLog      | ?    ? | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Received Message: Shared Sample AMQ Message]


Inside the console you can also run and see the following:

     JBossFuse:karaf@root> activemq:dstat
     Name                                                Queue Size  Producer #  Consumer #   Enqueue #   Dequeue #   Forward #
     ActiveMQ.Advisory.Connection                                 0           0           0           1           0           0
     ActiveMQ.Advisory.Consumer.Queue.myQueue                     0           0           0           1           0           0
     ActiveMQ.Advisory.MasterBroker                               0           0           0           1           0           0
     mySharedQueue                                                0           0           1           6           6           0

You can see our queue "mySharedQueue" as well as some amq defaults.
     
Troubleshooting
-----------------------

**Error resolving artifact com.redhat.consulting.fusequickstarts.karaf**

If you get this error it means that you need to build the missing quickstart so that it will be installed into your local maven repository. Otherwise the Feature will not be able to locate the bundle to install it.


