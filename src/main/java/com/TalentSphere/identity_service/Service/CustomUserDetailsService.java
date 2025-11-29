package com.TalentSphere.identity_service.Service;

import com.TalentSphere.identity_service.entities.User;
import com.TalentSphere.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Optional;
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find our user from DB
        Optional<User> credential = repository.findByUsername(username);

        // 2. Convert it to Spring's "UserDetails" object
        return credential.map(user -> new CustomUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
