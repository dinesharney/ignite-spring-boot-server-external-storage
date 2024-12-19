package com.example.ignite.server.dto;

import lombok.Data;

/**
 * DTO classes for API communication.
 */
@Data
public class OrderDTO {
    private Long id;
    private String product;
    private Double price;

    // Getters and setters
}
