package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "group")
public class GroupBillboardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    @OneToMany(mappedBy = "groupBillboardsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillboardEntity> billboards = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public GroupBillboardsEntity() {
    }

    public GroupBillboardsEntity(Long id, String name, Long userId, List<BillboardEntity> billboards) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.billboards = billboards;
    }

    public GroupBillboardsEntity(Long id, String name, Long userId, List<BillboardEntity> billboards, UserEntity userEntity) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.billboards = billboards;
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BillboardEntity> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<BillboardEntity> billboards) {
        this.billboards = billboards;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
