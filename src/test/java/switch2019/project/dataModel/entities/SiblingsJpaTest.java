package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Email;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsJpaTest {

    /**
     * Tests for gets
     */

    @Test
    void getEmailPersonOne (){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        String expected = "person1@email.com";

        String actual = siblingsJpa.getEmailPersonOne();

        assertEquals(expected,actual);
    }

    @Test
    void getEmailPersonOneFalse (){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        String expected = "1@email.com";

        String actual = siblingsJpa.getEmailPersonOne();

        assertNotEquals(expected,actual);
    }

    @Test
    void getEmailPersonTwo (){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        String expected = "person2@email.com";

        String actual = siblingsJpa.getEmailPersonTwo();

        assertEquals(expected,actual);
    }

    @Test
    void getEmailPersonTwoFalse (){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        String expected = "2@email.com";

        String actual = siblingsJpa.getEmailPersonTwo();

        assertNotEquals(expected,actual);
    }

    @Test
    void equalsSame(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        boolean result = siblingsJpa.equals(siblingsJpa);

        assertTrue(result);
    }

    @Test
    void equals(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa("person1@email.com", "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertTrue(result);
    }

    @Test
    void equalsDifferent1(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa("1@email.com", "person2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferent2(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa("person1@email.com", "2@email.com");

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsNull(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = null;

        boolean result = siblingsJpa.equals(siblingsJpa2);

        assertFalse(result);
    }

    @Test
    void equalsDifferentObjects(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        PersonJpa personJpa = new PersonJpa();

        boolean result = siblingsJpa.equals(personJpa);

        assertFalse(result);
    }

    @Test
    void testHashCode(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa("person1@email.com", "person2@email.com");


        assertEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }
    @Test
    void testHashCodeNot(){
        SiblingsJpa siblingsJpa = new SiblingsJpa("person1@email.com", "person2@email.com");
        SiblingsJpa siblingsJpa2 = new SiblingsJpa("1@email.com", "person2@email.com");


        assertNotEquals(siblingsJpa.hashCode(),siblingsJpa2.hashCode());
    }
}