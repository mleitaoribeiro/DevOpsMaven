package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Denomination;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US006CreatePersonAccountService;

import static org.junit.jupiter.api.Assertions.*;

class US006CreatePersonAccountControllerTest {

    private PersonRepository personRepo;
    private AccountRepository accountRepo;
    private US006CreatePersonAccountController controller;
    private US006CreatePersonAccountService service;

    @BeforeEach
    void universeSetUp() {
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
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        //Act
        boolean accountCreated = service.createPersonAccount(personEmail, accountDenomination, accountDescription);

        //Assert
        assertTrue(accountCreated);
    }

    @Test
    @DisplayName("Test If several accounts are created for an existing Person - Main Scenario")
    void testIfPersonAccountIsCreated() {
        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination1 = "Revolut";
        String accountDescription1 = "OnlineShopping";
        String accountDenomination2 = "Active";
        String accountDescription2 = "For sharing expenses";
        String accountDenomination3 = "CXG";
        String accountDescription3 = "Allowance paychecks";
        //Act
        boolean accountsCreated = controller.createPersonAccount(personEmail, accountDenomination1,
                accountDescription1)
                && service.createPersonAccount(personEmail, accountDenomination2, accountDescription2)
                && service.createPersonAccount(personEmail, accountDenomination3, accountDescription3);

        //Assert
        assertTrue(accountsCreated);
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID does not exists in Repository")
    void testIfAccountIsCreateNonExistingID() {
        //Arrange
        String personEmail = "carolina.dias@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        //Act
        try {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("This Person ID doesn't exist.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - account already exists on Repository")
    void testIfAccountIsNotCreatedWhenAlreadyExists() {
        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        //Act
        try {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        }
        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If User Account is Created - person ID null")
    void testIfAccountIsCreateNullID() {
        //Arrange
        String personEmail = null;
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        //Act
        try {
            controller.createPersonAccount(null, accountDenomination, accountDescription);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The email can´t be null!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Email is Empty")
    void testIfAccountIsCreateEmptyID() {
        //Arrange
        String personEmail = "";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        //Act
        try {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
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
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        Denomination nullDenomination = null;
        //Act
        try {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
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
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = null;

        try {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
            fail();
        } catch (IllegalArgumentException invalid) {
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }
}