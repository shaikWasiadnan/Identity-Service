package com.TalentSphere.identity_service.controller;

import com.TalentSphere.identity_service.Service.AuthService;
import com.TalentSphere.identity_service.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private AuthService authService;
    public UserController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public String addUser(@RequestBody User user){
        return authService.saveUser(user);
    }
}
