# URL Shortener

A backend URL Shortener application built using Java, Spring Boot, and PostgreSQL.

## Features

- Create short URLs using an original URL and a custom short code.
- Store URL mappings in PostgreSQL.
- Redirect short codes to their original URLs.
- Return 404 Not Found for invalid short codes.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Postman

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/urls` | Create a short URL |
| GET | `/api/urls/{shortCode}` | Redirect to the original URL |

## Example

**Create a short URL**

`POST /api/urls`

Query parameters:
- `originalUrl`: `https://www.google.com`
- `shortCode`: `google123`

**Redirect**

`GET /api/urls/google123`

The API responds with `302 Found` and redirects to the original URL.

## Run Locally

1. Install Java 17 and PostgreSQL.
2. Create a PostgreSQL database named `url_shortener`.
3. Configure the database credentials in `src/main/resources/application.properties`.
4. Run the Spring Boot application from IntelliJ IDEA or using Maven.

The application runs at `http://localhost:8080`.

## Testing

API endpoints were tested using Postman. URL persistence was verified directly in PostgreSQL.