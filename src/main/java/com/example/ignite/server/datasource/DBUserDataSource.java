package com.example.ignite.server.datasource;

import com.example.ignite.server.cachestore.UserCacheStore;
import com.example.ignite.server.entity.User;
import com.example.ignite.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("userDb")
public class DBUserDataSource implements UserDataSource {

    private static final Logger log = LoggerFactory.getLogger(DBUserDataSource.class);


    @Autowired
    UserRepository repository;

    @Override
    public User getUserById(UUID id) {
        log.info("Getting data from DB");
        return repository.findById(id).orElse(null);
    }
}

