package ignitecachestore.cachestore;

import ignitecachestore.entity.Customer;
import ignitecachestore.entity.Order;
import ignitecachestore.entity.Product;
import ignitecachestore.entity.User;
import ignitecachestore.service.CustomerService;
import ignitecachestore.service.OrderService;
import ignitecachestore.service.ProductService;
import ignitecachestore.service.UserService;
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
