# taskmanager 
# 🗂️ Task Manager API

A simple Spring Boot REST API to manage users, tasks, comments, and task status updates — secured with JWT and connected to PostgreSQL.

---

## 👤 Author

**Manas**  
[GitHub Profile](https://github.com/ggmanYr765)

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- PostgreSQL
- Maven

---

## 🚀 Run Locally

Make sure PostgreSQL is running locally and you've created a database named `taskmanager`.

### 🔧 Setup `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


# From project root
mvnw.cmd spring-boot:run

Then go to(SWAGGER UI): http://localhost:8080 

API ENDPOINTS:

| Method | Endpoint       | Description     |
| ------ | -------------- | --------------- |
| POST   | /auth/register | Register a user |
| POST   | /auth/login    | Login & get JWT |
| POST   | /tasks         | Create a task   |
| GET    | /tasks         | View all tasks  |
| PUT    | /tasks/{id}    | Update a task   |
| DELETE | /tasks/{id}    | Delete a task   |

Testing with Postman:

Register → POST /auth/register
Login → POST /auth/login
Copy the token from the response
Use the token in Authorization > Bearer Token for all /tasks endpoints

License:

This project is licensed under the MIT License.



