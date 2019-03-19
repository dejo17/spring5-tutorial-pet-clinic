package hr.scorpiusmobile.petclinic.services.map;

import hr.scorpiusmobile.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

   OwnerServiceMap ownerServiceMap;
   final  Long ownerID = 1L;
   final String lastName = "Smith";

    @BeforeEach
    void setUp() {

        ownerServiceMap=new OwnerServiceMap(new PetServiceMap(),new PetTypeServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerID).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet =ownerServiceMap.findAll();
        assertEquals(1,((Set) ownerSet).size());
    }

    @Test
    void findById() {
        Owner owner = new Owner();
        owner.setId(1L);
        assertEquals(ownerID,owner.getId());
    }

    @Test
    void saveExistId() {
        Owner owner2 = Owner.builder().firstName("Dean").build();
        owner2.setId(new Long(2));
       Owner savedOwner2=  ownerServiceMap.save(owner2);
        assertEquals(savedOwner2,ownerServiceMap.findById(2L));
    }
    @Test
    void saveNoId() {
        Owner owner2 = Owner.builder().build();
        Owner savedOwner2 = ownerServiceMap.save(owner2);

        assertNotNull(savedOwner2);
        assertNotNull(savedOwner2.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerID));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteByID() {
        ownerServiceMap.deleteByID(ownerID);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(lastName,smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner foo = ownerServiceMap.findByLastName("foo");
        assertNull(foo);
    }
}