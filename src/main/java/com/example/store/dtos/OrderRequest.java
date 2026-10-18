package com.example.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderRequest(
        @NotNull(message = "La fecha del pedido es obligatoria")
        LocalDate orderDate,

        @NotBlank(message = "El estado del pedido no puede estar en blanco")
        @Size(min = 2, max = 45, message = "El estado debe tener entre 2 y 45 caracteres")
        String orderStatus,

        @NotNull(message = "El monto total es obligatorio")
        @Positive(message = "El monto total debe ser mayor a cero")
        BigDecimal orderAmount

        // Long userId
) {
}