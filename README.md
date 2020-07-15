# Gitdate Backend with Github Oauth

This repo to be used as a template for a gitdate application backend

Use the [Gitdate Frontend Template](https://github.com/mmuzio/gitdate-template "Gitdate Template") with this template to have register, login, view profile, connect, and view matches functionality working right out of the box!

This template is built with Spring Boot. It uses Hibernate and Spring Data for data access operations. It has an AOP configuration for logging all repository, service, and controller method calls.

This project took inspiration (and a lot of code!) from the following repositories:

- [Spring Security with Github Oauth](https://github.com/callicoder/spring-boot-react-oauth2-social-login-demo/tree/master/spring-social "Spring Security with Github Oauth")
  - Most of the application architecture taken from this repo
- [Microservice Framework for AOP Monitoring](https://github.com/IBM/microsvcengineering/tree/master/microsvcframework "Microservice Framework for AOP Monitoring")
  - The com.example.springsocial.monitoring package was based on the com.ibm.dip.microsvcengineering.framework.monitoring package in this repo
  
### Setup

1. Create a github oauth application
2. Copy the client id and client secret
3. Update application.yml with your id and secret
4. Generate an application secret for your application
5. Update application.yml with your app's secret
6. Update your datasource properties. If you want to use the current properties, you must create a postgres database instance with name socialtesta and postgres as username/password 

### Functionality

This template has 5 primary functions:

1. Register
2. Login
3. View Profile
3. Connect - like or dislike other users
4. View Matches

### Running the app

You can run on localhost as you would with run any Spring Boot Application, using a local database. 

### Swagger

You can inspect the api documentation using Swagger. To do this you must:

1. Run the application using the previous steps

2. navigate to http://localhost:8080/swagger-ui.html#/
