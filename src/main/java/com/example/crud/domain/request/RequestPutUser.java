package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestPutUser(@NotBlank String email, @NotBlank String password, String name, String newEmail, String newPassword) {
}
