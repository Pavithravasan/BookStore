package com.assignment.bookStore.service;

import com.assignment.bookStore.dto.AuthDTO;
import com.assignment.bookStore.dto.ResponseDTO;


public interface AuthorizationService {

    ResponseDTO  register(AuthDTO authDTO);

    ResponseDTO  login(AuthDTO authDTO);
}
