package ignitecachestore.cachestore;

import ignitecachestore.context.SpringContext;
import ignitecachestore.entity.Customer;
import ignitecachestore.repository.CustomerRepository;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Ignite CacheStore implementation for handling Customer data persistence.
 */
@Component
public class CustomerCacheStore extends CacheStoreAdapter<Integer, Customer> {

    private JpaRepository<Customer, Long> getRepository() {
        return SpringContext.getBean(CustomerRepository.class);
    }

    @Override
    public Customer load(Integer key) {
        return getRepository().findById(Long.valueOf(key)).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Integer, ? extends Customer> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById(Long.valueOf((Integer) key));
    }
}

