OSGi Declarative Services
====================================
This builds the feature file for deploying the Camel OSGi Declarative Services Bundles.

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Because this Feature file installs several other bundles, you should first build the following other projects using the command `mvn clean install`.

 * **osgi_ds_api** - Provides the interfaces for OSGi services
 * **osgi_ds_service** - Registers an OSGi service via Declarative Services
 * **osgi_ds_consumer** - A Camel route is created and consumes an OSGi service via Declarative Services


Building
-----------------------
To build the project.

     mvn clean install

This will build the project.

Deploying to JBoss Fuse
-----------------------

To start up Fuse browse to your fuse install directory. Then run

     /bin/fuse

This will bring up the fuse console. Once in the console you will be able to install your bundle. Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2 repository in fuse in the fuse/etc/org.ops4j.pax.url.mvn.cfg file. Then you can use the mvn syntax below.

     karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf.osgi_ds/osgi_ds_feature/6.3/xml/features

     karaf@root> features:install -v camelOsgiDS

 The -v here stands for verbose and is optional.

Results
-----------------------
After running the above commands you should see your services started:

    [ 283] [Active     ] [            ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Declarative Services :: API (6.3)
    [ 284] [Active     ] [            ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Declarative Services :: Shared Service (6.3)
    [ 285] [Active     ] [            ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: OSGi Declarative Services :: Consumer (6.3)


Validate the services are registered and active in the service component registry

    [184 ] [ACTIVE          ] com.redhat.consulting.fusequickstarts.karaf.osgi_ds.route
    [186 ] [ACTIVE          ] com.redhat.consulting.fusequickstarts.karaf.osgi_ds.service


Finally, you should be able to look in `$FUSE_HOME/data/log/fuse.log` and see the following logging:

     2016-02-10 00:08:45,328 | INFO  |  timer://dsTimer | helloServiceLog                  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: I Have a Message: This is a Default Message]
    2016-02-10 00:08:45,329 | INFO  |  timer://dsTimer | helloServiceLog                  | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Hello Jim, How Are you?]

