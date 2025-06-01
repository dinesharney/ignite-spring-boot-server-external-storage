package com.example.ignite.server.controller;

import com.example.common.dto.*;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.entity.Order;
import com.example.ignite.server.entity.Product;
import com.example.ignite.server.entity.User;
import com.example.ignite.server.service.CustomerService;
import com.example.ignite.server.service.OrderService;
import com.example.ignite.server.service.ProductService;
import com.example.ignite.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/api")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    // Product Endpoints
    @PostMapping("/product")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        ResponseDTO response = new ResponseDTO();
        UUID id = productService.saveProduct(product).getId();
        response.setId(id);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
