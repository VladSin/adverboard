package org.example.vladsin.adverboard.model;

import java.util.List;

public class GroupBillboards {

    private Long id;
    private String name;
    private Long userId;
    private List<Billboard> billboards;

    public GroupBillboards(Long id, String name, Long userId, List<Billboard> billboards) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.billboards = billboards;
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

    public List<Billboard> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<Billboard> billboards) {
        this.billboards = billboards;
    }
}
