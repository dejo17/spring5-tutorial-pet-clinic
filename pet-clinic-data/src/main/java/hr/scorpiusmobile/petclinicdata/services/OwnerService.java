package hr.scorpiusmobile.petclinicdata.services;

import hr.scorpiusmobile.petclinicdata.model.Owner;
import java.util.Set;

public interface OwnerService {

    Owner findByLastName (String lastName);
    Owner findByID(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();

}
