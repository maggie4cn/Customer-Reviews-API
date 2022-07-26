# Customer Reviews API Introduction
* Customer Reviews API is a REST based API that supports the customer reviews section in a product page in ecommerce application. 
* This API supports operation for writing and listing reviews and comments to relationational MySQL database and non-relational MongoDB database for products.
* Built both Mongo DB and MySQL part of persistence layer for the REST API that supports writing and listing customer reviews in an e-commerce application.

### Buiding Steps
* Configure the MySQL Datasource in application.properties.
* Add Flyway scripts in src/main/resources/db/migration.
* Define JPA Entities and relationships.
* Define Spring Data JPA Repositories.
* Define Spring Data Mongo Repositories.
* Add tests for JPA Repositories using MySQL.
* Add tests for Mongo Repositories using Embedded Mongo.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
