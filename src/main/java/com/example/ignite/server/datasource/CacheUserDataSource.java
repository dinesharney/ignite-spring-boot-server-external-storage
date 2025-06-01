package com.example.ignite.server.datasource;

import com.example.ignite.server.cachestore.UserCacheStore;
import com.example.ignite.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.ignite.server.constants.AppConstants.USER_CACHE;

@Component("userCache")
public class CacheUserDataSource implements UserDataSource {

    private static final Logger log = LoggerFactory.getLogger(CacheUserDataSource.class);


    @Autowired
    Ignite ignite;

    @Override
    public User getUserById(UUID id) {
        log.info("Getting data from Cache");
        IgniteCache<UUID, User> cache = ignite.getOrCreateCache(USER_CACHE);
        return cache.get(id);
    }
}

