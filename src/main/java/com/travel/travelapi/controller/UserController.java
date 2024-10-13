package com.travel.travelapi.controller;

import com.travel.travelapi.dto.UserDTO;
import com.travel.travelapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /*@PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO newUser = userService.addUser(userDTO);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalStateException sms) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } */


}
