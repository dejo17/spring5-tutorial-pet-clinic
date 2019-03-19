package hr.scorpiusmobile.petclinic.services.map;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.model.Pet;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petType, PetTypeService petTypeService) {
        this.petService = petType;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if (object != null) {

            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type required!");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {

            return null;
        }

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {

        //old way:
        /*Set<Owner> owners = findAll();
        if (owners.size() > 0) {
            for (Owner owner : owners) {
                if (owner.getLastName() == lastName) {
                    return owner;
                }
            }
        }
        return null;*/
        //java 8 way, using stream:
        return findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
