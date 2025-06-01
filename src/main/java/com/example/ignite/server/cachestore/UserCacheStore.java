package com.example.ignite.server.cachestore;

import com.example.common.dto.CustomerDTO;
import com.example.ignite.server.repository.UserRepository;
import com.example.ignite.server.context.SpringContext;
import com.example.ignite.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Ignite CacheStore implementation for handling User data persistence.
 */
@Configurable
@Component
public class UserCacheStore extends CacheStoreAdapter<UUID, User> implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(UserCacheStore.class);


    private JpaRepository<User,UUID> getRepository() {
        return SpringContext.getBean(UserRepository.class);
    }

    @Override
    public User load(UUID key) {
        log.info("Inside UserCacheStore load() method");
        return getRepository().findById(key).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends UUID, ? extends User> entry) {
        log.info("Inside UserCacheStore write() method");
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        log.info("Inside UserCacheStore delete() method");
        getRepository().deleteById((UUID) key);
    }

    @Override
    public void loadCache(IgniteBiInClosure<UUID, User> clo, Object... args) {
        log.info("Inside UserCacheStore loadCache() method");
        // Load entire DB dataset into cache
        List<User> users = getRepository().findAll();
        for (User user : users) {
            clo.apply(user.getId(), user);
        }
    }
}