Multi-tenant Spring Data with MySQL.
Inspired by the article:
[Schema-based multi-tenancy with Spring Data, Hibernate and Flyway](https://sultanov.dev/blog/schema-based-multi-tenancy-with-spring-data/)

Use the following curl commands to test:
1. curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"jane\",\"password\":\"qwerty123\"}" http://localhost:8080/api/default_tenant/users
2. curl -u jane:qwerty123 -X POST -H "Content-Type: application/json" -d "{\"text\":\"Hello from Jane!\"}" http://localhost:8080/api/jane/notes
3. curl -u jane:qwerty123 http://localhost:8080/api/jane/notes