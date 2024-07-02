package com.example.security.securitydb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Your First Sample Hello world API is UP and Running...";
    }
}
