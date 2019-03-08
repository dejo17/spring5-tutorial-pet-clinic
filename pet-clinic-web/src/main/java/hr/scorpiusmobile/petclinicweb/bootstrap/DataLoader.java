package hr.scorpiusmobile.petclinicweb.bootstrap;

import hr.scorpiusmobile.petclinicdata.model.Owner;
import hr.scorpiusmobile.petclinicdata.model.Vet;
import hr.scorpiusmobile.petclinicdata.services.OwnerService;
import hr.scorpiusmobile.petclinicdata.services.VetService;
import hr.scorpiusmobile.petclinicdata.services.map.OwnerServiceMap;
import hr.scorpiusmobile.petclinicdata.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {

        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Dino");
        owner1.setLastName("Mađar");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Daniel");
        owner2.setLastName("Glavić");

        ownerService.save(owner2);
        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Damir");
        vet1.setLastName("Jerčinović");

        vetService.save(vet1);

        Vet vet2= new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Alenko");
        vet2.setLastName("Haramija");

        vetService.save(vet2);
        System.out.println("Vets loaded...");

    }
}
