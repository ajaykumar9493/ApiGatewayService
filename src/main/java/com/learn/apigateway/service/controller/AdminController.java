package com.learn.apigateway.service.controller;

import com.learn.apigateway.service.model.Movie;

import com.learn.apigateway.service.model.MovieRating;
import com.learn.apigateway.service.model.Rating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private RestTemplate restTemplate =new RestTemplate();

    @Value("${movie-service.url}")
    private String MovieServiceUrl;

    @Value("${rating-service.url}")
    private String RattingServiceUrl;

/*
    private final RestTemplate restTemplate;

    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
*/

    @PostMapping
    public ResponseEntity<Object> addMovir(@RequestBody Movie movie) {
        try {

            System.out.println(">> Inside AddMovie ::: " +
                    "Movie ::: "+movie);


            Movie rec_movie = restTemplate.postForObject(MovieServiceUrl+"/create", movie, Movie.class);
            return ResponseEntity.ok().body(rec_movie);
        } catch (HttpStatusCodeException ex) {
            System.out.println(">> Exception Occured ::: "+ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ex.getResponseBodyAsString());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateMovie(@RequestBody Movie movie) {
        try {
            ResponseEntity<Movie> responseEntity = restTemplate.exchange(MovieServiceUrl + "/update", HttpMethod.PUT, new HttpEntity<>(movie), Movie.class);
            Movie upd_movie = responseEntity.getBody();
            return ResponseEntity.ok().body(upd_movie);

        } catch (HttpStatusCodeException exception) {
            System.out.println(">> Exception occured ::: " + exception.getMessage());
            return ResponseEntity.status(exception.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(exception.getResponseBodyAsString());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> featchratting(@PathVariable Long id, @RequestParam String name) {

        System.out.println(">>> inside featch get API ::: ");
        Movie fetchMovie;
        try {
            System.out.println(">> Inside try ::");
            fetchMovie = restTemplate.getForObject(MovieServiceUrl + "/" + id, Movie.class);
        } catch (HttpStatusCodeException ex) {
            System.out.println(">> Exception Occured ::: " + ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ex.getResponseBodyAsString());
        }

        Rating fetchRating;
        try {
            String url = UriComponentsBuilder.fromHttpUrl(RattingServiceUrl + "/getrating")
                    .queryParam("name", name)
                    .toUriString();
            fetchRating = restTemplate.getForObject(url, Rating.class);
        } catch (HttpStatusCodeException ex) {
            System.out.println(">> Exception Occured ::: " + ex.getMessage());
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                fetchRating = new Rating(null, fetchMovie.getName(), 0, 0L);
            } else {
                fetchRating = new Rating(null, fetchMovie.getName(), -1, -1L);
            }
        }

        MovieRating movieRating = new MovieRating(fetchMovie, fetchRating);
        return ResponseEntity.ok().body(movieRating);
    }
}
