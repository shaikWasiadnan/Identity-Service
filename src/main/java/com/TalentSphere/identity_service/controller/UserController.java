package com.TalentSphere.identity_service.controller;

import com.TalentSphere.identity_service.Service.AuthService;
import com.TalentSphere.identity_service.Service.JwtService;
import com.TalentSphere.identity_service.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private AuthService authService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public UserController(AuthService authService,AuthenticationManager authenticationManager,JwtService jwtService){
        this.authService=authService;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }
    @PostMapping("/register")
    public String addUser(@RequestBody User user){
        return authService.saveUser(user);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody User user){
        //pack username and password in to an envelope called UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken envelope = new UsernamePasswordAuthenticationToken(
                user.getUsername(),user.getPassword());

        // then envelope will be given to boss and if password is wrong then below line will directly throw exception
        Authentication authenticate = authenticationManager.authenticate(envelope);

        //if password is correct then below will execute

        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            throw new RuntimeException("Invalid access");
        }


    }
}
