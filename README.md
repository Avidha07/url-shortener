# URL Shortener

A backend URL Shortener application built using Java, Spring Boot, and PostgreSQL.

## Features

- Create short URLs with automatically generated short codes.
- Store URL mappings in PostgreSQL.
- Redirect short URLs to original URLs.
- Validate URLs to accept only HTTP and HTTPS links.
- Return appropriate error responses for invalid URLs and unknown short codes.

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
| POST | `/api/urls?originalUrl=https://www.wikipedia.org` | Create a short URL |
| GET | `/api/urls/{shortCode}` | Redirect to the original URL |

## Example

Create a short URL:

`POST /api/urls?originalUrl=https://www.wikipedia.org`

The API returns `201 Created` with the generated short code.

Redirect:

`GET /api/urls/{shortCode}`

The API returns `302 Found` and redirects to the original URL.

## Error Handling

- `400 Bad Request` — Invalid or blank URL.
- `404 Not Found` — Short code does not exist.

## Run Locally

1. Install Java 17 and PostgreSQL.
2. Create a database named `url_shortener`.
3. Configure database credentials in `src/main/resources/application.properties`.
4. Run the application from IntelliJ IDEA or using Maven.

The application runs at `http://localhost:8080`.

## Testing

API endpoints and error responses were tested using Postman.