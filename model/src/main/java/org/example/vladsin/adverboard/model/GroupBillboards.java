package org.example.vladsin.adverboard.model;

import java.util.List;

public class GroupBillboards {

    private Long id;
    private String groupName;
    private Long userId;
    private List<Billboard> billboards;

    public GroupBillboards(Long id, String groupName, Long userId, List<Billboard> billboards) {
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

    public List<Billboard> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<Billboard> billboards) {
        this.billboards = billboards;
    }
}
