
# URL Shortener

A backend URL Shortener application built using Java, Spring Boot, and PostgreSQL.

## Features

- Create short URLs using a JSON request body.
- Generate unique short codes automatically.
- Store URL mappings in PostgreSQL.
- Redirect short URLs to their original URLs.
- Validate URLs and return consistent error responses.

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
| GET | `/{shortCode}` | Redirect to the original URL |

## Create a Short URL

**Request**

`POST /api/urls`

Content-Type: `application/json`

```json
{
  "originalUrl": "https://www.wikipedia.org"
}
```

**Response — 201 Created**

```json
{
  "id": 8,
  "originalUrl": "https://www.wikipedia.org",
  "shortCode": "3e8751a",
  "shortUrl": "http://localhost:8080/api/urls/3e8751a"
}
```


## Redirect to Original URL

**Request**

`GET /3e8751a`

The short URL is `http://localhost:8080/3e8751a`.

The API returns `302 Found` and redirects to the original URL using the `Location` header.

## Error Handling

- `400 Bad Request` — Blank or invalid URL.
- `404 Not Found` — Short code does not exist.

## Run Locally

1. Install Java 17 and PostgreSQL.
2. Create a database named `url_shortener`.
3. Configure database credentials in `src/main/resources/application.properties`.
4. Run the application from IntelliJ IDEA or using Maven.

The application runs at `http://localhost:8080`.

## Testing

API endpoints, URL validation, redirect behavior, and error responses were tested using Postman.
