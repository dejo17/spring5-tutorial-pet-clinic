package hr.scorpiusmobile.petclinic.bootstrap;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.model.Pet;
import hr.scorpiusmobile.petclinic.model.PetType;
import hr.scorpiusmobile.petclinic.model.Vet;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import hr.scorpiusmobile.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Tash");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Pumpica");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Dino");
        owner1.setLastName("Mađar");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Daniel");
        owner2.setLastName("Glavić");

        ownerService.save(owner2);
        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Damir");
        vet1.setLastName("Jerčinović");

        vetService.save(vet1);

        Vet vet2= new Vet();
        vet2.setFirstName("Alenko");
        vet2.setLastName("Haramija");

        vetService.save(vet2);
        System.out.println("Vets loaded...");

    }
}
