JAX-RS Service with CamelProxy EAP example
====================================
This example deploys two JAX-RS Endpoints to EAP and then uses a CamelProxy to redirect the requests to a Camel Route for Processing.

### Requirements:
 * JBoss Fuse 6.2.1
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

From the management console navigate to the Runtime tab and select 'Management Deployments' on the left hand side. Once here, select 'Add' and browse to your war file. You can either use the one in your .m2 directory or the one in `fuse-quickstarts/eap/jaxrs-proxy/target`. After choosing the war file, click the 'En/Disable' button to start it. 

Alternatively you can deploy your code using the jboss-as-maven-plugin. To do so go into `eap/parent/pom.xml` and change the configuration of the `jboss-as-maven-plugin` to use your management user's `username` and `password` and switch `<skip>` to `false`.  Then run:

     mvn clean install


Testing
-----------------------
Once you have the application deployed, you can test it by using the following URLs

 * http://localhost:8080/jaxrs-proxy/rest/customer - Returns 3 Customers
 * http://localhost:8080/jaxrs-proxy/rest/customer/0 - Returns the customer Bob Winston
 * http://localhost:8080/jaxrs-proxy/rest/customer/4 - Returns Error because the ID does not Exist (And we Coded no Error Handling)


