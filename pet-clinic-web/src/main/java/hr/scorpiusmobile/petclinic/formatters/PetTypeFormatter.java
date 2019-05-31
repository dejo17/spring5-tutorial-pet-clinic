package hr.scorpiusmobile.petclinic.formatters;

import hr.scorpiusmobile.petclinic.model.PetType;
import hr.scorpiusmobile.petclinic.repositories.PetTypeRepository;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Sets;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'.
 */

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {

        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType petType : findPetTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException("PetType not found: " + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
