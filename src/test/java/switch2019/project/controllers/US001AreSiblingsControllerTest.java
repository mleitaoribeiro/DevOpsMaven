package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US001AreSiblingsService;

import static org.junit.jupiter.api.Assertions.*;

class US001AreSiblingsControllerTest {

    private static PersonRepository personRepo;
    private static US001AreSiblingsController controller;
    private static US001AreSiblingsService service;

    @BeforeAll
    static void universeSetUp () {

        controller = new US001AreSiblingsController();
        service = new US001AreSiblingsService();

        personRepo = new PersonRepository();

        Person father = new Person("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));
        Person father2 = new Person("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("father2@isep.ipp.pt"));
        Person mother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("mother@isep.ipp.pt"));
        Person mother2 = new Person("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("mother2@isep.ipp.pt"));

        //Father
        personRepo.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));

        //Father2
        personRepo.createPerson("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("father2@isep.ipp.pt"));

        //Mother
        personRepo.createPerson("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("mother@isep.ipp.pt"));

        //Mother2
        personRepo.createPerson("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("mother2@isep.ipp.pt"));

        //Two Siblings with same Father and Mother
        //Antonio
        personRepo.createPersonWithParents("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("child1@isep.ipp.pt"));
        //Manuel
        personRepo.createPersonWithParents("Manuel", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("child2@isep.ipp.pt"));

        //Only same Mother with antonio
        //Roberto
        personRepo.createPersonWithParents("Roberto", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother, father2, new Email("child3@isep.ipp.pt"));

        //Only same Father with antonio
        //Amalia
        personRepo.createPersonWithParents("Amália", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), mother2, father, new Email("child4@isep.ipp.pt"));

        //Two people are in each other list of siblings
        Person firstFather = personRepo.findPersonByEmail(new Email ("father@isep.ipp.pt"));
        Person secondFather = personRepo.findPersonByEmail(new Email ("father2@isep.ipp.pt"));
        firstFather.addSibling(secondFather);
    }


    /**
     * US001
     * Test if two persons are siblings
     */
    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        Email antonioEmail = new Email("child1@isep.ipp.pt");
        Email manuelEmail = new Email("child2@isep.ipp.pt");

        //Act
        boolean siblings = controller.AreSiblings(service, personRepo, antonioEmail, manuelEmail);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void AreSiblingsSameMother() {
        //Arrange
        Email antonioEmail = new Email("child1@isep.ipp.pt");
        Email robertoEmail = new Email("child3@isep.ipp.pt");

        //Act
        boolean siblings = controller.AreSiblings(service, personRepo, antonioEmail, robertoEmail);

        //Assert
        assertTrue(siblings);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void AreSiblingsSameFather() {
        //Arrange
        Email antonioEmail = new Email("child1@isep.ipp.pt");
        Email amaliaEmail = new Email("child4@isep.ipp.pt");

        //Act
        boolean siblings = controller.AreSiblings(service, personRepo, antonioEmail, amaliaEmail);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void AreSiblingsInTheSiblingsList() {
        //Arrange
        Email joseEmail = new Email("father@isep.ipp.pt");
        Email rafaelEmail = new Email("father2@isep.ipp.pt");

        //Act
        boolean siblings = controller.AreSiblings(service, personRepo, joseEmail, rafaelEmail);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        Email joseEmail = new Email("father@isep.ipp.pt");
        Email robertoEmail = new Email("child3@isep.ipp.pt");
        Email mariaEmail = new Email("mother@isep.ipp.pt");
        Email amaliaEmail = new Email("child4@isep.ipp.pt");

        //Act
        boolean siblings = controller.AreSiblings(service, personRepo, joseEmail, robertoEmail);
        boolean siblings2 = controller.AreSiblings(service, personRepo, mariaEmail, amaliaEmail);

        //Assert
        assertFalse(siblings && siblings2);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - invalid email")
    void AreSiblingsInvalidEmail() {
        //Arrange
        Email joseEmail = new Email("father1@isep.ipp.pt");
        Email robertoEmail = new Email("child3@isep.ipp.pt");

        //Act
        try {
            controller.AreSiblings(service, personRepo, joseEmail, robertoEmail);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("No person found with that email.", description.getMessage());
        }

    }
}