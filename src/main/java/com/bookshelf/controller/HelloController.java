package com.bookshelf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, BookShelf!";
    }

    @GetMapping("/status")
    public String status() {
        return "BookShelf is running. Phase 1: REST API in progress.";
    }
}
