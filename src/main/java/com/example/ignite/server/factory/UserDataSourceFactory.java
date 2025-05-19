package com.example.ignite.server.factory;

import com.example.ignite.server.datasource.UserDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserDataSourceFactory {

    private final Map<String, UserDataSource> sources;

    @Autowired
    public UserDataSourceFactory(List<UserDataSource> sourceList) {
        this.sources = sourceList.stream()
                .collect(Collectors.toMap(bean -> bean.getClass()
                        .getAnnotation(Component.class).value(), Function.identity()));
    }

    public UserDataSource getSource(String key) {
        return sources.getOrDefault(key, sources.get("userCache"));
    }
}

