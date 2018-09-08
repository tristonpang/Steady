package com.bokbok.tristonpang.steady;

public class User {
    String health;
    String family;
    String socialService;
    String payment;

    public User(String health, String family, String socialService, String payment) {
        this.health = health;
        this.family = family;
        this.socialService = socialService;
        this.payment = payment;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSocialService() {
        return socialService;
    }

    public void setSocialService(String socialService) {
        this.socialService = socialService;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
