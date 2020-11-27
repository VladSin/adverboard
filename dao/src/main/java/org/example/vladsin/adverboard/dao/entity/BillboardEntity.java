package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "billboard")
public class BillboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billboard_id")
    private Long billboardId;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private Double price;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(mappedBy = "billboards", cascade = CascadeType.ALL)
    private List<AdEntity> ads = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupBillboardsEntity groupBillboardsEntity;

    public BillboardEntity() {
    }

    public BillboardEntity(Long billboardId, String location, Double price, Long userId, List<AdEntity> ads) {
        this.billboardId = billboardId;
        this.location = location;
        this.price = price;
        this.userId = userId;
        this.ads = ads;
    }

    public BillboardEntity(Long billboardId, String location, Double price, Long userId, List<AdEntity> ads, GroupBillboardsEntity groupBillboardsEntity) {
        this.billboardId = billboardId;
        this.location = location;
        this.price = price;
        this.userId = userId;
        this.ads = ads;
        this.groupBillboardsEntity = groupBillboardsEntity;
    }

    public Long getBillboardId() {
        return billboardId;
    }
    public void setBillboardId(Long billboardId) {
        this.billboardId = billboardId;
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

    public List<AdEntity> getAds() {
        return ads;
    }
    public void setAds(List<AdEntity> ads) {
        this.ads = ads;
    }

    public GroupBillboardsEntity getGroupBillboardsEntity() {
        return groupBillboardsEntity;
    }
    public void setGroupBillboardsEntity(GroupBillboardsEntity groupBillboardsEntity) {
        this.groupBillboardsEntity = groupBillboardsEntity;
    }
}
