package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "group_billboard")
public class GroupBillboardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "groupBillboardsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillboardEntity> billboards = new ArrayList<>();

    public GroupBillboardsEntity() {
    }

    public GroupBillboardsEntity(Long id, String groupName, Long userId, List<BillboardEntity> billboards) {
        this.id = id;
        this.groupName = groupName;
        this.userId = userId;
        this.billboards = billboards;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public List<BillboardEntity> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<BillboardEntity> billboards) {
        this.billboards = billboards;
    }
}
