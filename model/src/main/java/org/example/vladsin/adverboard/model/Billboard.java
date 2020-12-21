package org.example.vladsin.adverboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Billboard {
    private Long id;
    private String location;
    private Double price;
    private Long userId;
    private Long groupId;
    private List<Ad> ads;

    public Billboard() {
        super();
    }
    public Billboard(Long id, String location, Double price, Long userId, Long groupId, List<Ad> ads) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.userId = userId;
        this.groupId = groupId;
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

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Ad> getAds() {
        return ads;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public void addAd(Ad ad) {
        if(this.ads == null){
            List<Ad> ads = new ArrayList<>();
            ads.add(ad);
            this.ads = ads;
        } else {
            this.ads.add(ad);
        }
    }
}
