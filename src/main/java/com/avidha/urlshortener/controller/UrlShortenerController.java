package com.avidha.urlshortener.controller;

import com.avidha.urlshortener.entity.UrlMapping;
import com.avidha.urlshortener.service.UrlShortenerService;
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
    public ResponseEntity<UrlMapping> createShortUrl(
            @RequestParam String originalUrl) {

        UrlMapping urlMapping =
                urlShortenerService.createShortUrl(originalUrl);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlMapping);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortCode) {

        UrlMapping urlMapping =
                urlShortenerService.getByShortCode(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(java.net.URI.create(urlMapping.getOriginalUrl()))
                .build();
    }
}