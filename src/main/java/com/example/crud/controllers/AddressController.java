package com.example.crud.controllers;

import com.example.crud.domain.Address;
import com.example.crud.domain.User;
import com.example.crud.domain.request.RequestEmail;
import com.example.crud.domain.request.RequestPostAddress;
import com.example.crud.repository.AddressRepository;
import com.example.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    public ResponseEntity registerAddress(@RequestBody @Valid RequestPostAddress data){
        try {
            User userSource = userRepository.findByEmail(data.email());
            Address newAddress = consultCep(data.cep());
            newAddress.setNumero(data.numero());
            newAddress.setComplemento(data.complemento());
            newAddress.setUser(userSource);
            if (!data.bairro().isBlank())
                newAddress.setBairro(data.bairro());
            if (!data.logradouro().isBlank())
                newAddress.setLogradouro(data.logradouro());
            if (!data.localidade().isBlank())
                newAddress.setLocalidade(data.localidade());
            addressRepository.save(newAddress);
            return ResponseEntity.ok(newAddress);
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping
    public ResponseEntity deleteAdress(@RequestBody @Valid RequestEmail data){
        try{
            User userDelete = userRepository.findByEmail(data.email());
            addressRepository.deleteById(userDelete.getAddress().getCep());
            return ResponseEntity.ok().build();
        }catch (RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/email")
    public ResponseEntity findAddressByEmail(@RequestBody RequestEmail data){
        User user = userRepository.findByEmail(data.email());
        Address address = addressRepository.findByUser(user);
        if(address != null)
            return ResponseEntity.ok(address);
        else return
                ResponseEntity.notFound().build();
    }

    @PostMapping("/cep")
    public ResponseEntity consultAddressForCep(@RequestBody @Valid Address data){
        try{
            Address address = consultCep(data.getCep());
            return ResponseEntity.ok(address);
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }
    }
    private Address consultCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Address> resp =
                restTemplate
                        .getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), Address.class);
        return resp.getBody();
    }
}
