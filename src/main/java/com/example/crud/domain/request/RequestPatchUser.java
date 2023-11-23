package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestPatchUser(@NotBlank String id, String name, String email, String password) {
}
