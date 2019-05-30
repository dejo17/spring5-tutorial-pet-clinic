package hr.scorpiusmobile.petclinic.controllers;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

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
    public ModelAndView showOwner(@PathVariable("ownerId") String ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownersDetails");
        mav.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return mav;
    }


}
