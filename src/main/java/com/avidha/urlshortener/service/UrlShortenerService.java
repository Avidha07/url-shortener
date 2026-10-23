
package com.avidha.urlshortener.service;

import com.avidha.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;
import com.avidha.urlshortener.entity.UrlMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlShortenerService(
            UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public UrlMapping createShortUrl(String originalUrl, String shortCode) {
        UrlMapping urlMapping = new UrlMapping(originalUrl, shortCode);
        return urlMappingRepository.save(urlMapping);
    }

    public UrlMapping getByShortCode(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Short URL not found"
                        ));
    }
}
