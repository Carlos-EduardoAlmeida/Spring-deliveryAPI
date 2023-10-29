package com.example.crud.domain;

import jakarta.persistence.Entity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Address consultCep(@PathVariable("cep") String cep);

}
