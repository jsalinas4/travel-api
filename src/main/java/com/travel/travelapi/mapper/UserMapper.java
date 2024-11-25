package com.travel.travelapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travel.travelapi.dto.AuthResponseDTO;
import com.travel.travelapi.dto.LoginDTO;
import com.travel.travelapi.dto.UserProfileDTO;
import com.travel.travelapi.dto.UserRegistrationDTO;
import com.travel.travelapi.entity.User;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserProfileDTO toUserProfileDTO(User user) {
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);

        return userProfileDTO;
    }

    // Convertir de LoginDTO a User (cuando procesas el login)
    public User toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    // Convertir de User a AuthResponseDTO para la respuesta de autenticación
    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token); // Asignar el token


        
            authResponseDTO.setFirstName(user.getFullName());
        

        // Asignar el rol del usuario
        authResponseDTO.setRole(user.getRole().getName().toString());

        return authResponseDTO;
    }
}
