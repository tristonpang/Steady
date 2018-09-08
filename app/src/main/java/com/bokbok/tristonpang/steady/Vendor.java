package com.bokbok.tristonpang.steady;

import java.util.HashMap;
import java.util.List;

public class Vendor {
    private String id;
    private double rating;
    private HashMap<String, Integer> price;
    private String name;

    public Vendor() {
    }

    public Vendor(String id, double rating, HashMap<String, Integer> price, String name) {
        this.id = id;
        this.rating = rating;
        this.price = price;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public HashMap<String, Integer> getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}


