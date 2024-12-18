package ignitecachestore.cachestore;

import ignitecachestore.context.SpringContext;
import ignitecachestore.entity.Order;
import ignitecachestore.entity.Product;
import ignitecachestore.repository.OrderRepository;
import ignitecachestore.repository.ProductRepository;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Ignite CacheStore implementation for handling Product data persistence.
 */
@Component
public class ProductCacheStore extends CacheStoreAdapter<Integer, Product> {

    private JpaRepository<Product, Long> getRepository() {
        return SpringContext.getBean(ProductRepository.class);
    }

    @Override
    public Product load(Integer key) {
        return getRepository().findById(Long.valueOf(key)).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Integer, ? extends Product> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById(Long.valueOf((Integer) key));
    }
}

