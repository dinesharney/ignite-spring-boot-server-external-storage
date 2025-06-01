package com.example.ignite.server.repository;

import com.example.ignite.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA Repository for accessing Product data from the database.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
