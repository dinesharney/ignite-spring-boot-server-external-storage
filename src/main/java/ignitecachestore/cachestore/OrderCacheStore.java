package ignitecachestore.cachestore;

import ignitecachestore.context.SpringContext;
import ignitecachestore.entity.Customer;
import ignitecachestore.entity.Order;
import ignitecachestore.repository.CustomerRepository;
import ignitecachestore.repository.OrderRepository;
import ignitecachestore.repository.UserRepository;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Ignite CacheStore implementation for handling Order data persistence.
 */
@Component
public class OrderCacheStore extends CacheStoreAdapter<Integer, Order> {

    private JpaRepository<Order, Long> getRepository() {
        return SpringContext.getBean(OrderRepository.class);
    }

    @Override
    public Order load(Integer key) {
        return getRepository().findById(Long.valueOf(key)).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Integer, ? extends Order> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById(Long.valueOf((Integer) key));
    }
}

