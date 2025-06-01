package com.example.ignite.server.repository;

import com.example.ignite.server.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA Repository for accessing Customer data from the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
