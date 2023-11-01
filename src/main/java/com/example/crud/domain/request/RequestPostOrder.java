package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestPostOrder(@NotBlank String email, @NotBlank String orders, Integer quantity) {
}
