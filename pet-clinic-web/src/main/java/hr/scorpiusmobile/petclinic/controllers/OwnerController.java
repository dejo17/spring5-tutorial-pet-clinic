package hr.scorpiusmobile.petclinic.controllers;

import hr.scorpiusmobile.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "/index", "/index.html", ".html"})
    public String listOfOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {

        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner (@PathVariable("ownerId") String ownerId){
        log.debug("Lagani debug message za probu");
        ModelAndView mav = new ModelAndView("owners/ownersDetails");
        mav.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return mav;
    }


}
