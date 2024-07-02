package com.example.security.securitydb.service;

import com.example.security.securitydb.entity.User;
import com.example.security.securitydb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailServiceImpl implements CustomUserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails mapDataForRoles(String email) {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null; // you can later set the role and permission details for user.
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return user.isAccountNonExpired();
                }

                @Override
                public boolean isAccountNonLocked() {
                    return user.isAccountNonLocked();
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return user.isCredentialsNonExpired();
                }

                @Override
                public boolean isEnabled() {
                    return user.isEnabled();
                }
            };
        } catch (Exception ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = mapDataForRoles(username);
        return user;
    }
}
