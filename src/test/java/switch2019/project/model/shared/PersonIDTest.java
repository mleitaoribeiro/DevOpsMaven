package switch2019.project.model.shared;

import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.PersonName;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDTest {

    //@Test
    void getPersonName() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        String expected = "1234@isep.pt";

        //Act:
        Email result = personID.getEmail();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void testEqualsSameObject() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        //Act:
        boolean result = personID.equals(personID);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectTrue() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        PersonID personID2 = new PersonID(new Email("1234@isep.pt"));

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectFalse() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        PersonID personID2 = new PersonID(new Email("123@isep.pt"));

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsNull() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        PersonID personID2 = null;

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        Denomination denomination = new Denomination("denomination");

        //Act:
        boolean result = personID.equals(denomination);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTrue() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        PersonID personID2 = new PersonID(new Email("1234@isep.pt"));

        //Act & Assert:
        assertEquals(personID.hashCode(), personID2.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        //Arrange:
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        PersonID personID2 = new PersonID(new Email("123@isep.pt"));

        //Act & Assert:
        assertNotEquals(personID.hashCode(), personID2.hashCode());
    }
}