package com.example.ignite.server.cachestore;

import com.example.ignite.server.repository.UserRepository;
import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.User;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Ignite CacheStore implementation for handling User data persistence.
 */
@Configurable
@Component
public class UserCacheStore extends CacheStoreAdapter<Long, User> implements Serializable {

    private JpaRepository<User,Long> getRepository() {
        return SpringContext.getBean(UserRepository.class);
    }

    @Override
    public User load(Long key) {
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Long, ? extends User> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById((Long) key);
    }
}