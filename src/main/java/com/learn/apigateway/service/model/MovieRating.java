package com.learn.apigateway.service.model;

public class MovieRating {

    private Movie movie;

    private  Rating rating;

    public MovieRating(Movie movie, Rating rating) {
        this.movie = movie;
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "movie=" + movie +
                ", rating=" + rating +
                '}';
    }
}
