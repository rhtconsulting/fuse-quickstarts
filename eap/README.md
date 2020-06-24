# Red Hat Fuse on EAP Quick Starts #
This is a collection of Quick Starts designed to be run on Red Hat Fuse on EAP. All of these projects are written using JEE and the Java DSL unless otherwise noted.

## Prerequisites ##
To run these examples you will need to have JBoss EAP 7.2 with Red Hat Fuse 7.6 installed on top.

Red Hat Fuse must be installed on JBoss EAP 7.2. If you need to install JBoss EAP 7.2, download
it from [JBoss EAP 7.2 Installer Download](https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=37383&product=appplatform&version=7.2&downloadType=distributions).
See JBoss [EAP 7.2 Installation Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/7.2/html/Installation_Guide/index.html)
for installation instructions.

Once you have EAP 7.2 installed, you can follow the steps below to install Red Hat Fuse 7.6 on top of EAP.

1. Download the `Red Hat Fuse 7.6.0 on EAP Installer` from [https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=jboss.fuse&downloadType=distributions]
2. Navigate to `$EAP_HOME` of a clean instance of JBoss EAP. `EAP_HOME` refers to the root directory of the Red Hat JBoss Enterprise Application Platform installation on which Red Hat Fuse is deployed.
3. Run the installer with the following command:

		java -jar temporary location/fuse-eap-installer-7.6.0.jar

You need to start the JBoss Enterprise Application Platform instance for Red Hat Fuse to run. This is because the Red Hat Fuse components run on the JBoss Enterprise Application Platform container. You can start the instance by running:

	$EAP_HOME/bin/standalone.sh

Additional installation information can be found [here](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.6/html/installing_on_jboss_eap/).

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
