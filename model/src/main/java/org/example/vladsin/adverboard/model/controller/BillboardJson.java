package org.example.vladsin.adverboard.model.controller;

import java.util.List;

public class BillboardJson {
    private String location;
    private String status;
    private String available;
    private Long id;
    private Long userId;
    private Double price;
    private List<String> ads;
    private String verification;

    public BillboardJson() {
        super();
    }

    public BillboardJson(String location, String status, String available, Long id, Long userId, Double price, List<String> ads, String verification) {
        this.location = location;
        this.status = status;
        this.available = available;
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.ads = ads;
        this.verification = verification;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailable() {
        return available;
    }
    public void setAvailable(String available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getAds() {
        return ads;
    }
    public void setAds(List<String> ads) {
        this.ads = ads;
    }

    public String getVerification() {
        return verification;
    }
    public void setVerification(String verification) {
        this.verification = verification;
    }
}
