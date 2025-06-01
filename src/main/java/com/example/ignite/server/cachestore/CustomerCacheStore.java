package com.example.ignite.server.cachestore;

import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.repository.CustomerRepository;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Ignite CacheStore implementation for handling Customer data persistence.
 */
@Component
public class CustomerCacheStore extends CacheStoreAdapter<UUID, Customer> {

    private JpaRepository<Customer, UUID> getRepository() {
        return SpringContext.getBean(CustomerRepository.class);
    }

    @Override
    public Customer load(UUID key) {
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends UUID, ? extends Customer> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById((UUID) key);
    }
}

