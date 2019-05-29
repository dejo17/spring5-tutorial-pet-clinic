/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity{

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    public Person(Long id, String firstName, String lastName){
        super(id);
        this.firstName=firstName;
        this.lastName=lastName;
    }
    
}
