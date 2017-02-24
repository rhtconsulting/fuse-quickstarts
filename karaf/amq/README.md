Camel ActiveMQ OSGi example
====================================
This example deploys two basic Camel routes using ActiveMQ. Both are deployed using package scanning. The first one produces a simple text message and sends it to the queue. The second route picks up the message from the queue and logs it. 

### Requirements:
 * JBoss Fuse 6.3.0
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

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/amq/6.3/amq-6.3.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/amq/6.3

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

	2015-11-18 13:38:26,204 | INFO  |  timer://myTimer | route4  | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Created Message: Sample Message
	2015-11-18 13:38:26,206 | INFO  | onsumer[myQueue] | route3  | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620153 | Received Message: Sample Message

Inside the console you can also run and see the following:

     JBossFuse:karaf@root> activemq:dstat
     Name                                                Queue Size  Producer #  Consumer #   Enqueue #   Dequeue #   Forward #
     ActiveMQ.Advisory.Connection                                 0           0           0           1           0           0
     ActiveMQ.Advisory.Consumer.Queue.myQueue                     0           0           0           1           0           0
     ActiveMQ.Advisory.MasterBroker                               0           0           0           1           0           0
     myQueue                                                      0           0           1           6           6           0

You can see our queue "myQueue" as well as some amq defaults.

