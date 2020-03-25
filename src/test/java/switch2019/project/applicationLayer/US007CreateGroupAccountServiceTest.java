package switch2019.project.applicationLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US007CreateGroupAccountServiceTest {


    private static PersonRepository personRepo;
    private static GroupsRepository groupsRepo;
    private static AccountRepository accountRepo;
    private static US007CreateGroupAccountService service;

    @BeforeEach
    void universeSetUp() {

        personRepo = new PersonRepository();
        groupsRepo = new GroupsRepository();
        accountRepo = new AccountRepository();

        service = new US007CreateGroupAccountService(personRepo, groupsRepo, accountRepo);

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
        groupsRepo.createGroup(new Description("MArket"), personRobertoAlmeida);
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
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)));

        //Act
        Account accountCreated = service.createGroupAccount(createGroupAccountDTO).get();

        //Assert
        assertEquals(expected, accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Number of accounts increased")
    void testIfGroupAccountWasCreatedCompareSize() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)));


        //Act
        Account accountCreated = service.createGroupAccount(createGroupAccountDTO).get();

        int numberOfExpectedAccountsInTheRepositoryAfter = 1;
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
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Optional <Account> expected = Optional.empty();

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        Optional <Account> accountCreated = service.createGroupAccount(createGroupAccountDTO);
        int realNumberOfAccountsInTheRepository = 0;

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
        );
    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member - Number of accounts has not increased")
    void testIfGroupAccountWasCreatedNotGroupMemberNumberOfAccounts() {

        //Arrange
        String creatorEmail = "roberto_a.0@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Optional <Account> expected = Optional.empty();

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act

        Optional <Account> accountCreated = service.createGroupAccount(createGroupAccountDTO);

        int realNumberOfAccountsInTheRepository = 0;

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
        );
    }


    @Test
    @DisplayName("Test If group Account is created - Person do not Exists")
    void testIfGroupAccountWasCreatedPersonNotExists() {

        //Arrange
        String creatorEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Group Do Not Exists")
    void testIfSeveralGroupAccountsWereCreatedGroupDoNotExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String oneDescription = "xpto";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, oneDescription,
                accountDenomination, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No group was found with the given description", invalid.getMessage());
        }
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Test If group Account is created - Several accounts added - Null & Empty Values
     */


    @Test
    @DisplayName("Test If group Account is created - Email null")
    void testIfGroupAccountWasCreatedPersonIDNull() {

        //Arrange

        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(null, groupDescription,
                accountDenomination, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email can´t be null!", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Email Empty")
    void testIfGroupAccountWasCreatedPersonEmailEmpty() {

        //Arrange
        String creatorEmail = "";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email it´s not valid", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Group ID null")
    void testIfGroupAccountWasCreated_groupIDNull() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, null,
                accountDenomination, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }

    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination null")
    void testIfGroupAccountWasCreated_AccountDenominationNull() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                null, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination Empty")
    void testIfGroupAccountWasCreated_AccountDenominationEmpty() {

        //Arrange

        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description null")
    void testIfGroupAccountWasCreated_AccountDescriptionNull() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, null);

        //Act
        try {

            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description Empty")
    void testIfGroupAccountWasCreated_AccountDescriptionEmpty() {

        //Arrange

        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online Shopping";
        String accountDescription = "";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);

        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Email null - Number of accounts has not increased ")
    void testIfGroupAccountWasCreated_PersonIDNullNumberOfAccounts() {

        //Arrange
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(null, groupDescription,
                accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);

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
    void testIfSeveralGroupAccountsWereCreated_SeveralAccountsCreated() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)));

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination1, accountDescription1);

        Account expected1 = new Account(new Denomination(accountDenomination1), new Description(accountDescription1),
                new GroupID(new Description(groupDescription)));

        CreateGroupAccountDTO createGroupAccountDTO2 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination2, accountDescription2);

        Account expected2 = new Account(new Denomination(accountDenomination2), new Description(accountDescription2),
                new GroupID(new Description(groupDescription)));


        //Act
        Account accountCreated = service.createGroupAccount(createGroupAccountDTO).get();
        Account accountCreated1 = service.createGroupAccount(createGroupAccountDTO1).get();
        Account accountCreated2 = service.createGroupAccount(createGroupAccountDTO2).get();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(expected1, accountCreated1),
                () -> assertEquals(expected2, accountCreated2)
        );
    }


    /**
     * Test If group Account is created - Several Accounts Added - Happy Case - Check Number of Accounts
     */


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created - Number of accounts increased")
    void testIfSeveralGroupAccountsWereCreated_CompareSize() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        Account expected = new Account(new Denomination(accountDenomination), new Description(accountDescription),
                new GroupID(new Description(groupDescription)));

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination1, accountDescription1);

        Account expected1 = new Account(new Denomination(accountDenomination1), new Description(accountDescription1),
                new GroupID(new Description(groupDescription)));

        CreateGroupAccountDTO createGroupAccountDTO2 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination2, accountDescription2);

        Account expected2 = new Account(new Denomination(accountDenomination2), new Description(accountDescription2),
                new GroupID(new Description(groupDescription)));

        int numberOfExpectedAccountsInTheRepository = 3;

        //Act
        //Act
        Account accountCreated = service.createGroupAccount(createGroupAccountDTO).get();
        Account accountCreated1 = service.createGroupAccount(createGroupAccountDTO1).get();
        Account accountCreated2 = service.createGroupAccount(createGroupAccountDTO2).get();

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        //Assert
        Assertions.assertAll(
                () -> assertEquals(expected, accountCreated),
                () -> assertEquals(expected1, accountCreated1),
                () -> assertEquals(expected2, accountCreated2),
                () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
        );
    }


    /**
     * Test If group Account is created -  Failing scenarios - Simple Tests
     */


    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin")
    void testIfGroupAccountWasCreatedNotAdmin() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        Optional<Account> accountCreated = service.createGroupAccount(createGroupAccountDTO);

        //Assert
        assertFalse(accountCreated.isPresent());
    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member")
    void testIfGroupAccountWasCreatedNotGroupMember() {

        //Arrange
        String creatorEmail = "roberto_a.0@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        Optional<Account> accountCreated = service.createGroupAccount(createGroupAccountDTO);

        //Assert
        assertFalse(accountCreated.isPresent());

    }

    @Test
    @DisplayName("Test If group Account is created - Several Accounts - One of the Accounts already exists")
    void testIfSeveralGroupAccountsWereCreatedOneAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination1, accountDescription1);

        service.createGroupAccount(createGroupAccountDTO);
        service.createGroupAccount(createGroupAccountDTO1);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO1);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - False - Account already exists")
    void testIfGroupAccountWasCreatedAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";


        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);


        service.createGroupAccount(createGroupAccountDTO);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
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
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();
        int expectedAccountsAfter = 1;

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);


        //Act
        service.createGroupAccount(createGroupAccountDTO);
        try {
            service.createGroupAccount(createGroupAccountDTO);
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
        String creatorEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);

        } catch (IllegalArgumentException invalid) {

            int realNumberOfAccountsInTheRepository = 0;

            //Assert
            Assertions.assertAll(
                    () -> assertEquals("No person found with that email.", invalid.getMessage()),
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
            );
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Several Accounts Added - Number of accounts has not increased")
    void testIfSeveralGroupAccountsWereCreated_NumberOfAccounts() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        int numberOfExpectedAccountsInTheRepository = 2;

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        CreateGroupAccountDTO createGroupAccountDTO1 = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination1, accountDescription1);


        //Act
        service.createGroupAccount(createGroupAccountDTO);
        service.createGroupAccount(createGroupAccountDTO1);

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        try {
            service.createGroupAccount(createGroupAccountDTO1);

        } catch (IllegalArgumentException invalid) {

            //Assert
            Assertions.assertAll(
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository),
                    () -> assertEquals("This Account already exists for that ID.", invalid.getMessage())
            );
        }
    }


}