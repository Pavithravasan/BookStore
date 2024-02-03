package com.assignment.bookStore.service;

import com.assignment.bookStore.dto.AuthDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.dto.UpdateProfileDTO;


public interface AuthorizationService {

    ResponseDTO  register(AuthDTO authDTO);

    ResponseDTO  login(AuthDTO authDTO);

    ResponseDTO  updateProfile(UpdateProfileDTO updateProfileDTO);
}
