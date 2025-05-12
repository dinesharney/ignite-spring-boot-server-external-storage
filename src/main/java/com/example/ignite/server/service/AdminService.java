package com.example.ignite.server.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to handle business logic for Customer entities.
 */
@Service
public class AdminService {

    @Autowired
    private Ignite ignite;

    public void destroyCache(String cacheName){
        ignite.destroyCache(cacheName);
    }

    public void clearCache(String cacheName){
        IgniteCache<Object, Object> cache = ignite.cache(cacheName);
        cache.clear();
    }

    public List<Object> getAll(String cacheName){
        IgniteCache<Object, Object> cache = ignite.cache(cacheName);
        java.util.List<Object> list = new ArrayList();
        for (Cache.Entry<Object, Object> entry : cache) {
            list.add(entry.getValue());
        }
        return list;
    }

}