package com.example.ignite.server.controller;

import com.example.common.dto.CustomerDTO;
import com.example.common.dto.ResponseDTO;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.service.CustomerService;
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
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    // Customer Endpoints
    @PostMapping("/customer")
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        ResponseDTO response = new ResponseDTO();
        UUID id = customerService.saveCustomer(customer).getId();
        response.setId(id);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
