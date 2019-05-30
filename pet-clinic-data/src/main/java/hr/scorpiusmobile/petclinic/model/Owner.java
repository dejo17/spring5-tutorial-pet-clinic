/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;

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
@Table(name = "owners")
public class Owner extends Person {


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "city")
    private String city;

    @Builder
    public Owner(Long id, String firstName, String lastName, Set<Pet> pets, String address, String telephone, String city) {
        super(id, firstName, lastName);
        this.pets = pets;
        this.address = address;
        this.telephone = telephone;
        this.city = city;
    }


    /**
     * Return the Pet with given name, or null if none is found for this Owner
     *
     * @param name to test
     * @return Pet if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * @param name      to test
     * @param ignoreNew
     * @return Pet if pet name is already in use, null if there is no pet with given name
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }


}
