package org.example.vladsin.adverboard.model.controller;


import java.util.List;

public class GroupBillboardsJson {

    private Long id;
    private String status;
    private String available;
    private String groupName;
    private Long userId;
    private List<BillboardJson> billboards;
    private List<String> ads;
    private String verification;

    public GroupBillboardsJson(Long id, String status, String available, String groupName, Long userId, List<BillboardJson> billboards, List<String> ads, String verification) {
        this.id = id;
        this.status = status;
        this.available = available;
        this.groupName = groupName;
        this.userId = userId;
        this.billboards = billboards;
        this.ads = ads;
        this.verification = verification;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BillboardJson> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<BillboardJson> billboards) {
        this.billboards = billboards;
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
