package com.example.ignite.server.cachestore;

import com.example.ignite.server.repository.ProductRepository;
import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.Product;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Ignite CacheStore implementation for handling Product data persistence.
 */
@Component
public class ProductCacheStore extends CacheStoreAdapter<UUID, Product> {

    private JpaRepository<Product, UUID> getRepository() {
        return SpringContext.getBean(ProductRepository.class);
    }

    @Override
    public Product load(UUID key) {
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends UUID, ? extends Product> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById((UUID) key);
    }
}

