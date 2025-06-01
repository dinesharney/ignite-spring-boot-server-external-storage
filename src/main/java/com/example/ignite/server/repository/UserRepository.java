package com.example.ignite.server.repository;

import com.example.ignite.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA Repository for accessing User data from the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}
