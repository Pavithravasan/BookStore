package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.AuthDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired(required = true)
    private AuthorizationService authorizationService;

    @PostMapping("/register")
    public ResponseDTO registerUser(@RequestBody AuthDTO authDTO){
        return authorizationService.register(authDTO);
    }

    @PostMapping("/login")
    public ResponseDTO loginWithEmail(@RequestBody AuthDTO authDTO){
        return authorizationService.login(authDTO);
    }
}
