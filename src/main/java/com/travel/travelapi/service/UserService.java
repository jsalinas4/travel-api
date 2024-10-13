package com.travel.travelapi.service;

import com.travel.travelapi.dto.AuthResponseDTO;
import com.travel.travelapi.dto.LoginDTO;
import com.travel.travelapi.dto.UserProfileDTO;
import com.travel.travelapi.dto.UserRegistrationDTO;
import com.travel.travelapi.enums.ERole;


public interface UserService {
    
    UserProfileDTO register(UserRegistrationDTO registrationDTO);
    UserProfileDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum);
    UserProfileDTO getUserProfileById(Integer id);
    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);
    
    // Autenticar usuario (login)
    AuthResponseDTO login(LoginDTO loginDTO);

}
