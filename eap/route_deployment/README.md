Route Deployment Camel EAP example
====================================
This example deploy two basic Camel routes. They are deployed using Package Scanning.

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------
To build the project.

     mvn clean install

This will build the war including the dependencies.

Deploying to JBoss EAP
-----------------------

To start up EAP browse to your EAP install directory. Then run

     /bin/standalone.sh

This will bring up eap. Once you see logging like this, eap is up:

     11:08:55,464 INFO  [org.jboss.as] (Controller Boot Thread) JBAS015874: JBoss EAP 6.4.0.GA started in 10870ms - 
     Started 151 of 189 services (56 services are lazy, passive or on-demand)

If you do not already have a user set up for the JBoss Management console you can do so, but running /bin/add-user.sh in a separate window.  It will walk you through the process.  Select 'Mangement user' when given the option. One this is done and EAP is up, navigate to http://localhost:9990  and login with your newly created user. From there navigate to the Runtime tab and select 'Management Deployments' on the left hand side.  

Once here, select 'Add' and browse to your war file.  You can either use the one in your .m2 directory or the one in fuse-quickstarts/eap/route_deployment/target. After choosing the war file, click the 
'En/Disable' button to start it. 

Results
-----------------------
Once you have the route started you should be able to look in jboss/standalone/log/server.log and see the following logging:

     | INFO  |  timer://myTimer | route                            | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Hello World
     | INFO  |  timer://myTimer | HelloWorldLog                    | 198 - org.apache.camel.camel-core - 2.15.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
