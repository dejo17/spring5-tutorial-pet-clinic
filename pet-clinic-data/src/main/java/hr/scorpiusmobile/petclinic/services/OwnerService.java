package hr.scorpiusmobile.petclinic.services;

import hr.scorpiusmobile.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
}
