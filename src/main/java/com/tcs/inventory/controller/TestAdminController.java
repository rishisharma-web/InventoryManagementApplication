package com.tcs.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/test")
public class TestAdminController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You reached the admin test endpoint!");
    }
}
