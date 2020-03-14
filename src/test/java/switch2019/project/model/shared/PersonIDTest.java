package switch2019.project.model.shared;

import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.PersonName;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDTest {

    @Test
    void getPersonName() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        String expected = "Marta";

        //Act:
        String result = personID.getPersonName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void testEqualsSameObject() {
        //Arrange:
        PersonID personID = new PersonID("Marta");

        //Act:
        boolean result = personID.equals(personID);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectTrue() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        PersonID personID2 = new PersonID("Marta");

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjectFalse() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        PersonID personID2 = new PersonID("Alex");

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsNull() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        PersonID personID2 = null;

        //Act:
        boolean result = personID.equals(personID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        Denomination denomination = new Denomination("denomination");

        //Act:
        boolean result = personID.equals(denomination);

        //Assert:
        assertFalse(result);
    }

    @Test
    void testHashCodeTrue() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        PersonID personID2 = new PersonID("Marta");

        //Act & Assert:
        assertEquals(personID.hashCode(), personID2.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        //Arrange:
        PersonID personID = new PersonID("Marta");
        PersonID personID2 = new PersonID("Alex");

        //Act & Assert:
        assertNotEquals(personID.hashCode(), personID2.hashCode());
    }
}