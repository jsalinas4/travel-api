package com.travel.travelapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleDTO {
    private Integer id;

    @NotBlank(message = "No debe estar vacio")
    private String country;
    
    @NotBlank(message = "La ciudad es obligatoria")
    private String city;

    @Size(max=300, message = "Maximo de 300 caracteres")
    @NotBlank(message = "La descripcion es obligatoria")
    private String description;
}
