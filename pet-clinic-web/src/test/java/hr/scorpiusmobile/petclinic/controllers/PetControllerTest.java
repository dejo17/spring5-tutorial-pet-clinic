package hr.scorpiusmobile.petclinic.controllers;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.model.Pet;
import hr.scorpiusmobile.petclinic.model.PetType;
import hr.scorpiusmobile.petclinic.services.OwnerService;
import hr.scorpiusmobile.petclinic.services.PetService;
import hr.scorpiusmobile.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import javax.print.attribute.HashAttributeSet;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    MockMvc mockMvc;

    PetController petController;

    @Mock
    PetTypeService petTypeService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;

    Owner owner;

    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());
        owner = Owner.builder().id(1L).build();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new PetController(ownerService, petTypeService, petService)).build();
    }

    @Test
    public void testInitCreationForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

    }


    @Test
    public void testProcessCreationForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    public void testInitUpdateForm() throws Exception{

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());


        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

    }

    @Test
    public void testProcessUpdateForm() throws  Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void populatePetTypes() {
        //TODO
    }

    @Test
    void findOwner() {
        //TODO
    }

    @Test
    void initOwnerBinder() {
        //TODO
    }
}