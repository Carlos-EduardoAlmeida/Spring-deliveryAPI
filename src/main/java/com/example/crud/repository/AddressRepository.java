package com.example.crud.repository;

import com.example.crud.domain.Address;
import com.example.crud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AddressRepository extends JpaRepository<Address, String> {
    Address findByUser(User user);
}
