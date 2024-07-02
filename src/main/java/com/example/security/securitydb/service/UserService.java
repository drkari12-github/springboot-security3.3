package com.example.security.securitydb.service;

import com.example.security.securitydb.dto.AuthenticationRequest;
import com.example.security.securitydb.dto.RegisterRequest;

public interface UserService{

    String register(RegisterRequest request);
    String authenticate(AuthenticationRequest authenticationRequest);
}