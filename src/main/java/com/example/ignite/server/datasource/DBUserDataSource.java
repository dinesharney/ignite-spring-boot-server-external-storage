package com.example.ignite.server.datasource;

import com.example.ignite.server.entity.User;
import com.example.ignite.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userDb")
public class DBUserDataSource implements UserDataSource {

    @Autowired
    UserRepository repository;

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }
}

