package ignitecachestore.config;

import ignitecachestore.cachestore.CustomerCacheStore;
import ignitecachestore.cachestore.OrderCacheStore;
import ignitecachestore.cachestore.ProductCacheStore;
import ignitecachestore.cachestore.UserCacheStore;
import ignitecachestore.entity.Customer;
import ignitecachestore.entity.Order;
import ignitecachestore.entity.Product;
import ignitecachestore.entity.User;
import ignitecachestore.factory.CacheStoreFactory;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;

import javax.cache.configuration.FactoryBuilder;

@Configuration
public class IgniteConfiguration {

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

        CacheConfiguration<Integer, User> userCacheConfig = new CacheConfiguration<>("UserCache");
        userCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        userCacheConfig.setReadThrough(true);
        userCacheConfig.setWriteThrough(true);
        userCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(UserCacheStore.class));
        //userCacheConfig.setCacheStoreFactory(new CacheStoreFactory<>(userCacheStore));
        ignite.getOrCreateCache(userCacheConfig);

        CacheConfiguration<Integer, Order> orderCacheConfig = new CacheConfiguration<>("OrderCache");
        orderCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        orderCacheConfig.setReadThrough(true);
        orderCacheConfig.setWriteThrough(true);
        orderCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(OrderCacheStore.class));

        ignite.getOrCreateCache(orderCacheConfig);

        CacheConfiguration<Integer, Product> productCacheConfig = new CacheConfiguration<>("ProductCache");
        productCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        productCacheConfig.setReadThrough(true);
        productCacheConfig.setWriteThrough(true);
        productCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(ProductCacheStore.class));

        ignite.getOrCreateCache(productCacheConfig);

        CacheConfiguration<Integer, Customer> customerCacheConfig = new CacheConfiguration<>("CustomerCache");
        customerCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        customerCacheConfig.setReadThrough(true);
        customerCacheConfig.setWriteThrough(true);
        customerCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(CustomerCacheStore.class));

        ignite.getOrCreateCache(customerCacheConfig);

        return ignite;
    }
}
