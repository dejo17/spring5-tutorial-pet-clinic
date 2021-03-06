/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.scorpiusmobile.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

}
