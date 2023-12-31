package com.example.crud.repository;

import com.example.crud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
