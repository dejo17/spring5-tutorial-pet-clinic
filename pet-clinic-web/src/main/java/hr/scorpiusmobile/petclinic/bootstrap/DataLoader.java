package hr.scorpiusmobile.petclinic.bootstrap;

import hr.scorpiusmobile.petclinic.model.*;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import hr.scorpiusmobile.petclinic.services.SpecialtyService;
import hr.scorpiusmobile.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Tash");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Pumpica");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Dino");
        owner1.setLastName("Mađar");
        owner1.setAddress("Brace Buchoffer 3");
        owner1.setCity("Crikvenica");
        owner1.setTelephone("0989696668");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Daniel");
        owner2.setLastName("Glavić");
        owner2.setAddress("Misje selo 6");
        owner2.setCity("Vele Drazice");
        owner2.setTelephone("0989116335");

        ownerService.save(owner2);
        System.out.println("Owners loaded...");

        Pet exit = new Pet();
        exit.setPetType(savedDogPetType);
        exit.setOwner(owner1);
        exit.setName("Exit");
        exit.setBirthDate(LocalDate.now());
        owner1.getPets().add(exit);

        Pet mrtvaMacka = new Pet();
        mrtvaMacka.setPetType(savedCatPetType);
        mrtvaMacka.setOwner(owner2);
        mrtvaMacka.setName("Whiskas");
        mrtvaMacka.setBirthDate(LocalDate.now());
        owner2.getPets().add(mrtvaMacka);

        Vet vet1 = new Vet();
        vet1.setFirstName("Damir");
        vet1.setLastName("Jerčinović");
        vet1.getSpecialty().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Alenko");
        vet2.setLastName("Haramija");
        vet2.getSpecialty().add(savedSurgery);

        vetService.save(vet2);
        System.out.println("Vets loaded...");
    }
}
