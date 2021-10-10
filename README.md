# Introduction

A tool to track important moments with exact date time information and attached documents to answer one question: What happened?
All events, moments etc. can be created in a timeline and can be tagged. Later it can be extended to a social platform where people share moments to answer “What happened?” with evidence documents.

Project was developed with spring boot and  MongoDB is preferred for data persistency (working live no change needed). Also it has been dockerized.
For endpoints presentation [Swagger UI](https://swagger.io/tools/swagger-ui/) has been preferred. While project runtime, you could access it from the ip address of the port you use. 
You can also check [Api-Docs](https://github.com/murtekbey/pribas-task/api-docs.json)

### Project's Dependencies

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-mongodb)
* [Swagger 2](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)
* [Lombok](https://projectlombok.org/features/all)
* [Spring Boot Validation](https://www.baeldung.com/spring-boot-bean-validation)

### Layers

* [Api](https://github.com/murtekbey/pribas-task/tree/main/src/main/java/pribas/task/api)
* [Business](https://github.com/murtekbey/pribas-task/tree/main/src/main/java/pribas/task/business)
* [Core](https://github.com/murtekbey/pribas-task/tree/main/src/main/java/pribas/task/core)
* [DataAccess](https://github.com/murtekbey/pribas-task/tree/main/src/main/java/pribas/task/dataAccess)
* [Entities](https://github.com/murtekbey/pribas-task/tree/main/src/main/java/pribas/task/entities)

