package switch2019.project.applicationLayer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.SiblingsDTO;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.infrastructure.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class US001AreSiblingsServiceTest {

    private static PersonRepository personRepo;
    private static US001AreSiblingsService service;

    @BeforeAll
    static void universeSetUp () {

        personRepo = new PersonRepository();

        service = new US001AreSiblingsService(personRepo);

        Person father = personRepo.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));
        Person father2 = personRepo.createPerson("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("father2@isep.ipp.pt"));
        Person mother = personRepo.createPerson("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("mother@isep.ipp.pt"));
        Person mother2 = personRepo.createPerson("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("mother2@isep.ipp.pt"));

        //father and father2 are in each other list of siblings
        father.addSibling(father2);


        //Two Siblings with same Father and Mother
        Person antonio = personRepo.createPerson("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("child1@isep.ipp.pt"));
        Person manuel = personRepo.createPerson("Manuel", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("child2@isep.ipp.pt"));

        //And they are in each other lists
        antonio.addSibling(manuel);


        //Only same Mother with antonio
        //Roberto
        personRepo.createPerson("Roberto", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother, father2, new Email("child3@isep.ipp.pt"));


        //Only same Father with antonio
        //Amalia
        personRepo.createPerson("Amália", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), mother2, father, new Email("child4@isep.ipp.pt"));
    }


    /**
     * US001
     * Test if two persons are siblings
     */
    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String antonioEmail = "child1@isep.ipp.pt";
        String manuelEmail = "child2@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(antonioEmail, manuelEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void AreSiblingsSameMother() {
        //Arrange
        String antonioEmail = "child1@isep.ipp.pt";
        String robertoEmail = "child3@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(antonioEmail, robertoEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(siblings);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void AreSiblingsSameFather() {
        //Arrange
        String antonioEmail = "child1@isep.ipp.pt";
        String amaliaEmail = "child4@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(antonioEmail, amaliaEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void AreSiblingsInTheSiblingsList() {
        //Arrange
        String joseEmail = "father@isep.ipp.pt";
        String rafaelEmail = "father2@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(joseEmail, rafaelEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        String joseEmail = "father@isep.ipp.pt";
        String robertoEmail = "child3@isep.ipp.pt";
        String mariaEmail = "mother@isep.ipp.pt";
        String amaliaEmail = "child4@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(joseEmail, robertoEmail);
        SiblingsDTO siblingsDTO2 = new SiblingsDTO(mariaEmail, amaliaEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);
        boolean siblings2 = service.areSiblings(siblingsDTO2);

        //Assert
        assertFalse(siblings && siblings2);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - invalid email")
    void AreSiblingsInvalidEmail() {
        //Arrange
        String joseEmail = "father@isep.ipp.pt";
        String robertoEmail = "child3@isep.ipp.pt";

        SiblingsDTO siblingsDTO = new SiblingsDTO(joseEmail, robertoEmail);

        //Act
        try {
            service.areSiblings(siblingsDTO);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("No person found with that ID.", description.getMessage());
        }
    }

}