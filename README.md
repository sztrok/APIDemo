## **Simple API Demo**

API gathers information about public repositories and branches for given user.

Used technologies: Java 21, Spring Boot 3

Endpoint is set at: **http://localhost:8080/api/get_repositories**

Request body should be in JSON format and contain field "username" with username for which we want to gather information about repositories and branches, and header “Accept: application/json”, for data to be displayed in JSON.

