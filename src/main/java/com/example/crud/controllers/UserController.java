package com.example.crud.controllers;

import com.example.crud.domain.Order;
import com.example.crud.domain.request.RequestEmailAndPassword;
import com.example.crud.domain.request.RequestPatchUser;
import com.example.crud.domain.request.RequestPostUser;
import com.example.crud.domain.User;
import com.example.crud.repository.AddressRepository;
import com.example.crud.repository.OrderRepository;
import com.example.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity findAddressByUserId(@PathVariable("id") String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestEmailAndPassword data){
        User user = userRepository.findByEmailAndPassword(data.email(), data.password());
        if(user != null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestPostUser data){
        try{
            User newUser = new User(data);
            userRepository.save(newUser);
            return ResponseEntity.ok(newUser);
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity updateUser(@RequestBody @Valid RequestPatchUser data){
        try{
            User newUser = userRepository.findUserById(data.id());
            if(!data.name().isEmpty())
                newUser.setName(data.name());
            if(!data.email().isEmpty())
                newUser.setEmail(data.email());
            if(!data.password().isEmpty())
                newUser.setPassword(data.password());
            userRepository.save(newUser);
            return ResponseEntity.ok(newUser);
        }catch (RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<Order> listOrder = orderRepository.findAllByUser(user.get());
            for (Order eachOrder : listOrder) {
                if (eachOrder.getUser().equals(user.get())) orderRepository.delete(eachOrder);
            }
            if(user.get().getAddress() != null)
                addressRepository.deleteById(user.get().getAddress().getId());
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
