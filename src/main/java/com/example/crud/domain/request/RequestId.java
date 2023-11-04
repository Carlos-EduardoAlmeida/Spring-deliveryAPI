package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestId(@NotBlank String id) {
}
