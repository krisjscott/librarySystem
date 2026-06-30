# SpringBootInit — Book REST API

A Spring Boot REST API for managing books, built with Java 21, Spring Data JPA, H2 in-memory database, and Spring Security.

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
| JUnit Jupiter | via Boot BOM |

---

## Project Structure

```
app/src/main/java/org/example/
├── App.java                          # Entry point
├── Model/
│   └── book.java                     # JPA Entity
├── Repository/
│   └── BookRepository.java           # Spring Data repository
├── controller/
│   ├── BookController.java           # Book REST endpoints
│   └── HomeController.java           # Home endpoint
├── ErrorHandling/
│   ├── BookNotFoundException.java    # 404 exception
│   ├── BookIdMismatchException.java  # 409 exception
│   └── RestExceptionHandler.java     # Global exception handler
└── Security/
    └── HomeSecurity.java             # Security config
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

## API Endpoints

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
  {"id": 1, "name": "Clean Code", "author": "Robert Martin"}
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
Response `200 OK`:
```json
{"id": 1, "name": "Clean Code", "author": "Robert Martin"}
```
Response `404 Not Found` if ID doesn't exist:
```json
Book NOT found
```

---

### Get books by title
```
GET /api/books/title/{bookTitle}
```
```bash
curl http://localhost:8081/api/books/title/Clean%20Code
```
Response `200 OK`:
```json
[{"id": 1, "name": "Clean Code", "author": "Robert Martin"}]
```

---

### Get books by author
```
GET /api/books/author/{author}
```
```bash
curl http://localhost:8081/api/books/author/Robert%20Martin
```
Response `200 OK`:
```json
[{"id": 1, "name": "Clean Code", "author": "Robert Martin"}]
```

---

### Create a book
```
POST /api/books
```
```bash
curl -X POST http://localhost:8081/api/books \
  -H "Content-Type: application/json" \
  -d '{"name": "Clean Code", "author": "Robert Martin"}'
```
Response `201 Created`:
```json
{"id": 1, "name": "Clean Code", "author": "Robert Martin"}
```

---

### Update a book
```
PUT /api/books/{id}
```
```bash
curl -X PUT http://localhost:8081/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Clean Code (2nd Ed)", "author": "Robert Martin"}'
```
Response `200 OK`:
```json
{"id": 1, "name": "Clean Code (2nd Ed)", "author": "Robert Martin"}
```
Response `409 Conflict` if the ID in the URL doesn't match the body.

---

### Delete a book
```
DELETE /api/books/{id}
```
```bash
curl -X DELETE http://localhost:8081/api/books/1
```
Response `200 OK` (empty body).

---

## Error Responses

| HTTP Status | When |
|---|---|
| `404 Not Found` | Book ID does not exist |
| `409 Conflict` | ID in URL doesn't match ID in request body |
| `500 Internal Server Error` | Constraint violations (e.g. duplicate name, missing required field) |

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

// Database
runtimeOnly("com.h2database:h2")
```

---

## Notes

- The database is **in-memory (H2)** — all data resets when the app restarts.
- Spring Security is configured to **permit all requests** (`permitAll()`) with CSRF disabled — suitable for development only.
- The `name` field on `book` has a `UNIQUE` constraint — duplicate names will cause a 500 error.
