package com.example.ignite.server.cachestore;

import com.example.ignite.server.repository.ProductRepository;
import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.Product;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Ignite CacheStore implementation for handling Product data persistence.
 */
@Component
public class ProductCacheStore extends CacheStoreAdapter<Long, Product> {

    private JpaRepository<Product, Long> getRepository() {
        return SpringContext.getBean(ProductRepository.class);
    }

    @Override
    public Product load(Long key) {
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Long, ? extends Product> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById((Long) key);
    }
}

