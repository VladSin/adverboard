package org.example.vladsin.adverboard.model;

public class Ad {

    private Long id;
    private String link;
    private Long billboardId;

    public Ad(Long id, String link, Long billboardId) {
        this.id = id;
        this.link = link;
        this.billboardId = billboardId;
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

    public Long getBillboardId() {
        return billboardId;
    }
    public void setBillboardId(Long billboardId) {
        this.billboardId = billboardId;
    }
}
