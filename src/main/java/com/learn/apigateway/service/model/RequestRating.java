package com.learn.apigateway.service.model;

public class RequestRating {

    private String name;
    private double star;

    public RequestRating(String name, double star) {
        this.name = name;
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "RequestRating{" +
                "name='" + name + '\'' +
                ", star=" + star +
                '}';
    }
}
