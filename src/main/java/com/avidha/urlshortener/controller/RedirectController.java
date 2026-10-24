
package com.avidha.urlshortener.controller;

import com.avidha.urlshortener.entity.UrlMapping;
import com.avidha.urlshortener.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedirectController {

    private final UrlShortenerService urlShortenerService;

    public RedirectController(
            UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
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
