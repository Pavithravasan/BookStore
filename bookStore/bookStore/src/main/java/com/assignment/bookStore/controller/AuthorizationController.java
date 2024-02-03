package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.AuthDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.dto.UpdateProfileDTO;
import com.assignment.bookStore.service.AuthorizationService;
import com.assignment.bookStore.service.serviceImpl.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired(required = true)
    AuthorizationServiceImpl authorizationService;

    @PostMapping("/register")
    public ResponseDTO registerUser(@RequestBody AuthDTO authDTO){
        return authorizationService.register(authDTO);
    }

    @PostMapping("/login")
    public ResponseDTO loginWithEmail(@RequestBody AuthDTO authDTO){
        return authorizationService.login(authDTO);
    }

    @PutMapping("/updateProfile")
    public ResponseDTO  updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO){
        return authorizationService.updateProfile(updateProfileDTO);
    }

}

