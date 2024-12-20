package com.example.ignite.server.config;

import com.example.ignite.server.cachestore.CustomerCacheStore;
import com.example.ignite.server.cachestore.OrderCacheStore;
import com.example.ignite.server.cachestore.ProductCacheStore;
import com.example.ignite.server.cachestore.UserCacheStore;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.entity.Order;
import com.example.ignite.server.entity.Product;
import com.example.ignite.server.entity.User;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;

import javax.cache.configuration.FactoryBuilder;

import static com.example.ignite.server.constants.AppConstants.*;

@Configuration
public class IgniteConfig {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Configure Ignite instance and cache with Read-Through and Write-Through enabled.
     *
     * @param userCacheStore UserCacheStore instance for handling persistence.
     * @param orderCacheStore OrderCacheStore instance for handling persistence.
     * @param productCacheStore ProductCacheStore instance for handling persistence.
     * @param customerCacheStore CustomerCacheStore instance for handling persistence.
     * @return Ignite instance.
     */
    @Bean
    public Ignite igniteInstance(UserCacheStore userCacheStore, OrderCacheStore orderCacheStore,
                                 ProductCacheStore productCacheStore, CustomerCacheStore customerCacheStore) {
        Ignite ignite = Ignition.start();

        CacheConfiguration<Long, User> userCacheConfig = new CacheConfiguration<>(USER_CACHE);
        userCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        userCacheConfig.setReadThrough(true);
        userCacheConfig.setWriteThrough(true);
        userCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(UserCacheStore.class));
        ignite.getOrCreateCache(userCacheConfig);

        CacheConfiguration<Long, Order> orderCacheConfig = new CacheConfiguration<>(ORDER_CACHE);
        orderCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        orderCacheConfig.setReadThrough(true);
        orderCacheConfig.setWriteThrough(true);
        orderCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(OrderCacheStore.class));

        ignite.getOrCreateCache(orderCacheConfig);

        CacheConfiguration<Long, Product> productCacheConfig = new CacheConfiguration<>(PRODUCT_CACHE);
        productCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        productCacheConfig.setReadThrough(true);
        productCacheConfig.setWriteThrough(true);
        productCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(ProductCacheStore.class));

        ignite.getOrCreateCache(productCacheConfig);

        CacheConfiguration<Long, Customer> customerCacheConfig = new CacheConfiguration<>(CUSTOMER_CACHE);
        customerCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        customerCacheConfig.setReadThrough(true);
        customerCacheConfig.setWriteThrough(true);
        customerCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(CustomerCacheStore.class));

        ignite.getOrCreateCache(customerCacheConfig);

        return ignite;
    }
}
