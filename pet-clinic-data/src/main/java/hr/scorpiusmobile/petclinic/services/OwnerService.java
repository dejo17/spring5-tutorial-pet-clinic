package hr.scorpiusmobile.petclinic.services;

import hr.scorpiusmobile.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
    List<Owner> findByLastNameLike(String lastName);
}
