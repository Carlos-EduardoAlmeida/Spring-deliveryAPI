package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestEmail(@NotBlank String email) {
}
