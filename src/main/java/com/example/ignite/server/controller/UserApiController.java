package com.example.ignite.server.controller;

import com.example.common.dto.*;
import com.example.ignite.server.cachestore.UserCacheStore;
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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/api/v1")
public class UserApiController {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);


    @Autowired
    private UserService userService;

    @Autowired
    UserDataSourceFactory factory;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUsers(@PathVariable UUID id, @RequestParam(defaultValue = "cache") String source) {
        UserDataSource dataSource = factory.getSource(source);
        long start = System.nanoTime();
        User user = dataSource.getUserById(id);
        long end = System.nanoTime();
        log.info("Time Taken: {} ms", (end - start)/1000000);
        return ResponseEntity.ok(user);
    }

    // User Endpoints
    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDetail(userDTO.getDetail());        ResponseDTO response = new ResponseDTO();
        UUID id = userService.saveUser(user).getId();
        response.setId(id);
        response.setSuccess(true);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
