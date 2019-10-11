# challenger-api-ecommerce

Ecommerce API, simulates a product purchase and apply interest from the Selic rate
[Spring Boot](http://projects.spring.io/spring-boot/) .

# Spring Boot Application/Challenger-Api-Ecommerce

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* [git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)
* [Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* [Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* [Mockito](https://site.mockito.org/) - Open source testing framework for Java released under the MIT License. The framework allows the creation of test double objects (mock objects) in automated unit tests for the purpose of test-driven development (TDD) or behavior-driven development (BDD).

## To-Do

- [x] RestTemplate
- [x] Spring Boot
- [x] Mockito
- [x] Swagger
- [x] Unit Testing

## Running the application locally

o start the application run the `main` method on the com.ecommerce.api.Application` class of your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Documentation

* [Postman Collection](https://www.getpostman.com/collections/fa4539e0b163420beea8).
* [Swagger](http://localhost:8080/swagger-ui.html) - Documentation & Testing

## Files and Directories

The project has a particular directory structure. A representative project is shown below:

```
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.ecommerce.api
│           ├── com.ecommerce.api.business
│           ├── com.ecommerce.api.configuration
│           ├── com.ecommerce.api.controller
│           ├── com.ecommerce.api.factory
│           ├── com.ecommerce.api.model.commons
│           ├── com.ecommerce.api.model.request
│           ├── com.ecommerce.api.model.response
|           ├── com.ecommerce.api.service   
|           └── com.ecommerce.api.utils
├── src
│   └── test
│       └── java
|          └── java
|             └── com.ecommerce.api.business
|             └── com.ecommerce.api.service
├── JRE System Library
├── Maven Dependencies
├── src
├── target
├── mvnw
├── mvnw.cmd
├── pom.xml
```

## packages

- `model` — to hold our entities;
- `services` — to hold our business logic;
- `factory` — create generic template for integration with other services;
- `controllers` — to listen to the client;
- `business` — created to orchestrate access to the rest services and internal services of api;
- `resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.
- `test/` - contains unit and integration tests
- `pom.xml` - contains all the project dependencies
 
  
## Resources
* [Mockito](https://site.mockito.org/)
* [spring.io](https://spring.io/projects/spring-boot)
