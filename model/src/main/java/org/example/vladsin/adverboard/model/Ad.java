package org.example.vladsin.adverboard.model;

import java.util.List;

public class Ad {

    private Long id;
    private String link;
    private List<Billboard> billboards;

    public Ad(Long id, String link, List<Billboard> billboards) {
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

    public List<Billboard> getBillboards() {
        return billboards;
    }
    public void setBillboards(List<Billboard> billboards) {
        this.billboards = billboards;
    }
}
