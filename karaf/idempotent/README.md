Camel Idempotent Consumer example
====================================
This example deploys two basic Camel routes using ActiveMQ. Both are deployed using package scanning. The first one produces a simple text message with header and sends it to the queue. The second route uses an Idempotent Consumer with a custom Idempotent Repository to retrieve the messages and eliminate duplicated within a given timeframe.

### Requirements:
 * Red Hat Fuse 7.6
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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/idempotent/7.6/idempotent-<VERSION>.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/idempotent/<VERSION>

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the route started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

	2016-03-16 10:53:11,294 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:53:11,331 | INFO  | IdempotentQueue] | amqConsumerLog                   | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Received Message: Sample AMQ Message]
	2016-03-16 10:53:26,283 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:53:41,284 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:53:56,284 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:54:11,284 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:54:26,283 | INFO  |  timer://myTimer | amqIdempotentProducer            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Created Message: Sample AMQ Message
	2016-03-16 10:54:26,301 | INFO  | IdempotentQueue] | amqConsumerLog                   | 198 - org.apache.camel.camel-core - 2.15.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Received Message: Sample AMQ Message]
	
From analyzing the logs you can see that the First message is consumed, though the next few are skipped because they have the same appId Header value and are still within 60 seconds. Once the 60 seconds passes and the cache expires that key, only then is the next message accepted.

Inside the console you can also run and see the following:

     JBossFuse:karaf@root> activemq:dstat
     Name                                                Queue Size  Producer #  Consumer #   Enqueue #   Dequeue #   Forward #
     ActiveMQ.Advisory.Connection                                 0           0           0           1           0           0
     ActiveMQ.Advisory.Consumer.Queue.myIdempotentQueue           0           0           0           1           0           0
     ActiveMQ.Advisory.MasterBroker                               0           0           0           1           0           0
     myQueue                                                      0           0           1           6           6           0

You can see our queue "myIdempotentQueue" as well as some amq defaults.

