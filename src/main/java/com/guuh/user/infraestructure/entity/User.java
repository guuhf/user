package com.guuh.user.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , length = 100)
    private String name;
    @Column(name = "email" , length = 100, unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;


}
