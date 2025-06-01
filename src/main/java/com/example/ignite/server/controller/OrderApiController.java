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
@RequestMapping("/api/v1")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // Order Endpoints
    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setProduct(orderDTO.getProduct());
        order.setPrice(orderDTO.getPrice());
        ResponseDTO response = new ResponseDTO();
        UUID id = orderService.saveOrder(order).getId();
        response.setId(id);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
