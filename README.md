# MSIL Appointment Service

Production-style Java 21 and Spring Boot 3.5.7 appointment microservice with Controller, Service, ServiceImpl, Repository, Entity/POJO, DTOs, Mapper, Exceptions, Swagger, H2, PostgreSQL driver, tests, Docker, and Postman collection.

## Run in VS Code

1. Install JDK 21, Maven 3.9+, Extension Pack for Java, and Spring Boot Extension Pack.
2. Open this folder in VS Code.
3. Run `mvn clean test`.
4. Run `mvn spring-boot:run`.

## URLs

- API base: http://localhost:8081/api/v1/appointments
- Swagger UI: http://localhost:8081/swagger-ui.html
- H2 console: http://localhost:8081/h2-console
- Health: http://localhost:8081/actuator/health

H2 login: JDBC URL `jdbc:h2:mem:appointments`, user `sa`, blank password.

## Postman

Import `postman/MSIL-Appointment-Service.postman_collection.json` and execute requests in numeric order.

## APIs

- POST `/api/v1/appointments`
- GET `/api/v1/appointments/{id}`
- GET `/api/v1/appointments?page=0&size=20&status=PENDING_ASSIGNMENT`
- PUT `/api/v1/appointments/{id}`
- PATCH `/api/v1/appointments/{id}/driver`
- DELETE `/api/v1/appointments/{id}`

## PostgreSQL

The PostgreSQL JDBC driver is included. For deployment, replace the H2 datasource URL, username and password with environment-specific PostgreSQL configuration just for testing.

## DeveloperWorkflow feature --> CHand 4 demo to Navin and Rohit 2 now  demo with priya Second attempt . Now with Neeraj preparing for demo
 
