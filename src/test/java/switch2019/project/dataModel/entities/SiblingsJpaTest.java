
package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsJpaTest {

    private PersonJpa ownerId;
    private PersonJpa ownerId2;

    @BeforeEach
    public void setup(){
        ownerId = new PersonJpa("person1@email.com","Maria","22-05-1994","Porto",
                new AddressJpa("Rua das flores", "Porto","4455-911"));
        ownerId2 = new PersonJpa("person2@email.com","Maria","22-05-1994","Porto",
                new AddressJpa("Rua das margaridas", "Porto","4455-911"));
    }


    /**
     * Tests for gets for SiblingsIdJpa
     */

    @Test
    void getEmailPersonOne (){

        SiblingsJpa.SiblingsIdJpa siblingsIdJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        PersonJpa expected = new PersonJpa("person1@email.com","Maria","22-05-1994","Porto",
                new AddressJpa("Rua das flores", "Porto","4455-911"));

        PersonJpa actual = siblingsIdJpa.getOwnerEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getEmailPersonOneFalse (){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        String expected = "1@email.com";

        PersonJpa actual = siblingsJpa.getOwnerEmail();

        assertNotEquals(expected,actual);
    }

    @Test
    void getEmailPersonTwo (){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        String expected = "person2@email.com";

        String actual = siblingsJpa.getSiblingEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getEmailPersonTwoFalse (){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        String expected = "2@email.com";

        String actual = siblingsJpa.getSiblingEmail();

        assertNotEquals(expected,actual);
    }

    @Test
    void equalsSame(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        boolean result = siblingsJpa.equals(siblingsJpa);

        assertTrue(result);
    }

    @Test
    void equals(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertTrue(result);
    }

    @Test
    void equalsDifferent1(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = new SiblingsJpa.SiblingsIdJpa(ownerId2, "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferent2(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = new SiblingsJpa.SiblingsIdJpa(ownerId, "2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsNull(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = null;

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferentObjects(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        PersonJpa personJpa = new PersonJpa();

        boolean result = siblingsJpa.equals(personJpa);

        assertFalse(result);
    }

    @Test
    void testHashCode(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");


        assertEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }
    @Test
    void testHashCodeNot(){
        SiblingsJpa.SiblingsIdJpa siblingsJpa = new SiblingsJpa.SiblingsIdJpa(ownerId, "person2@email.com");
        SiblingsJpa.SiblingsIdJpa siblingsJpa2 = new SiblingsJpa.SiblingsIdJpa(ownerId2, "person2@email.com");


        assertNotEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }



/**
     * Tests for SiblingsJpa
     */

    @Test
    void getIdOwner (){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        PersonJpa expected = new PersonJpa("person1@email.com","Maria","22-05-1994","Porto",
                new AddressJpa("Rua das flores", "Porto","4455-911"));

        PersonJpa actual = siblingsJpa.getId().getOwnerEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getIdOwnerFalse (){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        String expected = "1@email.com";

        PersonJpa actual = siblingsJpa.getId().getOwnerEmail();

        assertNotEquals(expected,actual);
    }

    @Test
    void getIdSiblings (){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        String expected = "person2@email.com";

        String actual = siblingsJpa.getId().getSiblingEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getIdSiblingsFalse (){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        String expected = "2@email.com";

        String actual = siblingsJpa.getId().getSiblingEmail();

        assertNotEquals(expected,actual);
    }

    @Test
    void equalsSameJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        boolean result = siblingsJpa.equals(siblingsJpa);

        assertTrue(result);
    }

    @Test
    void equalsJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(ownerId, "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertTrue(result);
    }

    @Test
    void equalsDifferent1Jpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(ownerId2, "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferent2Jpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(ownerId, "2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsNullJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = null;

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferentObjectsJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        PersonJpa personJpa = new PersonJpa();

        boolean result = siblingsJpa.equals(personJpa);

        assertFalse(result);
    }

    @Test
    void testHashCodeJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(ownerId, "person2@email.com");


        assertEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }
    @Test
    void testHashCodeNotJpa(){
        SiblingsJpa siblingsJpa = new SiblingsJpa(ownerId, "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(ownerId2, "person2@email.com");


        assertNotEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }

}
