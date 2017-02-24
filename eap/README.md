# JBoss Fuse on EAP Quickstarts #
This is a collection of Quick Starts designed to be run on JBoss Fuse on EAP. All of these projects are written using JEE and the Java DSL unless otherwise noted.

## Prerequisites ##
To run these examples you will need to have JBoss EAP 6.4 with JBoss Fuse 6.3 installed on top.

JBoss Fuse must be installed on JBoss EAP 6.4. If you need to install JBoss EAP 6.4, download it from [JBoss EAP 6.4 Installer Download](https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=37383&product=appplatform&version=6.4&downloadType=distributions). See JBoss [EAP 6.4 Installation Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.4/html/Installation_Guide/index.html) for installation instructions.

Once you have EAP 6.4 installed, you can following the steps below to install JBoss Fuse 6.3 on top of EAP.

1. Download the JBoss Fuse 6.3 EAP Installer from [https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=jboss.fuse&downloadType=distributions]
2. Navigate to `$EAP_HOME` of a clean instance of JBoss EAP. `EAP_HOME` refers to the root directory of the Red Hat JBoss Enterprise Application Platform installation on which JBoss Fuse is deployed.
3. Run the installer with the following command:

		java -jar temporary location/fuse-eap-installer-6.3.0.redhat-187.jar

You need to start the JBoss Enterprise Application Platform instance for JBoss Fuse to run. This is because the JBoss Fuse components run on the JBoss Enterprise Application Platform container. You can start the instance by running:

	$EAP_HOME/bin/standalone.sh

Additional installation information can be found here [https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.3/html/installation_on_jboss_eap/]

## Basic Examples ##
These examples are designed to demonstrate a single component or feature without much advanced or complex functionality. This is a good place for beginners to start.

 * [Camel on EAP - Basic Route Deployment w/ CDI](route_deployment)
 * Camel on EAP - JMS using a Message Driven Bean
 * [Camel on EAP - JMS using Camel-JMS](jms)
 * [Camel on EAP - A-MQ using a Message Driven Bean](amq_mdb)
 * Camel on EAP - A-MQ using Camel-ActiveMQ
 * [Camel on EAP - JAX-RS Consumer with the Camel REST DSL](rest_dsl)
 * [Camel on EAP - JAX-RS Consumer with CamelProxy](jaxrs_proxy)
 * Camel on EAP - JAX-WS Consumer with CamelProxy (Contract First)
 * Camel on EAP - JAX-WS Consumer with CamelProxy (Code First)
 * [Camel on EAP - JPA](jpa)

## Advanced Examples ##
These examples build upon the basic examples to demonstrate more advanced features or functionality. These examples assume a basic understanding of Fuse and its components.

 * Camel on EAP - JAX-RS Security
 * Camel on EAP - JAX-WS Security
 * Camel on EAP - JMS Transactions using Camel-JMS
 * Camel on EAP - JNDI Integration
 * Camel on EAP - Infinispan / JBoss Data Grid
 * Camel on EAP - Drools / JBoss BxMS
 * Arquillian Integration Test Suite
