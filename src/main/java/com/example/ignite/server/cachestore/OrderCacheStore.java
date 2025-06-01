package com.example.ignite.server.cachestore;

import com.example.ignite.server.repository.OrderRepository;
import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.Order;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Ignite CacheStore implementation for handling Order data persistence.
 */
@Component
public class OrderCacheStore extends CacheStoreAdapter<UUID, Order> {

    private JpaRepository<Order, UUID> getRepository() {
        return SpringContext.getBean(OrderRepository.class);
    }

    @Override
    public Order load(UUID key) {
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends UUID, ? extends Order> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById((UUID) key);
    }
}

