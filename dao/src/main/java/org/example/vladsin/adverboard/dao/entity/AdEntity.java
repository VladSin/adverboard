package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ad")
public class AdEntity {

    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;

    @Column(name = "link")
    private String link;

    @Column(name = "billboard_id")
    private Long billboardId;

    @Column(name = "verification")
    private  String verification;

    public AdEntity() {
    }

    public AdEntity(Long adId, String link, Long billboardId, String verification) {
        this.adId = adId;
        this.link = link;
        this.billboardId = billboardId;
        this.verification = verification;
    }

    public Long getAdId() {
        return adId;
    }
    public void setAdId(Long id) {
        this.adId = id;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public Long getBillboardId() {
        return billboardId;
    }
    public void setBillboardId(Long billboardId) {
        this.billboardId = billboardId;
    }

    public String getVerification() {
        return verification;
    }
    public void setVerification(String verification) {
        this.verification = verification;
    }
}
