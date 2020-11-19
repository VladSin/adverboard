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

    @Column(name = "name")
    @Pattern(regexp="^[A-Z]+[a-z]+[А-Я]+[а-я]+$",
            message="Username must be alphanumeric with no spaces and first capital")
    private String name;

    @Column(name = "surname")
    @Pattern(regexp="^[A-Z]+[a-z]+[А-Я]+[а-я]+$",
            message="Username must be alphanumeric with no spaces and first capital")
    private String surname;

    @Column(name = "email")
    @Pattern(regexp="(\\w+)@.*",
            message="Key character not entered")
    private String email;

    public User() {
    }
    public User(Long id, String name, String surname, String email){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
