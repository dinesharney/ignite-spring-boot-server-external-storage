package com.example.ignite.server.entity;

import jakarta.persistence.*;

/**
 * Entity representing a User.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    private String detail;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

