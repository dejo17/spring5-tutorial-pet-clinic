/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name="owners")
public class Owner extends Person {

    private Set<Pet> pets = new HashSet<>();
    @Column(name="address")
    private String address;
    @Column(name="telephone")
    private String telephone;
    @Column(name="city")
    private String city;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
