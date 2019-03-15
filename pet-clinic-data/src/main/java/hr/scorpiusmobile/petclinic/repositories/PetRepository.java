package hr.scorpiusmobile.petclinic.repositories;

import hr.scorpiusmobile.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
