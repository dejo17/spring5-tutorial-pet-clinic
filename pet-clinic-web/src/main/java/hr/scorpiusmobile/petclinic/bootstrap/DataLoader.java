package hr.scorpiusmobile.petclinic.bootstrap;

import hr.scorpiusmobile.petclinic.model.*;
import hr.scorpiusmobile.petclinic.services.*;
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
    private final VisitService visitService;
    private final PetService petService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService,
                      VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
        this.petService = petService;
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
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry .setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Dino");
        owner1.setLastName("Poljak");
        owner1.setAddress("Brace Karamazovi 3");
        owner1.setCity("Buzet");
        owner1.setTelephone("08225456");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Daniel");
        owner2.setLastName("Horvat");
        owner2.setAddress("Misje selo 6");
        owner2.setCity("Osijek");
        owner2.setTelephone("099853253");

        ownerService.save(owner2);
        System.out.println("Owners loaded...");

        Pet exit = new Pet();
        exit.setPetType(savedDogPetType);
        exit.setOwner(owner1);
        exit.setName("Exit");
        exit.setBirthDate(LocalDate.now());
        owner1.getPets().add(exit);
        petService.save(exit);

        Pet mrtvaMacka = new Pet();
        mrtvaMacka.setPetType(savedCatPetType);
        mrtvaMacka.setOwner(owner2);
        mrtvaMacka.setName("Whiskas");
        mrtvaMacka.setBirthDate(LocalDate.now());
        owner2.getPets().add(mrtvaMacka);
        petService.save(mrtvaMacka);

        Visit mrtvaMackaVisit = new Visit();
        mrtvaMackaVisit.setPet(mrtvaMacka);
        mrtvaMackaVisit.setDate(LocalDate.now());
        mrtvaMackaVisit.setDescription("Kise, kaslje, auto ju gazi");
        visitService.save(mrtvaMackaVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Damir");
        vet1.setLastName("Miskovic");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Aldin");
        vet2.setLastName("Drakula");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);
        System.out.println("Vets loaded...");
    }
}
