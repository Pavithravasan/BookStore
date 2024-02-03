package com.assignment.bookStore.service.serviceImpl;

import com.assignment.bookStore.dto.*;
import com.assignment.bookStore.entity.Book;
import com.assignment.bookStore.entity.User;
import com.assignment.bookStore.enums.Status;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.repository.UserRepository;
import com.assignment.bookStore.security.service.JwtService;
import com.assignment.bookStore.service.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ResponseDTO register(AuthDTO authDTO) {
        User user=modelMapper.map(authDTO,User.class);
        user.setPassword(passwordEncoder.encode(authDTO.getPassword()));
        user= userRepository.save(user);
        String token=jwtService.generateToken(user);
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        return new ResponseDTO<>(Status.SUCCESS,map,"User Registered Successfully");

    }
    @Override
    public ResponseDTO login(AuthDTO authDTO) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(),authDTO.getPassword()));

        User user= userRepository.findByEmail(authDTO.getEmail()).orElseThrow(()-> new UsernameNotFoundException("Invalid Login Credentials"));
        String token=jwtService.generateToken(user);
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("role",user.getAuthorities().toString());
        return new ResponseDTO<>(Status.SUCCESS,map,"User Successfully LoggedIn");

    }

    @Override
    public ResponseDTO updateProfile(UpdateProfileDTO updateProfileDTO) {
        User user=userRepository.findByEmail(updateProfileDTO.getEmail()).orElseThrow(()->(new UsernameNotFoundException("User Not found")));
        user.setPassword(passwordEncoder.encode(updateProfileDTO.getPassword()));
        user.setEmail(updateProfileDTO.getEmail());
        user.setUsername(updateProfileDTO.getUsername());
        userRepository.save(user);
       return new ResponseDTO(Status.SUCCESS,null,"Profile Updated Successfully");
    }
}
