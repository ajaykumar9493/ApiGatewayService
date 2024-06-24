package com.learn.apigateway.service.controller;

import com.learn.apigateway.service.model.Rating;
import com.learn.apigateway.service.model.RequestRating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private RestTemplate restTemplate =new RestTemplate();

    @Value("${movie-service.url}")
    private String MovieServiceUrl;

    @Value("${rating-service.url}")
    private String RattingServiceUrl;

    @PostMapping("/rate")
    public ResponseEntity<Object> addingRating(@RequestBody RequestRating requestRating) {

        System.out.println(">> Inside AddingRating ::: ");
        try {
            Rating gotratting = restTemplate.postForObject(RattingServiceUrl + "/updateRating", requestRating, Rating.class);
            return ResponseEntity.ok(gotratting);
        } catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ex.getResponseBodyAsString());
        }
    }
}
