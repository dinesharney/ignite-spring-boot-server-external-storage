package com.example.ignite.server.datasource;

import com.example.ignite.server.entity.User;

import java.util.UUID;

public interface UserDataSource {
    User getUserById(UUID id);
}

