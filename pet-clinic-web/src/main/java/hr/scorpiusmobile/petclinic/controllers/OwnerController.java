package hr.scorpiusmobile.petclinic.controllers;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "/index", "/index.html", ".html"})
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        //find by last name
        List<Owner> resultList = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (resultList.isEmpty()) {
            //no owners found
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (resultList.size() == 1) {
            //one owner found
            log.debug("Found one owner");
            owner = resultList.get(0);
            return ("redirect:/owners/" + owner.getId());
        } else {
            //multiple owners found
            model.addAttribute("selections", resultList);
            return "owners/ownersList";
        }
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable String ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownersDetails");
        mav.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.debug("There are binding errors so I will return empty update Owner form view.");
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            log.debug("Owner successfully saved.");
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable String ownerId, Model model){

        model.addAttribute("owner",ownerService.findById(Long.valueOf(ownerId)));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult, @PathVariable String ownerId){

        if (bindingResult.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
            owner.setId(Long.valueOf(ownerId));
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
