package hr.scorpiusmobile.petclinicdata.services;

import hr.scorpiusmobile.petclinicdata.model.Owner;
import hr.scorpiusmobile.petclinicdata.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findByID(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
