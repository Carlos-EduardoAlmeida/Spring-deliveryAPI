package com.example.crud.domain;

import com.example.crud.domain.request.RequestPostUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(RequestPostUser requestUser){
        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
    }
}
