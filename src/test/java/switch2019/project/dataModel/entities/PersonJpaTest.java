package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonJpaTest {

    private PersonJpa personJpa;

    @BeforeEach
    public void setup() {
        personJpa = new PersonJpa("marta@gmail.com", "marta", "12-04-1995", "Porto",
                new AddressJpa("Avenida António Domingues", "Matosinhos", "4200-500"));

    }

    @Test
    @DisplayName("Test equals same object")
    void testEqualsSameObject() {
        //Assert
        assertEquals(personJpa, personJpa);
    }

    @Test
    @DisplayName("Test equals null object")
    void testEqualsNullObject() {
        //Arrange
        PersonJpa nullPersonJpa = null;

        //Assert
        assertNotEquals(nullPersonJpa, personJpa);
    }

    @Test
    @DisplayName("Test equals different object")
    void testEqualsDifferentObject() {
        //Arrange
        PersonJpa otherPersonJpa = new PersonJpa("joao@gmail.com", "joao", "12-04-1995", "Porto",
                new AddressJpa("Avenida António Domingues", "Matosinhos", "4200-500"));

        //Act
        boolean result = otherPersonJpa.equals(personJpa);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals different class object")
    void testEqualsDifferentClassObject() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1995, 04, 12), new Address("Porto"),
                new Address("Avenida António Domingues", "Matosinhos", "4200-500"), new Email("marta@gmail.com"));

        //Act
        boolean result = person.equals(personJpa);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashcode() {
        //Arrange
        PersonJpa samePerson = personJpa;
        PersonJpa otherPersonJPA = new PersonJpa("joao@gmail.com", "joao", "12-04-1995", "Porto",
                new AddressJpa("Avenida António Domingues", "Matosinhos", "4200-500"));

        //Assert
        assertEquals(samePerson.hashCode(), personJpa.hashCode());
        assertNotEquals(otherPersonJPA.hashCode(), personJpa.hashCode());
    }

    @Test
    @DisplayName("Test get email - success")
    void testGetEmail() {
        //Arrange
        String expected = "marta@gmail.com";

        //Act
        String result = personJpa.getEmail();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get name - success")
    void getName() {
        //Arrange
        String expected = "marta";

        //Act
        String result = personJpa.getName();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get birth date - success")
    void getBirthDate() {
        //Arrange
        String expected = "12-04-1995";

        //Act
        String result = personJpa.getBirthDate();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get birth place - success")
    void getBirthPlace() {
        //Arrange
        String expected = "Porto";

        //Act
        String result = personJpa.getBirthPlace();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get address - success")
    void getAddress() {
        //Arrange
        AddressJpa expected = new AddressJpa("Avenida António Domingues", "Matosinhos", "4200-500");
        //Act
        AddressJpa result = personJpa.getAddress();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test get siblings list - success")
    void getSiblings() {
        //Arrange
        List<SiblingsJpa> expected = new ArrayList<>();
        //Act
        List<SiblingsJpa> result = personJpa.getSiblings();

        //Assert
        assertEquals(expected, result);
    }

}