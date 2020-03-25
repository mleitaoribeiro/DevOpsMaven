package switch2019.project.applicationLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US006CreatePersonAccountServiceTest {

    private PersonRepository personRepo;
    private AccountRepository accountRepo;
    private US006CreatePersonAccountService service;

    @BeforeEach
    void universeSetUp() {
        personRepo = new PersonRepository();
        accountRepo = new AccountRepository();
        service = new US006CreatePersonAccountService(personRepo, accountRepo);

        //Add people to Repository
        personRepo.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepo.createPerson("Maria Santos", new DateAndTime(1995, 4, 12), new Address("Miragaia"),
                new Address("Rua de Camões", "Porto", "4220-099"), new Email("maria.santos@live.com.pt"));
        personRepo.createPerson("Mariana Alves", new DateAndTime(1987, 9, 11), new Address("Fafe"),
                new Address("Rua de Tagilde", "Vizela", "4620-500"), new Email("mariana.alves@gmail.com"));
    }


    @Test
    @DisplayName("Test If several accounts are created for an existing Person - Main Scenario")
    void testIfPersonAccountIsCreatedMainScenario() {
        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination1 = "Revolut";
        String accountDescription1 = "OnlineShopping";
        String accountDenomination2 = "Active";
        String accountDescription2 = "For sharing expenses";
        String accountDenomination3 = "CXG";
        String accountDescription3 = "Allowance paychecks";

        CreatePersonAccountDTO personAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination1, accountDescription1);
        Account expected1 = new Account(new Denomination(accountDenomination1), new Description(accountDescription1),
                personRepo.findPersonByEmail(new Email(personEmail)).getID());

        CreatePersonAccountDTO personAccountDTO2 = new CreatePersonAccountDTO(personEmail, accountDenomination2, accountDescription2);
        Account expected2 = new Account(new Denomination(accountDenomination2), new Description(accountDescription2),
                personRepo.findPersonByEmail(new Email(personEmail)).getID());

        CreatePersonAccountDTO personAccountDTO3 = new CreatePersonAccountDTO(personEmail, accountDenomination3, accountDescription3);
        Account expected3 = new Account(new Denomination(accountDenomination3), new Description(accountDescription3),
                personRepo.findPersonByEmail(new Email(personEmail)).getID());
        //Act
        Account accountCreated1 = service.createPersonAccount(personAccountDTO1).get();
        Account accountCreated2 = service.createPersonAccount(personAccountDTO2).get();
        Account accountCreated3 = service.createPersonAccount(personAccountDTO3).get();

        //Assert

        Assertions.assertAll(
                () -> assertEquals(expected1, accountCreated1),
                () -> assertEquals(expected2, accountCreated2),
                () -> assertEquals(expected3, accountCreated3)
        );

    }

    @Test
    @DisplayName("Test If person Account is created - Happy Case - Number of accounts increased")
    void testIfPersonAccountWasCreatedCompareSize() {

        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();
        int numberOfExpectedAccountsInTheRepositoryAfter = 1;
        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                personRepo.findPersonByEmail(new Email(personEmail)).getID());

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        Account accountCreated = service.createPersonAccount(createPersonAccountDTO).get();

        int realNumberOfAccountsInTheRepositoryAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(expectedAccountsBefore, realAccountsBefore),
                () -> assertEquals(numberOfExpectedAccountsInTheRepositoryAfter, realNumberOfAccountsInTheRepositoryAfter)
        );
    }


    @Test
    @DisplayName("Test If User Account is Created - person ID does not exists in Repository")
    void testIfAccountIsCreateNonExistingID() {
        //Arrange
        String personEmail = "carolina.dias@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO personAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(personAccountDTO1);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - account already exists on Repository")
    void testIfAccountIsNotCreatedWhenAlreadyExists() {
        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO personAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        service.createPersonAccount(personAccountDTO1);

        try {
            service.createPersonAccount(personAccountDTO1);
        }
        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If person Account isn't created - account already exists on repository - Number of accounts has not increased")
    void testIfPersonAccountWasNotCreatedCompareSize() {

        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        int expectedAccountsBefore = 1;
        int expectedAccountsAfter = 1;

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        service.createPersonAccount(personAccountDTO);
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();
        try {
            service.createPersonAccount(personAccountDTO);

            //Assert
        } catch (IllegalArgumentException accountAlreadyExists) {
            int realAccountsAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

            Assertions.assertAll(
                    () -> assertEquals("This Account already exists for that ID.", accountAlreadyExists.getMessage()),
                    () -> assertEquals(expectedAccountsBefore, realAccountsBefore),
                    () -> assertEquals(expectedAccountsAfter, realAccountsAfter)
            );
        }

    }

    @Test
    @DisplayName("Test If User Account is created with an existing Person - Main Scenario")
    void testIfPersonAccountIsCreated() {
        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                personRepo.findPersonByEmail(new Email(personEmail)).getID());

        //Act
        Account accountCreated = service.createPersonAccount(personAccountDTO).get();

        //Assert
        assertEquals(expected, accountCreated);
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID null")
    void testIfAccountIsCreateNullID() {
        //Arrange
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(null, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(personAccountDTO);
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

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(personAccountDTO);
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

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(personEmail, null, accountDescription);

        //Act
        try {
            service.createPersonAccount(personAccountDTO);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Null Description")
    void testIfAccountIsCreateNullDescription() {
        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";

        CreatePersonAccountDTO personAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, null);

        //Act
        try {
            service.createPersonAccount(personAccountDTO);

        //Assert
        } catch (IllegalArgumentException invalid) {
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If account is created - Happy Case")
    void testIfPersonAccountWasCreatedOptionalHappyCase() {

        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        Optional<Account> accountCreated = service.createPersonAccount(createPersonAccountDTO);

        //Assert
        assertTrue(accountCreated.isPresent());
    }

}

