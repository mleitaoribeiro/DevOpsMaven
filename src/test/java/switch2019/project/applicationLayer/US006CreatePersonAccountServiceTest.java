package switch2019.project.applicationLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.SerializationDTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

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

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination1, accountDescription1);
        AccountDTO accountDTOExpected1 = new AccountDTO(personEmail, accountDenomination1, accountDescription1);

        CreatePersonAccountDTO createPersonAccountDTO2 = new CreatePersonAccountDTO(personEmail, accountDenomination2, accountDescription2);
        AccountDTO accountDTOExpected2 = new AccountDTO(personEmail, accountDenomination2, accountDescription2);

        CreatePersonAccountDTO createPersonAccountDTO3 = new CreatePersonAccountDTO(personEmail, accountDenomination3, accountDescription3);
        AccountDTO accountDTOExpected3 = new AccountDTO(personEmail, accountDenomination3, accountDescription3);

        //Act
        AccountDTO accountDTOCreated1 = service.createPersonAccount(createPersonAccountDTO1);
        AccountDTO accountDTOCreated2 = service.createPersonAccount(createPersonAccountDTO2);
        AccountDTO accountDTOCreated3 = service.createPersonAccount(createPersonAccountDTO3);

        //Assert

        Assertions.assertAll(
                () -> assertEquals(accountDTOExpected1, accountDTOCreated1),
                () -> assertEquals(accountDTOExpected2, accountDTOCreated2),
                () -> assertEquals(accountDTOExpected3, accountDTOCreated3)
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
        int realAccountsBefore = accountRepo.repositorySize();
        int numberOfExpectedAccountsInTheRepositoryAfter = 1;
        AccountDTO accountDTOExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        AccountDTO accountDTOCreated = service.createPersonAccount(createPersonAccountDTO);

        int realNumberOfAccountsInTheRepositoryAfter = accountRepo.repositorySize();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(accountDTOExpected, accountDTOCreated),
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

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO1);
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
        String result = "";

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        service.createPersonAccount(createPersonAccountDTO1);
        try {
            service.createPersonAccount(createPersonAccountDTO1);
        } catch (IllegalArgumentException invalid) {
            result = invalid.getMessage();
        }

        //Assert
        assertEquals("This Account already exists for that ID.", result);

    }

    @Test
    @DisplayName("Test If person Account isn't created - account already exists on repository - Number of accounts has not increased")
    void testIfPersonAccountWasNotCreatedCompareSize() {

        //Arrange
        String personEmail = "maria.santos@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        String catchResult = "";
        int expectedAccountsBefore = 1;
        int expectedAccountsAfter = 1;

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        service.createPersonAccount(createPersonAccountDTO);
        int realAccountsBefore = accountRepo.repositorySize();
        try {
            service.createPersonAccount(createPersonAccountDTO);
        } catch (IllegalArgumentException accountAlreadyExists) {
            catchResult = accountAlreadyExists.getMessage();
        }

        int realAccountsAfter = accountRepo.repositorySize();
        String result = catchResult;

        //Assert

        Assertions.assertAll(
                () -> assertEquals("This Account already exists for that ID.", result),
                () -> assertEquals(expectedAccountsBefore, realAccountsBefore),
                () -> assertEquals(expectedAccountsAfter, realAccountsAfter)
        );
    }


    @Test
    @DisplayName("Test If User Account is created with an existing Person - Main Scenario")
    void testIfPersonAccountIsCreated() {
        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        AccountDTO accountDTOExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        AccountDTO accountDTOCreated = service.createPersonAccount(createPersonAccountDTO);

        //Assert
        assertEquals(accountDTOExpected, accountDTOCreated);
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID null")
    void testIfAccountIsCreateNullID() {
        //Arrange
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(null, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
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

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
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

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, null, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
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

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, null);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);

            //Assert
        } catch (IllegalArgumentException invalid) {
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

}

