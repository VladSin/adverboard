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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ad_billboard",
            joinColumns = {@JoinColumn(name = "ad_id")},
            inverseJoinColumns = {@JoinColumn(name = "billboard_id")})
    private List<BillboardEntity> billboards = new ArrayList<>();

    public AdEntity() {
    }

    public AdEntity(Long adId, String link, List<BillboardEntity> billboards) {
        this.adId = adId;
        this.link = link;
        this.billboards = billboards;
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

    public List<BillboardEntity> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<BillboardEntity> billboards) {
        this.billboards = billboards;
    }
}
