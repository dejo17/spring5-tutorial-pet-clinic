/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 
 */
public class Vet extends Person{

    private Set<Specialty> specialty = new HashSet<>();

    public Set<Specialty> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Set<Specialty> specialty) {
        this.specialty = specialty;
    }
}
