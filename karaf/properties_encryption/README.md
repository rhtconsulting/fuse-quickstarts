
OSGi Config Admin Service with Jasypt Encryption example
====================================

 This project is a simple Camel route the pulls encrypted properties from the OSGi Config Admin Service and then logs the decrypted value. It is an osgi bundle that can be installed on Fuse Karaf. It is very minimal and configured with Blueprint.

### Requirements:
 * JBoss Fuse 6.3.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------

To build the project.

     mvn clean install

This will build the bundle including the manifest information.

Setup
-----------------------
First Download and install [Jasypt](http://jasypt.org/download.html), to gain access to the Jasypt `listAlgorithms.sh`, `encrypt.sh` and `decrypt.sh` command-line tools. When installing the Jasypt command-line tools, don't forget to enable execute permissions on the script files, by running `chmod u+x ScriptName.sh`.

Use the Jasypt encrypt command-line tool to encrypt your sensitive configuration values (for example, passwords for use in configuration files). For example, the following command encrypts the SecretMessage value, using the specified algorithm and master password password:

	$ ./encrypt.sh input="SecretMessage" algorithm=PBEWithMD5AndDES password=password
	ENVIRONMENT
	Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 24.71-b01

	ARGUMENTS
	algorithm: PBEWithMD5AndDES
	input: SecretMessage
	password: password

	OUTPUT
	WSajBRcfsf5vH5BE02510QfrRi81wnUD

Copy the file `src\test\resources\com.redhat.consulting.fusequickstarts.karaf.properties.enc.cfg` to `$FUSE_ROOT\etc`. This is where all of the Fuse configuration files are stored and will allow Fuse to read your file into the Config Admin Service. After you have copied the file set the `test.message.enc` value in the file to your encrypted output String wrapped in `ENC(...)`. For example `ENC(WSajBRcfsf5vH5BE02510QfrRi81wnUD)`.

Make sure that the `jasypt-encryption` feature is installed in the container. If necessary, install the jasypt-encryption feature with the following console command:

	JBossFuse:karaf@root> features:install jasypt-encryption

Shut down the Fuse container.

Set `$JASYPT_ENCRYPTION_PASSWORD` Environment Variable from the command line and then start Fuse.

	export JASYPT_ENCRYPTION_PASSWORD="password"
	./bin/fuse

At this point Fuse is configured for using Jasypt. Follow the steps in the next section for instructions on how to deploy and test the code.

> **Note:** In a production (or possible even QA/Test) environment you should unset the `$JASYPT_ENCRYPTION_PASSWORD` Environment Variable after starting Fuse. This ensures there will be minimum risk of exposing the master password. The Jasypt library retains the master password in encrypted form in memory.

Deploying to JBoss Fuse
-----------------------
Usually we would install multiple bundles using a feature file, but in this case since we only have one bundle to install we can just install it using the file by the following command. Another option is to set up your local m2  repository in fuse in the `fuse/etc/org.ops4j.pax.url.mvn.cfg` file.  Then you can use the mvn syntax below.

     karaf@root> osgi:install -s file:/home/yourUser/.m2/repository/com/redhat/consulting/fusequickstarts/karaf/properties-encrypted/6.3/properties-encrypted-6.3.jar
        OR
     karaf@root> osgi:install -s mvn:com.redhat.consulting.fusequickstarts.karaf/properties-encrypted/6.3

 The -s here indicates to also start the bundle.  Alternatively you can omit the -s and after the install run

     karaf@root> osgi:start <bundleId>

Testing
-----------------------
Once you have the route started you should be able to look in fuse/data/log/fuse.log and see the following logging:

    2015-11-04 16:12:23,504 | INFO  |  timer://myTimer | EncryptedPropertiesLog     | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Displaying Injected Property 'message': SecretMessage]
	2015-11-04 16:12:23,505 | INFO  |  timer://myTimer | EncryptedPropertiesLog     | ?    ? | 198 - org.apache.camel.camel-core - 2.17.1.redhat-620133 | Exchange[ExchangePattern: InOnly, BodyType: String, Body: Reading Property 'test.message.enc': SecretMessage]

Additional Reading
-----------------------
Additional information about Encrypting Properties for Fuse using Jasypt can be found here:

 * [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Security_Guide/FMQSecurityEncryptProperties.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Security_Guide/FMQSecurityEncryptProperties.html)
 * [https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-Jasypt.html](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/IDU-Jasypt.html)
