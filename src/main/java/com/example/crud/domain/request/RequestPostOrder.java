package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPostOrder(@NotBlank String id, @NotBlank String orders,@NotNull Integer quantity, String image, Integer price) {
}
