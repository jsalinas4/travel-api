package com.travel.travelapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentDTO {
    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(max = 1000, message = "El comentario no puede exceder los 1000 caracteres")
    private String content;

    @NotNull(message = "El ID del artículo es requerido")
    private Integer articleId;
}
