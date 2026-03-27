package com.guuh.user.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.plaf.nimbus.State;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street", length = 150)
    private String street;
    @Column(name = "number", length = 10)
    private String number;
    @Column(name = "complement", length = 100)
    private String complement;
    @Column(name = "neighborhood", length = 100)
    private String neighborhood;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "zip_code", length = 9)
    private String zipCode;
    @Column(name = "country", length = 60)
    private String country;
}
