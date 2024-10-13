package com.travel.travelapi.dto;

import java.time.LocalDate;

import com.travel.travelapi.enums.ERole;

import lombok.Data;

@Data
public class UserProfileDTO {

    private Integer id;
    private String email;
    private ERole role;  // El rol puede ser CUSTOMER o AUTHOR

    // Campos específicos para Customer
    private String firstName;  // Nombre del cliente o autor
    private String lastName;   // Apellido del cliente o autor

    private String nationality; //Para publicacion
    private LocalDate birthdate;
}
