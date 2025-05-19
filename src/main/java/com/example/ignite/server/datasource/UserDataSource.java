package com.example.ignite.server.datasource;

import com.example.ignite.server.entity.User;

public interface UserDataSource {
    User getUserById(Long id);
}

