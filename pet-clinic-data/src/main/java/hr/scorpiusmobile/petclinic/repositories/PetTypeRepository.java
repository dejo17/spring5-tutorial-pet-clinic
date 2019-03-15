package hr.scorpiusmobile.petclinic.repositories;

import hr.scorpiusmobile.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
