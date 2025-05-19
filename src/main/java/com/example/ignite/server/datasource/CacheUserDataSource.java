package com.example.ignite.server.datasource;

import com.example.ignite.server.entity.User;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.ignite.server.constants.AppConstants.USER_CACHE;

@Component("userCache")
public class CacheUserDataSource implements UserDataSource {

    @Autowired
    Ignite ignite;

    @Override
    public User getUserById(Long id) {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache(USER_CACHE);
        return cache.get(id);
    }
}

