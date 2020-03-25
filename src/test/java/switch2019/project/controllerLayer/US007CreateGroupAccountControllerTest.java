package switch2019.project.controllerLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US007CreateGroupAccountControllerTest {

    private PersonRepository personRepo;
    private GroupsRepository groupsRepo;
    private AccountRepository accountRepo;
    private US007CreateGroupAccountController controller;
    private US007CreateGroupAccountService service;

    @BeforeEach
    void universeSetUp() {

        personRepo = new PersonRepository();
        groupsRepo = new GroupsRepository();
        accountRepo = new AccountRepository();

        service = new US007CreateGroupAccountService(personRepo, groupsRepo, accountRepo);
        controller = new US007CreateGroupAccountController(service);


        //Persons used to create groups (ADMINS)

        Person personJoaoCardoso = personRepo.createPerson("João Cardoso", new DateAndTime(1993, 1, 13), new Address("Porto"),
                new Address("Rua do Amadeu", "Porto", "4000-189"), new Email("joao.cardoso_12@hotmail.com"));
        Person personMiluAlbertina = personRepo.createPerson("Milu Albertina", new DateAndTime(1985, 1, 15), new Address("Guimarães"),
                new Address("Rua das uvas", "Faro", "4000-189"), new Email("milu@gmail.com"));
        Person personRobertoAlmeida = personRepo.createPerson("Roberto Almeida ", new DateAndTime(2000, 3, 15), new Address("Lisboa"),
                new Address("Rua 2 ", "Lisboa", "4356-189"), new Email("roberto_a.0@gmail.com"));
        Person personFrederico = personRepo.createPerson("Frederico Caveira ", new DateAndTime(1999, 10, 20), new Address("Faro"),
                new Address("Rua da uva ", "Lisboa", "4543-136"), new Email("112345@isep.ipp.pt"));


        //Person used to add to groups (MEMBERS)

        Person personJose = personRepo.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("jose@gmai.com"));
        Person personRafael = personRepo.createPerson("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("rafael_2@hotmail.com"));
        Person personMaria = personRepo.createPerson("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123313@isep.ipp.pt"));
        Person personMariana = personRepo.createPerson("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("112332@isep.ipp.pt"));


        //Add Groups to Repository

        groupsRepo.createGroup(new Description("Familia"), personJoaoCardoso);
        groupsRepo.createGroup(new Description("Friends"), personMiluAlbertina);
        groupsRepo.createGroup(new Description("Market"), personRobertoAlmeida);
        groupsRepo.createGroup(new Description("Isep"), personFrederico);

        //Add members to groups

        Group groupFamilia = groupsRepo.findGroupByDescription(new Description("Familia"));
        groupFamilia.addMember(personJose);
        groupFamilia.addMember(personMaria);

        Group groupFriends = groupsRepo.findGroupByDescription(new Description("Friends"));
        groupFriends.addMember(personRafael);
        groupFriends.addMember(personFrederico);
        groupFriends.addMember(personJoaoCardoso);

        Group groupMarket = groupsRepo.findGroupByDescription(new Description(("MArket")));
        groupMarket.addMember(personMariana);
        groupMarket.setAdmin(personMaria);

        Group groupIsep = groupsRepo.findGroupByDescription(new Description("Isep"));
        groupIsep.addMember(personMaria);
        groupIsep.addMember(personMariana);

    }


    /**
     * Test If group Account is created - Happy Cases
     */

    @Test
    @DisplayName("Test If group Account is created - Main Scenario - Happy Case")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);
        AccountDTO expected = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        //Act
        AccountDTO accountCreated = controller.createGroupAccount(createGroupAccountDTO).get();

        //Assert
        assertEquals(expected, accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Number of accounts increased")
    void testIfGroupAccountWasCreatedCompareSize() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();
        int numberOfExpectedAccountsInTheRepositoryAfter = 1;
        AccountDTO expected = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        AccountDTO accountCreated = controller.createGroupAccount(createGroupAccountDTO).get();

        int realNumberOfAccountsInTheRepositoryAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(expectedAccountsBefore, realAccountsBefore),
                () -> assertEquals(numberOfExpectedAccountsInTheRepositoryAfter, realNumberOfAccountsInTheRepositoryAfter)
        );
    }


    /**
     * Test If group Account is created - Failing scenarios
     */


    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin - Number of accounts has not increased")
    void testIfGroupAccountWasCreatedNotAdminNumberOfAccounts() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        Optional <AccountDTO> accountCreated = controller.createGroupAccount(createGroupAccountDTO);
        int realNumberOfAccountsInTheRepository = 0;

        //Assert
        Assertions.assertAll(
                () -> assertFalse(accountCreated.isPresent()),
                () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
        );

    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member - Number of accounts has not increased")
    void testIfGroupAccountWasCreatedNotGroupMemberNumberOfAccounts() {

        //Arrange
        String personEmail = "roberto_a.0@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act

        Optional <AccountDTO> accountCreated = controller.createGroupAccount(createGroupAccountDTO);
        int realNumberOfAccountsInTheRepository = 0;

        Assertions.assertAll(
                () -> assertFalse(accountCreated.isPresent()),
                () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
        );
    }


    @Test
    @DisplayName("Test If group Account is created - Person do not Exists")
    void testIfGroupAccountWasCreatedPersonNotExists() {

        //Arrange
        String personEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);


        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Group Do Not Exists")
    void testIfSeveralGroupAccountsWereCreatedGroupDoNotExists() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "xpto";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No group was found with the given description", invalid.getMessage());
        }
    }


