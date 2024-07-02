package com.example.security.securitydb.service;

import com.example.security.securitydb.dto.AuthenticationRequest;
import com.example.security.securitydb.dto.RegisterRequest;
import com.example.security.securitydb.entity.User;
import com.example.security.securitydb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    //public AuthenticationResponse register(RegisterRequest request) {
    public String register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encodedPassword)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .build();
        var savedUser = userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            System.out.println("authentication obj -> "+ authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //saveUserToken(user, jwtToken);
            return "Successfully Authenticated"; // you can return accessToken here.
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Authentication failed " + ex.getMessage());
        }
    }
}