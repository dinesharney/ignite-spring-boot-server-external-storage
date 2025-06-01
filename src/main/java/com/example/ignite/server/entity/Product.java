package com.example.ignite.server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.domain.Persistable;

import java.sql.Types;
import java.util.UUID;

/**
 * Entity representing a Product.
 */

@Entity
@Table(name = "products")
public class Product implements Persistable<UUID>  {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String name;
    private Double price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
