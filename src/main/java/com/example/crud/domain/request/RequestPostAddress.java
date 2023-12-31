package com.example.crud.domain.request;

import jakarta.validation.constraints.NotBlank;

public record RequestPostAddress(@NotBlank String userId, @NotBlank String cep, String bairro, String logradouro, String localidade, String complemento, @NotBlank String numero, String uf) {
}
