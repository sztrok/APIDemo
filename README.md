## **Simple API Demo**

API gathers information about public repositories and branches for given user.

Used technologies: Java 21, Spring Boot 3

GET Endpoint is set at: **http://localhost:8080/api/repository/all**

Request body should be in JSON format like:
```json
{
  "username":"string"
}
```
Request should also contain header `Accept: application/json`.

