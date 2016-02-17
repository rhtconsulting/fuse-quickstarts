Camel JPA
====================================
This bundle makes use of container managed JPA with Hibernate in a Bean.

### Requirements: ###

 * JBoss Fuse 6.2.0 or Greater
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Before you can get started with this example, you need to install the 

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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/jpa/hibernate-container/1.0.0/hibernate-container-1.0.0.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf.infinispan/local-cache/1.0.0

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:


Troubleshooting
-----------------------

Unresolved constraint in bundle com.redhat.consulting.fusequickstarts.karaf.jpa [265]: Unable to resolve 265.0: missing requirement [265.0] osgi.wiring.package; (&(osgi.wiring.package=org.apache.camel.component.jpa)(version>=2.15.0)(!(version>=3.0.0)))

Install camel-jpa


waiting for namespace handlers [http://aries.apache.org/xmlns/jpa/v1.0.0]

Install jpa


Cant find H2

install -s mvn:com.h2database/h2/1.3.163


waiting for dependencies [(&(&(!(org.apache.aries.jpa.proxy.factory=*))(osgi.unit.name=camelJpaContainer))(objectClass=javax.persistence.EntityManagerFactory))]

Install hibernate


Error creating EntityManagerFactory java.lang.RuntimeException: The DataSource osgi:service/javax.sql.DataSource/(osgi.jndi.service.name=jdbc/h2ds) required by bundle com.redhat.consulting.fusequickstarts.karaf.jpa/6.2.1 could not be found.

Install jndi
