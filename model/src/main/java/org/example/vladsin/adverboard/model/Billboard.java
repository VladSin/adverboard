package org.example.vladsin.adverboard.model;

import java.util.List;

public class Billboard {
    private Long id;
    private String location;
    private Double price;
    private Long userId;
    private List<Ad> ads;

    public Billboard(Long id, String location, Double price, Long userId, List<Ad> ads) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.userId = userId;
        this.ads = ads;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Ad> getAds() {
        return ads;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
