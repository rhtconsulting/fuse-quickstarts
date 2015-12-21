Using Blueprint Annotations instead of XML
====================================
A small application to manage a list of tasks using Blueprint Annotations instead of handwritten xml.

The blueprint-maven-plugin allows you to use a subset of the JEE annotations in your source code and creates standard Blueprint XML from it. This allows you to build an example with JPA persistence, Transactions and a Servlet UI using zero hand written blueprint xml.

This example shows how to:

 * Create DataSources using pax-jdbc
 * Create bundles using maven and the maven bundle plugin
 * Wire bundles using CDI annotations and OSGi services
 * Write JPA DAO classes like in JEE using @PersistenceContext and @Transactional

Based on work by [Christian Schneider](https://github.com/cschneider/Karaf-Tutorial/tree/master/tasklist-blueprint-cdi).

### Requirements:
 * JBoss Fuse 6.2.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8


Bundles
-----------------------
The following bundles are included in this project.

 * TBD


Setup
-----------------------
TBD


Building
-----------------------
To build the project.

     mvn clean install

This will build all of the bundles and features needed for the examples.


Additional Reading
-----------------------
If you are looking for more information about integrating JBoss Data Grid and JBoss Fuse, check out the following documentation.

 * TBD

Troubleshooting
-----------------------
TBD
