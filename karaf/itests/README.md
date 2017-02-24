
Integration Test Suite
====================================

This project is a compilation of Integration Tests designed for testing some of the other Karaf based examples. All of the Integration Tests are written using a combination of [Pax Exam](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Deploying_into_the_Container/PaxExam.html) and the [Camel Test Framework](http://camel.apache.org/camel-test.html).

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or higher (http://maven.apache.org/)
 * Java 8


Tested Bundles
-----------------------
The following Bundles are currently tested bu the Integration Test Suite.

 * [Blueprint - Config Admin Service](https://github.com/rhtconsulting/fuse-quickstarts/tree/master/karaf/properties)
  * This bundle contains a single Pax Exam test that makes use of camel-test's adviceWith functionality to create mock endpoints for testing.
 * [Blueprint - REST DSL](https://github.com/rhtconsulting/fuse-quickstarts/tree/master/karaf/rest_dsl)
  * This bundle contains tests for the provided REST endpoints using Pax Exam. In this case Pax Exam is running in server mode to perform blackbox testing.

Setup
-----------------------
Since all of the tests are Integration Tests and rely on the availability of the Fuse container as a Maven dependency, you will need to download and install JBoss Fuse into your local `.m2` folder or as part of your Maven repository.

First, you should download Fuse from here: [https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=jboss.fuse&downloadType=distributions]

Once you have it downloaded you need to install it in Maven. You can do so using the following Maven command line.

	mvn install:install-file -Dfile=jboss-fuse-6.3.0.GA-full_zip.zip -DgroupId=org.jboss.fuse -DartifactId=jboss-fuse -Dversion=6.3.0.GA -Dpackaging=zip

If you are installing it into a remote repository, use the following Maven coordinates.

	<groupId>org.jboss.fuse</groupId>
	<artifactId>jboss-fuse</artifactId>
	<version>6.3.0</version>
	<packaging>zip</packaging> 

If you are using a different version of Fuse than the one above, then you will need to install it using the same instructions and then update the appropriate fields in the `FuseTestUtil` class.

Running the Tests
-----------------------
you can run the tests using the following command:

	mvn clean install

