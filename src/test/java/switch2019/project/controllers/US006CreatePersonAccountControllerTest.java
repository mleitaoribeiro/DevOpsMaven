package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US006CreatePersonAccountService;

import static org.junit.jupiter.api.Assertions.*;

class US006CreatePersonAccountControllerTest {

    private static PersonRepository personRepo;
    private static AccountRepository accountRepo;
    private static US006CreatePersonAccountController controller;
    private static US006CreatePersonAccountService service;

    @BeforeAll
    static void universeSetUp() {
        personRepo = new PersonRepository();
        accountRepo = new AccountRepository();
        service = new US006CreatePersonAccountService(personRepo, accountRepo);
        controller = new US006CreatePersonAccountController(service);

        //Add people to Repository
        personRepo.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepo.createPerson("Maria Santos", new DateAndTime(1995, 4, 12), new Address("Miragaia"),
                new Address("Rua de Camões", "Porto", "4220-099"), new Email("maria.santos@live.com.pt"));
        personRepo.createPerson("Mariana Alves", new DateAndTime(1987, 9, 11), new Address("Fafe"),
                new Address("Rua de Tagilde", "Vizela", "4620-500"), new Email("mariana.alves@gmail.com"));
    }

    @Test
    @DisplayName("Test If User Account is created with an existing Person - Main Scenario")
    void testIfPersonAccountAreCreated() {
        //Arrange
        Person onePerson = personRepo.findPersonByID(new PersonID(new Email("jose.cardoso@hotmail.com")));
        PersonID onePersonID = onePerson.getID();

        //Act
        boolean accountCreated = controller.createPersonAccount(onePersonID, new Denomination("Revolut"),
                new Description("Online Shopping"));

        //Assert
        assertTrue(accountCreated);
    }

    @Test
    @DisplayName("Test If several accounts are created for an existing Person - Main Scenario")
    void testIfPersonAccountIsCreated() {
        //Arrange
        Person onePerson = personRepo.findPersonByID(new PersonID( new Email("maria.santos@live.com.pt")));
        PersonID onePersonID = onePerson.getID();

        //Act
        boolean accountsCreated = controller.createPersonAccount(onePersonID, new Denomination("Revolut"),
                new Description("OnlineShopping"))
                && service.createPersonAccount(onePersonID, new Denomination("MbWay"),
                new Description("For sharing expenses"))
                && service.createPersonAccount(onePersonID, new Denomination("CXG"),
                new Description("Allowance paychecks"));

        //Assert
        assertTrue(accountsCreated);
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID does not exists in Repository")
    void testIfAccountIsCreateNonExistingID() {
        //Arrange
        PersonID newPersonID = new PersonID(new Email("sousa.cardoso@gmail.com"));

        //Act
        try {
            controller.createPersonAccount(newPersonID, new Denomination("Revolut"),
                    new Description("OnlineShopping"));
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("This Person ID doesn't exist or it's null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID null")
    void testIfAccountIsCreateNullID() {
        //Arrange
        PersonID nullID = null;

        //Act
        try {
            controller.createPersonAccount(nullID, new Denomination("Revolut"),
                    new Description("Online Shopping"));
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("This Person ID doesn't exist or it's null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Email is Empty")
    void testIfAccountIsCreateEmptyID() {
        //Arrange

        //Act
        try {
            controller.createPersonAccount(new PersonID(new Email("")), new Denomination("Revolut"),
                    new Description("Online Shopping"));
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The email it´s not valid", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Null Denomination")
    void testIfAccountIsCreateNullDenomination() {
        //Arrange
        Person onePerson = personRepo.findPersonByID(new PersonID(new Email("jose.cardoso@hotmail.com")));
        PersonID onePersonID = onePerson.getID();

        Denomination nullDenomination = null;
        //Act
        try {
            controller.createPersonAccount(onePersonID, null,
                    new Description("Online Shopping"));
        }
        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Null Description")
    void testIfAccountIsCreateNullDescription() {
        //Arrange
        Person onePerson = personRepo.findPersonByID(new PersonID(new Email("mariana.alves@gmail.com")));
        PersonID onePersonID = onePerson.getID();

        Description nullDescription = null;

        try {
            controller.createPersonAccount(onePersonID, new Denomination("MbWay"), nullDescription );
            fail();
        }
        catch (IllegalArgumentException invalid) {
            assertEquals("Account Description can't be null.", invalid.getMessage());
        }
    }
}