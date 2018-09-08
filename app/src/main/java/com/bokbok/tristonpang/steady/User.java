package com.bokbok.tristonpang.steady;

public class User {
    private String id;
    private String name;
    private String contactNum;
    private String email;
    private String health;
    private String family;
    private String socialService;
    private String payment;
    private boolean isDeaf;

    public User() {
    }


    public User(String id, String name, String contactNum, String email,
                String health, String family, String socialService,
                String payment, boolean isDeaf) {
        this.id = id;
        this.name = name;
        this.contactNum = contactNum;
        this.email = email;
        this.health = health;
        this.family = family;
        this.socialService = socialService;
        this.payment = payment;
        this.isDeaf = isDeaf;
    }

    public String getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getFamily() {
        return family;
    }

    public String getSocialService() {
        return socialService;
    }

    public String getPayment() {
        return payment;
    }

    public String getName() {
        return name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDeaf() {
        return isDeaf;
    }

}
