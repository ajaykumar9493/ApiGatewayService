package com.learn.apigateway.service.model;



import java.util.List;

public class Movie {
private Long id;
private String name;
private String director;
private List<String> actors;

    public Movie(String name, String director, List<String> actors) {
        this.name = name;
        this.director = director;
        this.actors = actors;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                '}';
    }
}
