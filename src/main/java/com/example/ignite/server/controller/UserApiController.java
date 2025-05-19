package com.example.ignite.server.controller;

import com.example.common.dto.CustomerDTO;
import com.example.common.dto.OrderDTO;
import com.example.common.dto.ProductDTO;
import com.example.common.dto.UserDTO;
import com.example.ignite.server.datasource.UserDataSource;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.entity.Order;
import com.example.ignite.server.entity.Product;
import com.example.ignite.server.entity.User;
import com.example.ignite.server.factory.UserDataSourceFactory;
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
@RequestMapping("/api/v1")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    UserDataSourceFactory factory;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id, @RequestParam(defaultValue = "cache") String source) {
        UserDataSource dataSource = factory.getSource(source);
        return ResponseEntity.ok(dataSource.getUserById(id));
    }

    // User Endpoints
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDetail(userDTO.getDetail());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
