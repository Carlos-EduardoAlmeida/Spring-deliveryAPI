package com.example.crud.domain;

import com.example.crud.domain.request.RequestUser;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String password;

    public User(RequestUser requestUser){
        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
    }
}
