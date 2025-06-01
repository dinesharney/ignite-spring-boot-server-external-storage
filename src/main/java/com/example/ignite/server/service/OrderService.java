package com.example.ignite.server.service;

import com.example.ignite.server.repository.OrderRepository;
import com.example.ignite.server.entity.Order;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service to handle business logic for Order entities.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    Ignite ignite;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        IgniteCache<UUID, Order> cache = ignite.getOrCreateCache("OrderCache");
        cache.put(order.getId(),order);
        return order;
    }

    public Order updateOrder(UUID id, Order updatedOrder) {
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null;
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
