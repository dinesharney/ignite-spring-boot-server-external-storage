package com.example.ignite.server.controller;

import com.example.ignite.server.dto.CustomerDTO;
import com.example.ignite.server.dto.OrderDTO;
import com.example.ignite.server.dto.ProductDTO;
import com.example.ignite.server.dto.UserDTO;
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

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/api")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    // Customer Endpoints
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
