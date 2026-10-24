
package com.avidha.urlshortener.controller;

import com.avidha.urlshortener.dto.CreateUrlRequest;
import com.avidha.urlshortener.dto.CreateUrlResponse;
import com.avidha.urlshortener.entity.UrlMapping;
import com.avidha.urlshortener.service.UrlShortenerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(
            UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<CreateUrlResponse> createShortUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        UrlMapping urlMapping =
                urlShortenerService.createShortUrl(
                        request.getOriginalUrl()
                );

        String shortUrl =
                "http://localhost:8080/api/urls/"
                        + urlMapping.getShortCode();

        CreateUrlResponse response = new CreateUrlResponse(
                urlMapping.getId(),
                urlMapping.getOriginalUrl(),
                urlMapping.getShortCode(),
                shortUrl
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortCode) {

        UrlMapping urlMapping =
                urlShortenerService.getByShortCode(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(
                        java.net.URI.create(
                                urlMapping.getOriginalUrl()
                        )
                )
                .build();
    }
}
