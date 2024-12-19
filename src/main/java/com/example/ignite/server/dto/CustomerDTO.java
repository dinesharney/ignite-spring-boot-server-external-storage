package com.example.ignite.server.dto;

import lombok.Data;

/**
 * DTO classes for API communication.
 */
@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;

    // Getters and setters
}
