package org.example.vladsin.adverboard.dao.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Pattern(regexp="^[A-Z]+[a-z]+[А-Я]+[а-я]+$",
            message="Username must be alphanumeric with no spaces and first capital")
    private String username;

    @Column(name = "email")
    @Pattern(regexp="(\\w+)@.*",
            message="Key character not entered")
    private String email;

    public User() {
    }

    public User(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }
    public void setName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
