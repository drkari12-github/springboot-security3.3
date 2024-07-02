package com.example.security.securitydb.controller;

import com.example.security.securitydb.dto.AuthenticationRequest;
import com.example.security.securitydb.dto.RegisterRequest;
import com.example.security.securitydb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    //private final AuthenticationServic service;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try{
            return userService.authenticate(request);
        }catch(Exception e){
            return "Authentication failed for " + request.getEmail();
        }
    }
}
