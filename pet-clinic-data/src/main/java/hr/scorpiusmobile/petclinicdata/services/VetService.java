package hr.scorpiusmobile.petclinicdata.services;

import hr.scorpiusmobile.petclinicdata.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findByID(Long id);
    Vet save( Vet vet);
    Set< Vet> findAll();
}
