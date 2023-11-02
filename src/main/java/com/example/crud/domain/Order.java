package com.example.crud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String orders;
    private Integer quantity;
}
