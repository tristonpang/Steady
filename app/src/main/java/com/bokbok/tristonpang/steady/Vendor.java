package com.bokbok.tristonpang.steady;

import java.util.HashMap;
import java.util.List;

public class Vendor {
    double rating;
    HashMap<String, Integer> price;
    String name;
    List<String> services;

    public Vendor(double rating, HashMap<String, Integer> price, String name, List<String> services) {
        this.rating = rating;
        this.price = price;
        this.name = name;
        this.services = services;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public HashMap<String, Integer> getPrice() {
        return price;
    }

    public void setPrice(HashMap<String, Integer> price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
