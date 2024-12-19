package com.example.ignite.server.cachestore;

import com.example.ignite.server.service.OrderService;
import com.example.ignite.server.service.UserService;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.entity.Order;
import com.example.ignite.server.entity.Product;
import com.example.ignite.server.entity.User;
import com.example.ignite.server.service.CustomerService;
import com.example.ignite.server.service.ProductService;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner to load initial data into Ignite cache from the database on application startup.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Ignite ignite;

    @Override
    public void run(String... args) throws Exception {
        IgniteCache<Long, User> userCache = ignite.cache("UserCache");
        userService.getAllUsers().forEach(user -> userCache.put(user.getId(), user));

        IgniteCache<Long, Order> orderCache = ignite.cache("OrderCache");
        orderService.getAllOrders().forEach(order -> orderCache.put(order.getId(), order));

        IgniteCache<Long, Product> productCache = ignite.cache("ProductCache");
        productService.getAllProducts().forEach(product -> productCache.put(product.getId(), product));

        IgniteCache<Long, Customer> customerCache = ignite.cache("CustomerCache");
        customerService.getAllCustomers().forEach(customer -> customerCache.put(customer.getId(), customer));
    }
}
