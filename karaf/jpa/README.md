Camel JPA
====================================
This bundle makes use of container managed JPA with Hibernate in a Bean.

### Requirements: ###

 * Red Hat Fuse 7.6.0 or Greater
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Before you can get started with this example, you need to install several features and bundles. Run the following commands to ensure the proper requirements are installed.

	features:install jndi
	features:install jpa
	features:install hibernate
	features:install camel-jpa
	install -s mvn:com.h2database/h2/1.3.163
	
Once you have the above features installed, you should copy the `datasource.xml` file from the project to the `$FUSE_HOME\deploy` folder to create the Data source.
	

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

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/jpa/7.6/jpa-7.6.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/jpa/7.6

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Results
-----------------------
Once you have the bundle deployed and started you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

	2016-02-17 17:45:38,886 | INFO  | timer://jpaTimer | timerToJpa                       | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Generating New Person
	2016-02-17 17:45:38,888 | INFO  | timer://jpaTimer | timerToJpa                       | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Saving Person
	2016-02-17 17:45:38,932 | INFO  | timer://jpaTimer | timerToJpa                       | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Saved Person: Person [id=1, name=SomeName]
	2016-02-17 17:45:38,947 | INFO  | jpa.model.Person | jpaToLog                         | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Retrieved Person: Person [id=1, name=SomeName]


Troubleshooting
-----------------------

### Unresolved constraint in bundle com.redhat.consulting.fusequickstarts.karaf.jpa [265]: Unable to resolve 265.0: missing requirement [265.0] osgi.wiring.package; (&(osgi.wiring.package=org.apache.camel.component.jpa)(version>=2.17.0)(!(version>=3.0.0)))

To resolve this issue, you need to run the following command:

	features:install camel-jpa


### waiting for namespace handlers [http://aries.apache.org/xmlns/jpa/v1.0.0]

To resolve this issue, you need to run the following command:

	features:install jpa


### Cant find H2

To resolve this issue, you need to run the following command:

	install -s mvn:com.h2database/h2/1.3.163


### waiting for dependencies [(&(&(!(org.apache.aries.jpa.proxy.factory=*))(osgi.unit.name=camelJpaContainer))(objectClass=javax.persistence.EntityManagerFactory))]

To resolve this issue, you need to run the following command:

	features:install hibernate


### Error creating EntityManagerFactory java.lang.RuntimeException: The DataSource osgi:service/javax.sql.DataSource/(osgi.jndi.service.name=jdbc/h2ds) required by bundle com.redhat.consulting.fusequickstarts.karaf.jpa/7.6 could not be found.

To resolve this issue, you need to run the following command:

	features:install jndi
