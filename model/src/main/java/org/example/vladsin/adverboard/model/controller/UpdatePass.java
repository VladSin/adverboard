package org.example.vladsin.adverboard.model.controller;

public class UpdatePass {

    private Long userId;
    private String password;

    public UpdatePass() {
    }

    public UpdatePass(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
