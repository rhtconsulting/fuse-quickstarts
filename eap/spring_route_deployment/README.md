Spring Route Deployment Camel EAP example
====================================
This example deploys two basic Camel routes using the Camel Subsystem in EAP.

### Requirements:
 * JBoss Fuse 6.3.0
 * JBoss EAP 6.4.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

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

From the management console navigate to the Runtime tab and select 'Management Deployments' on the left hand side. Once here, select 'Add' and browse to your war file. You can either use the one in your .m2 directory or the one in `fuse-quickstarts/eap/spring_route_deployment/target`. After choosing the war file, click the 'En/Disable' button to start it. 

Alternatively you can deploy your code using the jboss-as-maven-plugin. To do so go into `eap/parent/pom.xml` and change the configuration of the `jboss-as-maven-plugin` to use your management user's `username` and `password` and switch `<skip>` to `false`.  Then run:

     mvn clean install


Results
-----------------------
Once you have the route started you should be able to look in jboss/standalone/log/server.log and see the following logging:

	14:39:21,130 INFO  [com.redhat.consulting.fusequickstarts.eap.deployment.route] (Camel (spring-camel-eap) thread #1 - timer://myEapTimer) Hello World
	14:39:21,135 INFO  [HelloWorldLog] (Camel (spring-camel-eap) thread #1 - timer://myEapTimer) Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
	14:39:21,135 INFO  [GoodbyeWorldLog] (Camel (spring-camel-eap) thread #0 - timer://myOtherEapTimer) Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
	14:39:23,109 INFO  [com.redhat.consulting.fusequickstarts.eap.deployment.route.scan] (Camel (spring-camel-eap) thread #0 - timer://myOtherEapTimer) Goodbye World

