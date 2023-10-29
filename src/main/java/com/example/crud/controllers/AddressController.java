package com.example.crud.controllers;

import com.example.crud.domain.Address;
import com.example.crud.domain.CepService;
import com.example.crud.domain.User;
import com.example.crud.domain.request.RequestPostAddress;
import com.example.crud.domain.request.RequestUser;
import com.example.crud.repository.AddressRepository;
import com.example.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity findAddressByEmail(@RequestBody User data){
        String userid = userRepository.findByEmail(data.getEmail()).getId();
        Address address = addressRepository.findByUserid(userid);
        return ResponseEntity.ok(address);
    }

    @PostMapping("/cep")
    public ResponseEntity consultAddressForCep(@RequestBody @Valid Address data){
        Address address = consultCep(data.getCep());
        return ResponseEntity.ok(address);
    }


    @PostMapping
    public ResponseEntity registerAddress(@RequestBody @Valid RequestPostAddress data){
        User userSource = userRepository.findByEmail(data.email());
        Address newAddress = consultCep(data.cep());
        if(userSource != null) {
            newAddress.setNumero(data.numero());
            newAddress.setComplemento(data.complemento());
            newAddress.setUserid(userSource.getId());
            if(data.bairro() != null)
                newAddress.setBairro(data.bairro());
            if(data.logradouro() != null)
                newAddress.setLogradouro(data.logradouro());
            if(data.localidade() != null)
                newAddress.setLocalidade(data.localidade());
        }
        addressRepository.save(newAddress);
        return ResponseEntity.ok(newAddress);
    }

    @DeleteMapping
    public ResponseEntity deleteAdress(@RequestBody @Valid Address data){
        addressRepository.deleteById(data.getUserid());
        return ResponseEntity.ok().build();
    }
    private Address consultCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Address> resp =
                restTemplate
                        .getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), Address.class);
        return resp.getBody();
    }
}
