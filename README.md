# LibrarySystem — Book & User REST API

A Spring Boot REST API for managing books and users, built with Java 21, Spring Data JPA, H2 in-memory database, and Spring Security.

---

## Tech Stack

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.4 |
| Spring Data JPA | via Boot BOM |
| Spring Security | via Boot BOM |
| H2 Database | in-memory |
| Gradle | 8.8 |
| Lombok | 1.18.30 |
| JUnit Jupiter | via Boot BOM |

---

## Project Structure

```
app/src/main/java/org/example/
├── App.java                              # Entry point
├── Entity/
│   ├── Book.java                         # JPA Entity
│   └── User.java                         # JPA Entity
├── Dto/
│   ├── CreateBookRepositoryDto.java
│   ├── CreateUserRepositoryDto.java
│   └── ExceptionResponseDto.java
├── Repository/
│   ├── BookRepository.java               # Spring Data repository
│   └── UserRepository.java               # Spring Data repository
├── Service/
│   ├── BookService.java
│   └── UserService.java
├── controller/
│   ├── BookController.java               # Book REST endpoints
│   ├── UserController.java               # User REST endpoints
│   └── HomeController.java               # Home endpoint
├── ErrorHandling/
│   ├── BookNotFoundException.java        # 404 exception
│   ├── BookIdMismatchException.java      # 409 exception
│   ├── BookIdMissingException.java       # 404 exception
│   ├── UserNotFoundException.java        # 404 exception
│   ├── UserIdMissingException.java       # 404 exception
│   ├── DuplicateResourceException.java   # 409 exception
│   └── RestExceptionHandler.java         # Global exception handler
└── Security/
    └── HomeSecurity.java                 # Security config
```

---

## Getting Started

### Prerequisites
- Java 21
- Gradle 8.8 (or use the included `./gradlew` wrapper)

### Run the app

```bash
./gradlew bootRun
```

The app starts on **http://localhost:8081**

### Run the tests

```bash
./gradlew test
```

---

## Book API

Base URL: `http://localhost:8081/api/books`

### Get all books
```
GET /api/books
```
```bash
curl http://localhost:8081/api/books
```
Response `200 OK`:
```json
[
  {"id": 1, "title": "Clean Code", "author": "Robert Martin", "issuedBy": false, "createdAt": "2026-07-17T10:00:00"}
]
```

---

### Get book by ID
```
GET /api/books/{id}
```
```bash
curl http://localhost:8081/api/books/1
```
Response `404 Not Found` if ID doesn't exist.

---

### Get book by title
```
GET /api/books/title/{bookTitle}
```
```bash
curl http://localhost:8081/api/books/title/Clean%20Code
```

---

### Get book by author
```
GET /api/books/author/{author}
```
```bash
curl http://localhost:8081/api/books/author/Robert%20Martin
```

---

### Check if a book is issued
```
GET /api/books/{title}/issue
```
```bash
curl http://localhost:8081/api/books/Clean%20Code/issue
```
Response `200 OK`: `true` or `false`

---

### Create a book
```
POST /api/books
```
```bash
curl -X POST http://localhost:8081/api/books \
  -H "Content-Type: application/json" \
  -d '{"title": "Clean Code", "author": "Robert Martin", "issuedBy": false}'
```
Response `201 Created`. Response `409 Conflict` if a book with the same title already exists.

---

### Update a book
```
PUT /api/books/{id}
```
```bash
curl -X PUT http://localhost:8081/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "title": "Clean Code (2nd Ed)", "author": "Robert Martin", "issuedBy": true}'
```

---

### Delete a book
```
DELETE /api/books/{id}
```
```bash
curl -X DELETE http://localhost:8081/api/books/1
```
Response `204 No Content`.

---

## User API

Base URL: `http://localhost:8081/api/user`

### Get all users
```
GET /api/user
```
```bash
curl http://localhost:8081/api/user
```

---

### Get user by ID
```
GET /api/user/{id}
```
```bash
curl http://localhost:8081/api/user/1
```
Response `404 Not Found` if ID doesn't exist.

---

### Get user by username
```
GET /api/user/username/{name}
```
```bash
curl http://localhost:8081/api/user/username/jdoe
```

---

### Create a user
```
POST /api/user
```
```bash
curl -X POST http://localhost:8081/api/user \
  -H "Content-Type: application/json" \
  -d '{"username": "jdoe", "name": "John Doe", "issuedBook": false}'
```
Response `201 Created`. Response `409 Conflict` if the username already exists.

---

### Update a user
```
PUT /api/user/{id}
```
```bash
curl -X PUT http://localhost:8081/api/user/1 \
  -H "Content-Type: application/json" \
  -d '{"username": "jdoe", "name": "John Doe", "issuedBook": true}'
```

---

### Delete a user
```
DELETE /api/user/{id}
```
```bash
curl -X DELETE http://localhost:8081/api/user/1
```
Response `204 No Content`.

---

## Error Responses

| HTTP Status | When |
|---|---|
| `404 Not Found` | Book/user ID or lookup value does not exist |
| `409 Conflict` | Duplicate title/username, or ID in URL doesn't match ID in request body |
| `500 Internal Server Error` | Constraint violations or other unhandled runtime errors |

---

## Dependencies

```kotlin
// Testing
testImplementation("org.springframework.boot:spring-boot-starter-test")

// Core
implementation("org.springframework.boot:spring-boot-starter-web")
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
implementation("org.springframework.boot:spring-boot-starter-security")
implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
compileOnly("org.projectlombok:lombok:1.18.30")
annotationProcessor("org.projectlombok:lombok:1.18.30")

// Database
runtimeOnly("com.h2database:h2")
```

---

## Notes

- The database is **in-memory (H2)** — all data resets when the app restarts.
- Spring Security is configured to **permit all requests** (`permitAll()`) with CSRF disabled — suitable for development only.
- The `title` field on `Book` and the `userName` field on `User` each have a `UNIQUE` constraint — duplicates return a `409 Conflict`.