//**********************************************************************************************************************\\


    /**
     * Test If group Account is created - Several accounts added - Null & Empty Values
     */


    @Test
    @DisplayName("Test If group Account is created - Email null")
    void testIfGroupAccountWasCreatedPersonIDNull() {

        //Arrange
        String personEmail = null;
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email can´t be null!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Email Empty")
    void testIfGroupAccountWasCreatedPersonEmailEmpty() {

        //Arrange
        String personEmail = "";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email it´s not valid", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Group ID null")
    void testIfGroupAccountWasCreatedGroupIDNull() {


        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = null;
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }

    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination null")
    void testIfGroupAccountWasCreatedAccountDenominationNull() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                null, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination Empty")
    void testIfGroupAccountWasCreatedAccountDenominationEmpty() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        // Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description null")
    void testIfGroupAccountWasCreatedAccountDescriptionNull() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, null);

        //Act
        try {

            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description Empty")
    void testIfGroupAccountWasCreatedAccountDescriptionEmpty() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online Shopping";
        String accountDescription = "";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Email null - Number of accounts has not increased ")
    void testIfGroupAccountWasCreatedPersonIDNullNumberOfAccounts() {

        //Arrange
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(null, groupDescription,
                accountDenomination, accountDescription);


        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();
            //Assert
            Assertions.assertAll(
                    () -> assertEquals("The email can´t be null!", invalid.getMessage()),
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
            );
        }
    }


    /**
     * Test If group Account is created - Several Accounts Added - Happy Cases - Simple Tests
     */


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created")
    void testIfSeveralGroupAccountsWereCreatedSeveralAccountsCreated() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);
        AccountDTO expected = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination1, accountDescription1);
        AccountDTO expected1 = new AccountDTO(groupDescription, accountDenomination1, accountDescription1);

        CreateGroupAccountDTO createGroupAccountDTO2 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination2, accountDescription2);
        AccountDTO expected2 = new AccountDTO(groupDescription, accountDenomination2, accountDescription2);


        //Act
        AccountDTO accountCreated = controller.createGroupAccount(createGroupAccountDTO).get();
        AccountDTO accountsCreated1 = controller.createGroupAccount(createGroupAccountDTO1).get();
        AccountDTO accountsCreated2 = controller.createGroupAccount(createGroupAccountDTO2).get();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(expected1, accountsCreated1),
                () -> assertEquals(expected2, accountsCreated2)
        );
    }


    /**
     * Test If group Account is created - Several Accounts Added - Happy Case - Check Number of Accounts
     */


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created - Number of accounts increased")
    void testIfSeveralGroupAccountsWereCreatedCompareSize() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination1, accountDescription1);

        CreateGroupAccountDTO createGroupAccountDTO2 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination2, accountDescription2);

        int numberOfExpectedAccountsInTheRepository = 3;

        //Act
        controller.createGroupAccount(createGroupAccountDTO);
        controller.createGroupAccount(createGroupAccountDTO1);
        controller.createGroupAccount(createGroupAccountDTO2);

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
    }


    /**
     * Test If group Account is created -  Failing scenarios - Simple Tests
     */


    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin")
    void testIfGroupAccountWasCreatedNotAdmin() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        Optional<AccountDTO> accountCreated = controller.createGroupAccount(createGroupAccountDTO);

        //Assert
        assertFalse(accountCreated.isPresent());
    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member")
    void testIfGroupAccountWasCreatedNotGroupMember() {

        //Arrange
        String personEmail = "roberto_a.0@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        Optional<AccountDTO> accountCreated = controller.createGroupAccount(createGroupAccountDTO);
        //Assert
        assertFalse(accountCreated.isPresent());

    }

    @Test
    @DisplayName("Test If group Account is created - Several Accounts - One of the Accounts already exists")
    void testIfSeveralGroupAccountsWereCreatedOneAccountAlreadyExists() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination1, accountDescription1);


        controller.createGroupAccount(createGroupAccountDTO);
        controller.createGroupAccount(createGroupAccountDTO1);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO1);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - False - Account already exists")
    void testIfGroupAccountWasCreatedAccountAlreadyExists() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);


        controller.createGroupAccount(createGroupAccountDTO);

        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }


    /**
     * Test If group Account is created -  Failing scenarios  - Check Number of Accounts
     */


    @Test
    @DisplayName("Test If group Account is created - False - Account Already Exists")
    void testIfGroupAccountsWasCreatedFalseCompareSize() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();
        int expectedAccountsAfter = 1;

        //Act
        controller.createGroupAccount(createGroupAccountDTO);
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            int realAccountsAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

            //Assert
            Assertions.assertAll(
                    () -> assertEquals("This Account already exists for that ID.", invalid.getMessage()),
                    () -> assertEquals(expectedAccountsBefore, realAccountsBefore),
                    () -> assertEquals(expectedAccountsAfter, realAccountsAfter)
            );
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Person do not Exists - Number of accounts has not increased")
    void testIfGroupAccountWasCreatedPersonNotExistsNumberOfAccounts() {

        //Arrange
        String personEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepository = 0;


        //Act
        try {
            controller.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();
            //Assert
            Assertions.assertAll(
                    () -> assertEquals("No person found with that email.", invalid.getMessage()),
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
            );
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Several Accounts Added - Number of accounts has not increased")
    void testIfSeveralGroupAccountsWereCreatedNumberOfAccounts() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        int numberOfExpectedAccountsInTheRepository = 2;

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination, accountDescription);
        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(personEmail, groupDescription,
                accountDenomination1, accountDescription1);


        //Act
        controller.createGroupAccount(createGroupAccountDTO);
        controller.createGroupAccount(createGroupAccountDTO1);

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        try {
            controller.createGroupAccount(createGroupAccountDTO1);
        } catch (IllegalArgumentException invalid) {
            //Assert
            Assertions.assertAll(
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository),
                    () -> assertEquals("This Account already exists for that ID.", invalid.getMessage())
            );
        }
    }

}