package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestEmailAndPassword(@NotBlank String email, @NotBlank String password) {
}
