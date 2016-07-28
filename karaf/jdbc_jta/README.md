Build Application

- copy `src/main/resources/datasources/create.sql` in your user folder.

Start Fuse

Install Dependencies
```bash
# IMPORTANT: "connector" feature required to have rollback to work, otherwise rollback will fail without reporting any errors!
features:install jndi camel-jdbc transaction connector
# note: older version of h2db had a bug that prevented rollback to work. A version that shows the bugged behavior is 1.3.163
# see: https://groups.google.com/d/msg/h2-database/GJJVQbyHOJQ/lwMzO9OUSjMJ
install -s mvn:com.h2database/h2/1.4.192
install -s blueprint:mvn:com.redhat.consulting.fusequickstarts.karaf/jdbc-jta/6.2.1/xml/h2
```

Install Application
```bash
install -s mvn:com.redhat.consulting.fusequickstarts.karaf/jdbc-jta/6.2.1
```
