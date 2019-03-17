package hr.scorpiusmobile.petclinic.services.map;

import hr.scorpiusmobile.petclinic.model.Specialty;
import hr.scorpiusmobile.petclinic.model.Vet;
import hr.scorpiusmobile.petclinic.services.SpecialtyService;
import hr.scorpiusmobile.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object != null) {
            if (object.getSpecialty().size() > 0) {
                object.getSpecialty().forEach(speciality -> {
                    if (speciality.getId() == null) {
                        Specialty savedSpecialty = specialtyService.save(speciality);
                        speciality.setId(savedSpecialty.getId());
                    }
                });
            }
        }

        return super.save(object);

    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteById(id);
    }
}