package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "El nombre de la categoría no puede estar en blanco")
        @Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
        String categoryName
) {
}