package hr.scorpiusmobile.petclinicdata.services;

import hr.scorpiusmobile.petclinicdata.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
}
