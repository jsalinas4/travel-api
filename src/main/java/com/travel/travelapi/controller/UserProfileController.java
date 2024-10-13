package com.travel.travelapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelapi.dto.UserProfileDTO;
import com.travel.travelapi.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CUSTOMER', 'AUTHOR')")  // Permitir solo a CUSTOMER y AUTHOR
public class UserProfileController {

    private final UserService userService;

    // Obtener el perfil de un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer id) {
        UserProfileDTO userProfile = userService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    // Actualizar el perfil del usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Integer id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedProfile = userService.updateUserProfile(id, userProfileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }
}
