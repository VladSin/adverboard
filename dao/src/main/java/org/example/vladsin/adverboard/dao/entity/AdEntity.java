package org.example.vladsin.adverboard.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ad")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", unique = true)
    private Long id;

    @Column(name = "link")
    private String link;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ad_billboard",
            joinColumns = {@JoinColumn(name = "ad_id")},
            inverseJoinColumns = {@JoinColumn(name = "billboard_id")})
    private List<BillboardEntity> billboards = new ArrayList<>();

    public AdEntity() {
    }

    public AdEntity(Long id, String link, List<BillboardEntity> billboards) {
        this.id = id;
        this.link = link;
        this.billboards = billboards;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
