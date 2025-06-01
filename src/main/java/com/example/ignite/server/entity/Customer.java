package com.example.ignite.server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.domain.Persistable;

import java.sql.Types;
import java.util.UUID;

/**
 * Entity representing a Customer.
 */
@Entity
@Table(name = "customers")
public class Customer implements Persistable<UUID>  {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String name;
    private String email;
    private String detail;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public boolean isNew() {
        return true;
    }
}
