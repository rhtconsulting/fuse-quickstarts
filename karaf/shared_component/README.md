Using a Shared Camel Component
====================================
This project demonstrates how to expose a configured Camel Component (ActiveMQ in this case) as an OSGi Service and then use it in another

### Requirements:
 * Red Hat Fuse 7.6.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Bundles
-----------------------
This project includes the following bundles:

 * **shared_amq** - This bundle configures the ActiveMQ component and exposes it as an OSGi service for other bundles to use.
 * **amq_routes** - This bundle includes two Camel routes that utilize the shared ActiveMQ component.
 * **shared_amq_feature** - This bundle builds the Feature file used to install the project.

Building
-----------------------
To build the project.

       mvn clean install

This will build the project.

Deploying to Red Hat Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

       /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

       karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf.shared_component/shared_amq_feature/7.6/xml/features

       karaf@root> features:install -v sharedAmqExample

The -v here stands for verbose and is optional.

Results
-----------------------
After running the above commands you should see your services started:

  	[ 286] [Active     ] [Created     ] [       ] [   80] Fuse Quick Start :: Karaf :: Blueprint :: Shared Component :: OSGI Shared A-MQ (ActiveMQ) Routes (7.6)
  	[ 287] [Active     ] [Created     ] [       ] [   80] Fuse Quick Start :: Karaf :: Blueprint :: Shared Component :: OSGI Shared A-MQ (ActiveMQ) Component (7.6)

Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

  	2015-11-19 11:53:44,712 | INFO  |  timer://myTimer | sharedAmqProducer   | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Created Message: Shared Sample AMQ Message
  	2015-11-19 11:53:44,718 | INFO  | r[mySharedQueue] | amqConsumerLog      | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Received Message: Shared Sample AMQ Message]


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

  If you get this error it means that you need to build the missing Quick Start so that it will be installed into your local maven repository. Otherwise the Feature will not be able to locate the bundle to install it.
