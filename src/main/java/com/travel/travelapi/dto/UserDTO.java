package com.travel.travelapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private  Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max=50, message = "Maximo de 50 caracteres")
    private String firstName;
    @Size(max=50, message = "Maximo de 50 caracteres")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    private String email;
    private LocalDate birthDate;
    private String nationality;
}
