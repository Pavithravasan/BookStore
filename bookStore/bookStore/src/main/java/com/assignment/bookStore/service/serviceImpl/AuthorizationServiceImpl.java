package com.assignment.bookStore.service.serviceImpl;

import com.assignment.bookStore.dto.AuthDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.entity.User;
import com.assignment.bookStore.enums.Status;
import com.assignment.bookStore.repository.UserRepository;
import com.assignment.bookStore.security.service.JwtService;
import com.assignment.bookStore.service.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    UserRepository userRepository;
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

        User user= userRepository.findByEmail(authDTO.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User with given email is not found"));
        String token=jwtService.generateToken(user);
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("role",user.getAuthorities().toString());
        return new ResponseDTO<>(Status.SUCCESS,map,"User Successfully LoggedIn");

    }
}
