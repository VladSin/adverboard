package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;
import org.example.vladsin.adverboard.model.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authuser")
public class AuthUserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_id")
    private Long userId;

    public AuthUserEntity() {
    }

    public AuthUserEntity(Long id, String login, String password, Role role, Long userId) {
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
