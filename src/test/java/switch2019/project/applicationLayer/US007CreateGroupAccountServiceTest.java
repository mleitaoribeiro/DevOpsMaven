package switch2019.project.applicationLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

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
        Person personFrederico =  personRepo.createPerson("Frederico Caveira ", new DateAndTime(1999, 10, 20), new Address("Faro"),
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

        Group groupFamilia = groupsRepo.findGroupByDescription( new Description ("Familia"));
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
     *
     * Test If group Account is created - Happy Cases
     *
     */


    @Test
    @DisplayName("Test If group Account is created - Main Scenario - Happy Case")
    void testIfGroupAccountWasCreated_HappyCase() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Act
        boolean accountCreated = service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        //Assert
        assertTrue(accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Number of accounts increased")
    void testIfGroupAccountWasCreated_CompareSize() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();


        //Act
        boolean accountCreated = service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        int numberOfExpectedAccountsInTheRepositoryAfter = 1;
        int realNumberOfAccountsInTheRepositoryAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        assertTrue(accountCreated
                && expectedAccountsBefore == realAccountsBefore
                && numberOfExpectedAccountsInTheRepositoryAfter == realNumberOfAccountsInTheRepositoryAfter);
    }


    /**
     *
     * Test If group Account is created - Failing scenarios
     *
     */


    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin - Number of accounts has not increased")
    void testIfGroupAccountWasCreated_NotAdminNumberOfAccounts() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  ="Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";


        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        boolean accountCreated =  service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        int realNumberOfAccountsInTheRepository = 0;

        //Assert
        assertFalse(accountCreated);
        assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member - Number of accounts has not increased")
    void testIfGroupAccountWasCreated_NotGroupMemberNumberOfAccounts() {

        //Arrange
        String creatorEmail = "roberto_a.0@gmail.com";
        String groupDescription  = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act

        boolean accountCreated =  service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        int realNumberOfAccountsInTheRepository = 0;

        //Assert
        assertFalse(accountCreated);
        assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
    }


    @Test
    @DisplayName("Test If group Account is created - Person do not Exists")
    void testIfGroupAccountWasCreated_PersonNotExists() {

        //Arrange
        String creatorEmail = "miguel@gmail.com";
        String  groupDescription = "Isep";
        String  accountDenomination = "Online";
        String  accountDescription = "Online Shopping";

        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Group Do Not Exists")
    void testIfSeveralGroupAccountsWereCreated_groupDoNotExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String oneDescription  = "xpto";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Act
        try {
            service.createGroupAccount(creatorEmail, oneDescription, accountDenomination, accountDescription);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No group was found with the given description", invalid.getMessage());
        }
    }






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /**
     *
     * Test If group Account is created - Several accounts added - Null & Empty Values
     *
     */



    @Test
    @DisplayName("Test If group Account is created - Email null")
    void testIfGroupAccountWasCreated_PersonIDNull() {

        //Arrange
        String creatorEmail = null;
        String groupDescription ="Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email can´t be null!", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Email Empty")
    void testIfGroupAccountWasCreated_PersonEmailEmpty() {

        //Arrange
        String creatorEmail = "";
        String groupDescription ="Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email it´s not valid", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Group ID null")
    void testIfGroupAccountWasCreated_groupIDNull() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = null;
        String accountDenomination = "Online";
        String accountDescription ="Online Shopping";

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }

    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination null")
    void testIfGroupAccountWasCreated_AccountDenominationNull() {

        //Arrange
        String  creatorEmail = "joao.cardoso_12@hotmail.com";
        String  groupDescription = "Familia";
        String  accountDenomination = null;
        String  accountDescription = "Online Shopping";

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination Empty")
    void testIfGroupAccountWasCreated_AccountDenominationEmpty() {

        //Arrange & Act
        try {
            String  creatorEmail = "joao.cardoso_12@hotmail.com";
            String  groupDescription  = "Familia";
            String  accountDenomination = "";
            String  accountDescription = "Online Shopping";

            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can´t be null or empty!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description null")
    void testIfGroupAccountWasCreated_AccountDescriptionNull() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";
        String accountDenomination = "Online Shopping";
        String accountDescription = null;

        //Act
        try {

            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description Empty")
    void testIfGroupAccountWasCreated_AccountDescriptionEmpty() {

        //Arrange & Act
        try {
            String creatorEmail = "joao.cardoso_12@hotmail.com";
            String groupDescription  = "Familia";
            String accountDenomination = "Online Shopping";
            String accountDescription = "";

            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Email null - Number of accounts has not increased ")
    void testIfGroupAccountWasCreated_PersonIDNullNumberOfAccounts() {

        //Arrange
        String creatorEmail = null;
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();
            //Assert
            assertEquals("The email can´t be null!", invalid.getMessage());
            assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
        }

    }



    /**
     *
     * Test If group Account is created - Several Accounts Added - Happy Cases - Simple Tests
     *
     */



    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created")
    void testIfSeveralGroupAccountsWereCreated_SeveralAccountsCreated() {

        //Arrange
        String creatorEmail ="joao.cardoso_12@hotmail.com";
        String groupDescription  ="Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        //Act
        boolean accountsCreated = service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription)
                && service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1)
                && service.createGroupAccount(creatorEmail, groupDescription, accountDenomination2, accountDescription2);

        //Assert
        assertTrue(accountsCreated);
    }



    /**
     *
     * Test If group Account is created - Several Accounts Added - Happy Case - Check Number of Accounts
     *
     */



    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created - Number of accounts increased")
    void testIfSeveralGroupAccountsWereCreated_CompareSize() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        String accountDenomination2 = "Netflix";
        String accountDescription2 = "Netflix Account";

        int numberOfExpectedAccountsInTheRepository = 3;

        //Act
        boolean accountsCreated = service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription)
                && service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1)
                && service.createGroupAccount(creatorEmail, groupDescription, accountDenomination2, accountDescription2);

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Assert
        assertTrue(accountsCreated && numberOfExpectedAccountsInTheRepository == realNumberOfAccountsInTheRepository);
    }



    /**
     *
     * Test If group Account is created -  Failing scenarios - Simple Tests
     *
     */



    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin")
    void testIfGroupAccountWasCreated_NotAdmin() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Act
        boolean accountCreated =  service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        //Assert
        assertFalse(accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member")
    void testIfGroupAccountWasCreated_NotGroupMember() {

        //Arrange
        String  creatorEmail = "roberto_a.0@gmail.com";
        String  groupDescription  = "Isep";
        String  accountDenomination ="Online";
        String  accountDescription = "Online Shopping";

        //Act
        boolean accountCreated =  service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        //Assert
        assertFalse(accountCreated);

    }

    @Test
    @DisplayName("Test If group Account is created - Several Accounts - One of the Accounts already exists")
    void testIfSeveralGroupAccountsWereCreated_OneAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1);

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - False - Account already exists")
    void testIfGroupAccountWasCreated_AccountAlreadyExists() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }



    /**
     *
     * Test If group Account is created -  Failing scenarios  - Check Number of Accounts
     *
     */



    @Test
    @DisplayName("Test If group Account is created - False - Account Already Exists")
    void testIfGroupAccountsWasCreated_FalseCompareSize() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int expectedAccountsBefore = 0;
        int realAccountsBefore = accountRepo.numberOfAccountsInTheAccountsRepository();

        //Act
        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            int expectedAccountsAfter = 1;
            int realAccountsAfter = accountRepo.numberOfAccountsInTheAccountsRepository();

            assertEquals("This Account already exists for that ID.", invalid.getMessage());
            assertEquals(expectedAccountsBefore, realAccountsBefore);
            assertEquals(expectedAccountsAfter, realAccountsAfter);
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Person do not Exists - Number of accounts has not increased")
    void testIfGroupAccountWasCreated_PersonNotExistsNumberOfAccounts() {

        //Arrange
        String creatorEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            boolean accountCreated = service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            int realNumberOfAccountsInTheRepository = 0;
            //Assert
            assertEquals("No person found with that email.", invalid.getMessage());
            assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Several Accounts Added - Number of accounts has not increased")
    void testIfSeveralGroupAccountsWereCreated_NumberOfAccounts() {

        //Arrange
        String creatorEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription  = "Familia";

        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        String accountDenomination1 = "Revolut";
        String accountDescription1 = "Revolut Account";

        int numberOfExpectedAccountsInTheRepository = 2;


        //Act
        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination, accountDescription);
        service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1);

        int realNumberOfAccountsInTheRepository = accountRepo.numberOfAccountsInTheAccountsRepository();

        try {
            service.createGroupAccount(creatorEmail, groupDescription, accountDenomination1, accountDescription1);
        }
        catch(IllegalArgumentException invalid) {
            //Assert
            assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository);
            assertEquals("This Account already exists for that ID.", invalid.getMessage());
        }
    }



}