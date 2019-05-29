/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, Set<Pet> pets, String address, String telephone, String city) {
        super(id, firstName, lastName);
        this.pets = pets;
        this.address = address;
        this.telephone = telephone;
        this.city = city;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    @Column(name="address")
    private String address;
    @Column(name="telephone")
    private String telephone;
    @Column(name="city")
    private String city;
}
