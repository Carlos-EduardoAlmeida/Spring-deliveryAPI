package com.example.crud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address")
@Entity(name = "address")
public class Address {
    @Id
    private String userid;
    private String numero;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    //private String uf;
}