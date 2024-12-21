package com.example.ignite.server.controller;

import com.example.common.dto.CustomerDTO;
import com.example.common.dto.OrderDTO;
import com.example.common.dto.ProductDTO;
import com.example.common.dto.UserDTO;
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
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // Order Endpoints
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setProduct(orderDTO.getProduct());
        order.setPrice(orderDTO.getPrice());
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
