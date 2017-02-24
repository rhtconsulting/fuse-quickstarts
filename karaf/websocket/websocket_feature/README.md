WebSocket Feature
====================================
This builds the feature file for deploying the WebSocket Route Bundle and required dependencies

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Setup
-----------------------
Because this Feature file installs several other bundles, you should first build the following other projects using the command `mvn clean install`.

 * websocket-route


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

     karaf@root> features:addurl mvn:com.redhat.consulting.fusequickstarts.karaf.websocket/websocket_feature/6.3/xml/features
     karaf@root> features:install -v fuseWebSocket

 The -v here stands for verbose and is optional.

Results
-----------------------
After running the above commands you should see your services started:

    [ 286] [Active     ] [            ] [       ] [   50] camel-websocket (2.17.1.redhat-621084)
    [ 287] [Active     ] [Created     ] [       ] [   80] Fuse Quickstart :: Karaf :: Blueprint :: Websocket :: Route (6.3)

Testing
---------------------

Start the web based chat application by launching a web browser and navigating to [http://localhost:9090](http://localhost:9090).

Enter a username and click **join** to start the chat session. Enter some text and click **send** to produce a message that will be consumed by Fuse and sent to all parties currently connected to the chat.

Open a second browser window to simulate multiple users. 

The Fuse log will display a result similar to the following output:

    2016-02-11 21:50:54,268 | INFO  | qtp774858011-119 | websocketChat                    | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | >> Message received : joe:has joined!
    2016-02-11 21:51:33,598 | INFO  | qtp774858011-119 | websocketChat                    | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | >> Message received : mary:has joined!
    2016-02-11 21:51:42,434 | INFO  | qtp774858011-120 | websocketChat                    | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | >> Message received : joe:hi mary
    2016-02-11 21:51:52,406 | INFO  | qtp774858011-117 | websocketChat                    | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | >> Message received : mary:hi joe, how are you?
    2016-02-11 21:52:01,837 | INFO  | qtp774858011-118 | websocketChat                    | 198 - org.apache.camel.camel-core - 2.17.1.redhat-621084 | >> Message received : joe:doing fine, thanks for asking
