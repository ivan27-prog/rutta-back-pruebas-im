package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        @NotBlank(message = "El nombre del producto no puede estar en blanco")
        @Size(min = 2, max = 200)
        String productName,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a cero")
        BigDecimal price,

        String activity,

        String level,

        @NotNull(message = "El id de la categoría es obligatorio")
        Long categoryId,

        List<Long> colorIds,

        List<Long> sizeIds
) {
}