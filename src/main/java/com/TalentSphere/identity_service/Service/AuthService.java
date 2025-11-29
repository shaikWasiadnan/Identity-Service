package com.TalentSphere.identity_service.Service;

import com.TalentSphere.identity_service.entities.User;
import com.TalentSphere.identity_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtService jwtService;
    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository,JwtService jwtService){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
        this.jwtService=jwtService;
    }
    public String saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added to DB Successfully";
    }
    public String generateToken(String username){
        return jwtService.generateToken(username);
    }
}
