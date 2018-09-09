package com.bokbok.tristonpang.steady;

import java.util.HashMap;

public class Vendor {
    private String id;
    private double rating;
    private HashMap<String, String> price;
    private String name;

    public Vendor() {
    }

    public Vendor(String id, double rating, HashMap<String, String> price, String name) {
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

    public HashMap<String, String> getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}


