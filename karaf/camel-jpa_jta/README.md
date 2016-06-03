
Prerequisite:
```bash
docker run -d --name fuse-postgresql-server -e POSTGRES_USER=fuse -e POSTGRES_PASSWORD=fuse -e POSTGRES_DB=jpa -p 5432:5432 postgres:9.4
```

To install:

```bash
source mvn:com.redhat.consulting.fusequickstarts.karaf/jpa/6.2.1/karaf
```

To check:

```bash
docker exec -it fuse-postgresql-server psql -U fuse -d jpa -c "select * from Person;"

```
