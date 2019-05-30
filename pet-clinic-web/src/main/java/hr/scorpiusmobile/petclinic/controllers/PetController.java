package hr.scorpiusmobile.petclinic.controllers;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.model.PetType;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    public PetController(OwnerService ownerService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") String ownerId) {
        return ownerService.findById(Long.valueOf(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

}
