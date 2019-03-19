package hr.scorpiusmobile.petclinic.services.springdatajpa;

import hr.scorpiusmobile.petclinic.model.Owner;
import hr.scorpiusmobile.petclinic.repositories.OwnerRepository;
import hr.scorpiusmobile.petclinic.repositories.PetRepository;
import hr.scorpiusmobile.petclinic.repositories.PetTypeRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    final String LAST_NAME = "Milinovic";
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().lastName(LAST_NAME).id(1L).build();  //owner to be returned by mock
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);    //mock returns owner when findByLastName() is called
        assertEquals(returnOwner.getLastName(), service.findByLastName(LAST_NAME).getLastName());
        verify(ownerRepository).findByLastName(any()); //probably redundant but verifies if findByLastName() is called
    }

    @Test
    void findAll() {
        Set<Owner> ownersMock = new HashSet<>();
        ownersMock.add(Owner.builder().id(1l).build());
        ownersMock.add(Owner.builder().id(2l).build());

        when(ownerRepository.findAll()).thenReturn(ownersMock);
        Set<Owner> ownersReturnedByService = service.findAll();
        assertEquals(ownersMock, ownersReturnedByService);

        assertNotNull(ownersReturnedByService);
        assertEquals(2, ownersReturnedByService.size());
    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner returnedOwner = service.findById(1L);
        assertEquals(returnedOwner, returnOwner);
        assertNotNull(returnedOwner);
        assertEquals(returnOwner.getId(), returnedOwner.getId());
    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner returnedOwner = service.findById(2L);
        assertNull(returnedOwner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().firstName("Dean").build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteByID() {
        service.deleteByID(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}