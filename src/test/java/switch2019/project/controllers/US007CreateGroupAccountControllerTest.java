package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.*;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US007CreateGroupAccountService;

import static org.junit.jupiter.api.Assertions.*;

class US007CreateGroupAccountControllerTest {

    private static PersonRepository personRepo;
    private static GroupsRepository groupsRepo;
    private static AccountRepository accountRepo;
    private static US007CreateGroupAccountController controller;
    private static US007CreateGroupAccountService service;

    @BeforeEach
     void universeSetUp() {

        personRepo = new PersonRepository();
        groupsRepo = new GroupsRepository();
        accountRepo = new AccountRepository();

        service = new US007CreateGroupAccountService(personRepo, groupsRepo, accountRepo);
        controller = new US007CreateGroupAccountController(service);


        //Persons used to create groups (ADMINS)

        Person personJoaoCardoso = new Person ("João Cardoso", new DateAndTime(1993, 1, 13), new Address("Porto"),
                new Address("Rua do Amadeu", "Porto", "4000-189"), new Email("joao.cardoso_12@hotmail.com"));
        Person personMiluAlbertina = new Person ("Milu Albertina", new DateAndTime(1985, 1, 15), new Address("Guimarães"),
                new Address("Rua das uvas", "Faro", "4000-189"), new Email("milu@gmail.com"));
        Person personRobertoAlmeida = new Person ("Roberto Almeida ", new DateAndTime(2000, 3, 15), new Address("Lisboa"),
                new Address("Rua 2 ", "Lisboa", "4356-189"), new Email("roberto_a.0@gmail.com"));
        Person personFrederico = new Person("Frederico Caveira ", new DateAndTime(1999, 10, 20), new Address("Faro"),
                new Address("Rua da uva ", "Lisboa", "4543-136"), new Email("112345@isep.ipp.pt"));

        //Person to add to groups (MEMBERS)

        Person personJose = new Person("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("jose@gmai.com"));
        Person personRafael = new Person("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("rafael_2@hotmail.com"));
        Person personMaria = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("123313@isep.ipp.pt"));
        Person personMariana = new Person("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("112332@isep.ipp.pt"));


        //Add Admins and members to Repository

        personRepo.createPerson("João Cardoso", new DateAndTime(1993, 1, 13), new Address("Porto"),
                new Address("Rua do Amadeu", "Porto", "4000-189"), new Email("joao.cardoso_12@hotmail.com"));
        personRepo.createPerson("Milu Albertina", new DateAndTime(1985, 1, 15), new Address("Guimarães"),
                new Address("Rua das uvas", "Faro", "4000-189"), new Email("milu@gmail.com"));
        personRepo.createPerson("Roberto Almeida ", new DateAndTime(2000, 3, 15), new Address("Lisboa"),
                new Address("Rua 2 ", "Lisboa", "4356-189"), new Email("roberto_a.0@gmail.com"));
        personRepo.createPerson("Frederico Caveira ", new DateAndTime(1999, 10, 20), new Address("Faro"),
                new Address("Rua da uva ", "Lisboa", "4543-136"), new Email("112345@isep.ipp.pt"));

        //Add Groups to Repository

        groupsRepo.createGroup("Familia", personJoaoCardoso);
        groupsRepo.createGroup("Friends", personMiluAlbertina);
        groupsRepo.createGroup("MArket", personRobertoAlmeida);
        groupsRepo.createGroup("Isep", personFrederico);

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


    @Test
    @DisplayName("Test If group Account is created - Main Scenario - Happy Case")
    void testIfGroupAccountWasCreated_HappyCase() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        boolean accountCreated = controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);

        //Assert
        assertTrue(accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Happy Case - Several Accounts Created")
    void testIfSeveralGroupAccountsWereCreated_SeveralAccountsCreated() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));

        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        Denomination accountDenomination1 = new Denomination("Revolut");
        Description accountDescription1 = new Description("Revolut Account");

        Denomination accountDenomination2 = new Denomination("Netflix");
        Description accountDescription2 = new Description("Netflix Account");

        //Act
        boolean accountsCreated = controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription)
                && controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination1, accountDescription1)
                && controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination2, accountDescription2);

        //Assert
        assertTrue(accountsCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - False - Account already exists")
    void testIfGroupAccountWasCreated_AccountAlreadyExists() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);
        boolean accountCreated =  controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);

        //Assert
        assertFalse(accountCreated);
    }

    @Test
    @DisplayName("Test If group Account is created - Several Accounts - One Account already exists")
    void testIfSeveralGroupAccountsWereCreated_OneAccountAlreadyExists() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));

        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        Denomination accountDenomination1 = new Denomination("Revolut");
        Description accountDescription1 = new Description("Revolut Account");


        //Act
        boolean accountsCreated = controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription)
                && controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination1, accountDescription1)
                && controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination1, accountDescription1);

        //Assert
        assertFalse(accountsCreated);
    }

    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin")
    void testIfGroupAccountWasCreated_NotAdmin() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFriendsID  = new GroupID(new Description("Friends"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        boolean accountCreated =  controller.createGroupAccount(creatorID, groupFriendsID, accountDenomination, accountDescription);

        //Assert
        assertFalse(accountCreated);
    }

    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member")
    void testIfGroupAccountWasCreated_NotGroupMember() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("roberto_a.0@gmail.com"));
        GroupID groupIsepID  = new GroupID(new Description("Isep"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        boolean accountCreated =  controller.createGroupAccount(creatorID, groupIsepID, accountDenomination, accountDescription);

        //Assert
        assertFalse(accountCreated);
    }


    @Test
    @DisplayName("Test If group Account is created - Several Accounts - Group Do Not Exists")
    void testIfSeveralGroupAccountsWereCreated_groupDoNotExists() {

        //Arrange
        PersonID creatorID = new PersonID (new Email("joao.cardoso_12@hotmail.com"));
        GroupID oneGroupID  = new GroupID(new Description("xpto"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");



        try {
            controller.createGroupAccount(creatorID, oneGroupID, accountDenomination, accountDescription);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No group found with that ID.", invalid.getMessage());
        }
    }


    @Test
    @DisplayName("Test If group Account is created - Person do not Exists")
    void testIfGroupAccountWasCreated_PersonNotExists() {

        //Arrange
        PersonID creatorID = new PersonID(new Email("miguel@gmail.com"));
        GroupID groupIsepID = new GroupID(new Description("Isep"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        try {
            controller.createGroupAccount(creatorID, groupIsepID, accountDenomination, accountDescription);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The Person ID doesn't exist!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Person ID null")
    void testIfGroupAccountWasCreated_PersonIDNull() {

        //Arrange
        PersonID creatorID = null;
        GroupID groupIsepID = new GroupID(new Description("Isep"));
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        try {
            controller.createGroupAccount(creatorID, groupIsepID, accountDenomination, accountDescription);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The Person ID doesn't exist!", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Group ID null")
    void testIfGroupAccountWasCreated_groupIDNull() {

        //Arrange
        PersonID creatorID = new PersonID(new Email("joao.cardoso_12@hotmail.com"));
        GroupID onegroupID  = null;
        Denomination accountDenomination = new Denomination("Online");
        Description accountDescription = new Description("Online Shopping");

        //Act
        try {
            controller.createGroupAccount(creatorID, onegroupID, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("No group found with that ID.", invalid.getMessage());
        }

    }


    @Test
    @DisplayName("Test If group Account is created - Account denomination null")
    void testIfGroupAccountWasCreated_AccountDenominationNull() {

        //Arrange
        PersonID creatorID = new PersonID(new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));
        Denomination accountDenomination = null;
        Description accountDescription = new Description("Online Shopping");

        //Act
        try {
            controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("Neither the Denomination nor OwnerID can be null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination Empty")
    void testIfGroupAccountWasCreated_AccountDenominationEmpty() {

        //Arrange & Act
        try {
            PersonID creatorID = new PersonID(new Email("joao.cardoso_12@hotmail.com"));
            GroupID groupFamilyID  = new GroupID(new Description("Familia"));
            Denomination accountDenomination = new Denomination("");
            Description accountDescription = new Description("Online Shopping");

            controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);
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
        PersonID creatorID = new PersonID(new Email("joao.cardoso_12@hotmail.com"));
        GroupID groupFamilyID  = new GroupID(new Description("Familia"));
        Denomination accountDenomination = new Denomination("Online Shopping");
        Description accountDescription = null;

        //Act
        try {

            controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("Account Description can't be null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description Empty")
    void testIfGroupAccountWasCreated_AccountDescriptionEmpty() {

        //Arrange & Act
        try {
            PersonID creatorID = new PersonID(new Email("joao.cardoso_12@hotmail.com"));
            GroupID groupFamilyID  = new GroupID(new Description("Familia"));
            Denomination accountDenomination = new Denomination("Online Shopping");
            Description accountDescription = new Description("");

            controller.createGroupAccount(creatorID, groupFamilyID, accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

}