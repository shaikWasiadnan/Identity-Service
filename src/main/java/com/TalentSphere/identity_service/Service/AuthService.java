package com.TalentSphere.identity_service.Service;

import com.TalentSphere.identity_service.entities.User;
import com.TalentSphere.identity_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }
    public String saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added to DB Successfully";
    }
}
