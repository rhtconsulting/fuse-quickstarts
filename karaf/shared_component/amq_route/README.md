ActiveMQ Routes using Shared Component
====================================
This bundle creates two routes that publish and consume messages to ActiveMQ using a shared ActiveMQ component that is exposed as an OSGi Service. 

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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/shared_component/amq_route/7.6/amq_route-7.6.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.shared_component/amq_route/7.6

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
After running the above commands and installing the `shared_amq` bundle you should see your services started:

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
