package com.example.ignite.server.service;

import com.example.ignite.server.repository.CustomerRepository;
import com.example.ignite.server.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service to handle business logic for Customer entities.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(UUID id, Customer updatedCustomer) {
        if (customerRepository.existsById(id)) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        }
        return null;
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}