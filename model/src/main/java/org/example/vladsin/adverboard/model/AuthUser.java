package org.example.vladsin.adverboard.model;

public class AuthUser {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Long userId;

    public AuthUser(Long id, String login, String password, Role role, Long userId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
