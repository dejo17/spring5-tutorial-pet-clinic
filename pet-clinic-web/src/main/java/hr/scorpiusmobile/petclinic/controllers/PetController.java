package hr.scorpiusmobile.petclinic.controllers;


import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.model.Pet;
import hr.scorpiusmobile.petclinic.model.PetType;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    public PetController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        log.debug("Populating petTypes model attribute in PetController");
        Set<PetType> petTypes = new HashSet<>();
        petTypes = petTypeService.findAll();
        log.debug("petTypes list has " + petTypes.size() + " items.");
        return petTypes;

    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") String ownerId) {
        return ownerService.findById(Long.valueOf(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        pet.setOwner(owner);
        owner.getPets().add(pet);

        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable String petId, Model model) {

        Pet pet = petService.findById(Long.valueOf(petId));
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, Owner owner, BindingResult result, Model model) {

        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
