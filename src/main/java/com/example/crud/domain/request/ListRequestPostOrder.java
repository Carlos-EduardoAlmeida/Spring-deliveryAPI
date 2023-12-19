package com.example.crud.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListRequestPostOrder(@NotBlank String id, @NotNull List<RequestPostOrder> listRequestPostOrder) {
}
