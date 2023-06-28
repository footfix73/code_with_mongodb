# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.example.code-with-mongodb' is invalid and this project uses 'com.example.codewithmongodb' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#messaging.kafka)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.nosql.mongodb)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.nosql.mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

````
curl --location 'http://localhost:8080/addUser' \
--header 'Content-Type: application/json' \
--data '{
  "userId":"U016",
  "firstName":"Joao",
  "lastName":"Carrisso"
}'
````

````
curl --location 'http://localhost:8080/getAllUser'
````

````
curl --location 'http://localhost:8080/getAllFirstName/Joao'
````

````
curl --location 'http://localhost:8080/users?firstName=Joao&page=4&size=3'
````

````
http://localhost:8080/getAllFirstNameByLetter?letter=Jos&page=0&size=3
````