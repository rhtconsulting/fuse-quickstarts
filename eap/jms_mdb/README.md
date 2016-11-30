Route Deployment Camel EAP JMS MDB example
====================================
This example deploys two basic Camel routes using the Camel Subsystem in EAP. A Message Driven Bean (MDB) is used to move the message to the Consumer route.

### Requirements:
 * JBoss Fuse 6.2.1
 * JBoss EAP 6.4.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8
 * ActiveMQ Resource Adapter (http://activemq.apache.org/resource-adapter.html)

Building
-----------------------
To build the project.

     mvn clean install

This will build the war including the dependencies.

Building and Deploying to JBoss EAP
-----------------------

To start up EAP browse to your EAP install directory. Then run

     /bin/standalone.sh

This will bring up EAP. Once you see logging like this, EAP is up:

     11:08:55,464 INFO  [org.jboss.as] (Controller Boot Thread) JBAS015874: JBoss EAP 6.4.0.GA started in 10870ms -
     Started 151 of 189 services (56 services are lazy, passive or on-demand)

If you do not already have a user set up for the JBoss Management console you can set one up buy running `$EAP_HOME/bin/add-user.sh` in a separate window. It will walk you through the process. Select 'Management user' when given the option. One this is done and EAP is up, navigate to `http://localhost:9990`  and login with your newly created user.

### To Deploy your war:

From the management console navigate to the Runtime tab and select 'Management Deployments' on the left hand side. Once here, select 'Add' and browse to your war file. You can either use the one in your .m2 directory or the one in `fuse-quickstarts/eap/jms_mdb/target`. After choosing the war file, click the 'En/Disable' button to start it.

Alternatively you can deploy your code using the jboss-as-maven-plugin. To do so go into `eap/parent/pom.xml` and change the configuration of the `jboss-as-maven-plugin` to use your management user's `username` and `password` and switch `<skip>` to `false`.  Then run:

     mvn clean install


Results
-----------------------
Once you have the route started you should be able to look in jboss/standalone/log/server.log and see the following logging:

22:13:25,901 INFO  [producer] (Camel (jms-mdb) thread #0 - timer://myTimer) Created Message: Sample AMQ Message
22:13:25,985 INFO  [com.redhat.consulting.fusequickstarts.eap.jms.mdb.MessageDrivenBean] (Thread-2 (HornetQ-client-global-threads-1255435117)) Message rec'd: Sample AMQ Message
22:13:26,024 INFO  [consumer] (Thread-2 (HornetQ-client-global-threads-1255435117)) Received Message: Sample AMQ Message

