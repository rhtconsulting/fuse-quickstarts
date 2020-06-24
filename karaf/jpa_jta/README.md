Build Application

Start Fuse

Install Dependencies
````
features:install jndi jpa hibernate camel-jpa transaction connector
install -s mvn:com.h2database/h2/1.3.163
install -s blueprint:mvn:com.redhat.consulting.fusequickstarts.karaf/camel-jpa-jta/7.6/xml/h2
````

Install Application
````
install -s mvn:com.redhat.consulting.fusequickstarts.karaf/camel-jpa-jta/7.6
````
