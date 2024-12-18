package ignitecachestore.dto;

import lombok.Data;

/**
 * DTO classes for API communication.
 */
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;

    // Getters and setters
}
