package com.example.ignite.server.controller;

import com.example.common.dto.CustomerDTO;
import com.example.ignite.server.entity.Customer;
import com.example.ignite.server.service.AdminService;
import com.example.ignite.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller to handle Cache Admin  requests to manage issue related to Cache
 * like CorruptedTreeException or refreshing data.
 * Usage of this APIs should be restricted to Admin person only.
 *
 */
@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/cache/destroy")
    public ResponseEntity<String> destroyCache(@RequestParam String cache) {
        adminService.destroyCache(cache);
        return ResponseEntity.ok(cache);
    }

    @GetMapping("/cache/clear")
    public ResponseEntity<String> clearCache(@RequestParam String cache) {
        adminService.clearCache(cache);
        return ResponseEntity.ok(cache);
    }
}
