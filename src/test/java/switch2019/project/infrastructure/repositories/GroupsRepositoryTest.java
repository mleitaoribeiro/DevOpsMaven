package switch2019.project.infrastructure.repositories;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupsRepositoryTest {

    /**
     * As user , I Want to Creat a group and be admin (US002.1)
     */

    @Test
    @DisplayName("Test if Group was Created")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group expected = new Group(new Description("Test Person"), person1);

        //Act
        Group groupCreated = groupsRepository.createGroup(new Description("Test Person"), person1);

        //Assert
        assertEquals(expected,groupCreated);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("Francis", new DateAndTime(2001, 4, 12), new Address("Dublin"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));

        //Act
        try {
             groupsRepository.createGroup(null, person1);
        }
        catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group was not created when it is already contained in the repository")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("Amy", new DateAndTime(1990, 12, 04), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));

        //Act
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1);
        try {
            groupsRepository.createGroup(new Description("Grupo de Teste"), person1);
        } catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group is created even it has the same name but different members")
    public void createGroupWithSameDescriptionAndDifferentMembers() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("Amy", new DateAndTime(1990, 12, 4), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marshall", new DateAndTime(1990, 12, 4), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("123@isep.pt"));
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1);
        //Act
        try {
            groupsRepository.createGroup(new Description("Grupo de Teste"), person2);
        }
        //Assert
        catch (IllegalArgumentException ex) {
            assertEquals("This Group Description already exists.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group is created with different description but same person")
    public void createGroupWithDifferentDescriptionAndDifferentMembers() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("Amy", new DateAndTime(1999, 5, 13), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));

        Group expected = new Group(new Description("Grupo Diferente"), person1);

        //Act
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1);

        Group groupCreated = groupsRepository.createGroup(new Description("Grupo Diferente"), person1);

        //Assert
        assertEquals(expected,groupCreated);
    }

    @Test
    @DisplayName("Test if group was not created when its null")
    public void testIfWasCreatedWhenNull() {
        //Arrange
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = null;

        //Act
        try {
            groupsRepository.createGroup(null, person1);
        } catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null.", ex.getMessage());
        }
    }

    /**
     * Check the number of Groups inside the groupList
     */
    @Test
    @DisplayName("Test if the number of groups on the list was increased")
    public void howManyGroupsTest() {
        //Arrange
        Group group1 = new Group(new Description("Amigos"));
        Group group2 = new Group(new Description("Pokémons"));
        GroupsRepository groupList = new GroupsRepository();

        //Act
        groupList.addGroupToRepository(group1);
        groupList.addGroupToRepository(group2);
        int result = groupList.repositorySize();

        //Assert
        assertEquals(2, result);
    }

    /**
     * Test if a group was added to the groupList
     */
    @Test
    @DisplayName("Test if the group added is not in the list")
    public void testGroupIsInList() {
        //Arrange
        Group group1 = new Group(new Description("Switchieees"));
        Group group2 = new Group(new Description("Clube da Costura"));
        GroupsRepository groupList = new GroupsRepository();

        //Act
        boolean groupAdded = groupList.addGroupToRepository(group1);

        //Assert
        assertTrue(groupAdded);
    }

    @Test
    @DisplayName("Test if a null group is not added to the list")
    public void testAddGroupIsNull() {
        //Arrange
        GroupsRepository groupList = new GroupsRepository();

        //Act
        boolean groupAdded = groupList.addGroupToRepository(null);

        //Assert
        assertFalse(groupAdded);
    }

    @Test
    @DisplayName("Test if more than one group was added is in the list")
    public void testGroupIsInList_MoreThanOne() {
        //Arrange
        Group group1 = new Group(new Description("Switchieees"));
        Group group2 = new Group(new Description("Clube da Costura"));
        Group group3 = new Group(new Description("Clube dos Livros"));

        GroupsRepository groupList = new GroupsRepository();

        //Act
        boolean group1added = groupList.addGroupToRepository(group1);
        boolean group2added = groupList.addGroupToRepository(group2);
        boolean group3added = groupList.addGroupToRepository(group3);

        //Assert
        assertTrue(group1added && group2added && group3added);
    }

    /**
     * US004 - Check which groups are family
     * If the list of groups consists exclusively in families.
     */
    @Test
    @DisplayName("Validate if only groups of families are being returned")
    public void ifGroupListIsOnlyFamilies_SuccessCase() {

        //Arrange
        // Global Groups List
        GroupsRepository globalGroupsRepository = new GroupsRepository();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", new DateAndTime(1960, 10, 10), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));
        Person carlosDAD = new Person("Carlos", new DateAndTime(1950, 12, 12), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("123@isep.pt"));
        Person oscar = new Person("Oscar", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("12@isep.pt"));
        Person marta = new Person("Marta", new DateAndTime(1995, 11, 5), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("1@isep.pt"));
        Person joao = new Person("Joao", new DateAndTime(2000, 1, 12), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("12345@isep.pt"));

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, carlosDAD));
        Group family = new Group(new Description("Family"));
        family.addMember(manuelaMOM);
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsRepository.addGroupToRepository(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail@isep.pt"));
        Person marge = new Person("Marge",new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail2@isep.pt"));
        Person bart = new Person("Bart", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail3@isep.pt"));
        Person lisa = new Person("Lisa", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail4@isep.pt"));
        Person maggie = new Person("Maggie", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail5@isep.pt"));

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(marge, bart, lisa, maggie));
        Group simpsons = new Group(new Description("Simpsons"));
        simpsons.addMember(homer);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsRepository.addGroupToRepository(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", new DateAndTime(1990, 12, 4), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("email@isep.pt"));
        Person diana = new Person("Diana", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD, new Email("email2@isep.pt"));
        Person elsa = new Person("Elsa",new DateAndTime(1990, 12, 4), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD, new Email("email3@isep.pt"));
        Person ines = new Person("Ines", new DateAndTime(1990, 12, 4), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD, new Email("email4@isep.pt"));

        // Group
        HashSet<Person> noMomMembersToAdd = new HashSet<>(Arrays.asList(diana, elsa, ines));
        Group familyWithNoMom = new Group(new Description("Family with no Mom"));
        familyWithNoMom.addMember(joaoDAD);
        familyWithNoMom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsRepository.addGroupToRepository(familyWithNoMom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", new DateAndTime(1990, 12, 04), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail@isep.pt"));
        Person martaC = new Person("Marta Cardoso", new DateAndTime(1990, 12, 04), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail2@isep.pt"));
        Person martaP = new Person("Marta Pinheiro", new DateAndTime(1990, 12, 04), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail3@isep.pt"));

        // Group
        HashSet<Person> martasGroupMembersToAdd = new HashSet<>(Arrays.asList(martaC, martaP));
        Group martaGroup = new Group(new Description("Marta's group"));
        martaGroup.addMember(martaR);
        martaGroup.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsRepository.addGroupToRepository(martaGroup);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Porto", "4520-233"), new Email("new@isep.pt"));
        Person carolyn = new Person("Princess Carolyn", new DateAndTime(1990, 12, 4),
                new Address("Lisboa"), new Address("Rua B", "Porto", "4520-233"), new Email("new2@isep.pt"));
        Person todd = new Person("Todd Chavez", new DateAndTime(1990, 12, 4),
                new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"), new Email("new3@isep.pt"));
        Person diane = new Person("Diane Nguyen", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Porto", "4520-233"), new Email("new4@isep.pt"));

        // Group
        HashSet<Person> bojackGangMembersToAdd = new HashSet<>(Arrays.asList(carolyn, todd, diane));
        Group bojackGang = new Group(new Description("Bojack's Gang"));
        bojackGang.addMember(bojack);
        bojackGang.addMultipleMembers(bojackGangMembersToAdd);
        globalGroupsRepository.addGroupToRepository(bojackGang);

        //Act
        Set<Group> realResult = globalGroupsRepository.returnOnlyFamilies();
        HashSet<Group> expectedResult = new HashSet<>(Arrays.asList(family, simpsons));

        //Assert
        assertEquals(expectedResult, realResult);
    }

    /**
     * checkIfAPersonIsAdminInAGivenGroup method Tested
     */

    @Test
    @DisplayName("Happy Case - Person is Admin")
    void isPersonAdminOfAGivenGroupTrue() {

        //Arrange:
        GroupsRepository testGroupList = new GroupsRepository();

            //Arrange Groups:
        Group testGroup = new Group(new Description("test group"));
        testGroupList.addGroupToRepository(testGroup);

            //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", new DateAndTime(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        testGroup.addMember(testGroupAdmin);

        //Act:
        boolean result = testGroupList.checkIfAPersonIsAdminInAGivenGroup(new GroupID(new Description("test group")),testGroupAdmin);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Person is not an Admin")
    void isPersonAdminOfAGivenGroupFalse() {

        //Arrange:
        GroupsRepository testGroupList = new GroupsRepository();

        //Arrange Groups:
        Group testGroup = new Group(new Description("test group"));
        testGroupList.addGroupToRepository(testGroup);

        //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", new DateAndTime(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Act:
        boolean result = testGroupList.checkIfAPersonIsAdminInAGivenGroup(new GroupID(new Description("test group")),testGroupAdmin);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Group Description is not found")
    void isPersonAdminOfAGivenGroupException() {

        //Arrange:
        GroupsRepository testGroupList = new GroupsRepository();

        //Arrange Groups:
        Group testGroup = new Group(new Description("test group"));
        testGroupList.addGroupToRepository(testGroup);

        //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", new DateAndTime(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        testGroup.addMember(testGroupAdmin);

        //Act:
        try {
            testGroupList.checkIfAPersonIsAdminInAGivenGroup(new GroupID(new Description("blabla")), testGroupAdmin);
        } catch (IllegalArgumentException groupNotFound) {
            assertEquals("There're no groups found with that description.", groupNotFound.getMessage());
        }
    }

    @Test
    void createTransactionOnSpecificGroup() {
        // Arrange ____________________________________________________________________________________________________

        // Person:
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        //Categories:
        Category categoryFriends = new Category("friends");

        //Monetary Value:
        MonetaryValue monetaryValue100 = new MonetaryValue(100, Currency.getInstance("EUR"));

        // Groups:
        GroupsRepository groupsRepository = new GroupsRepository();
        Group spiceGirls = new Group(new Description("spice girls"));
        Group work = new Group(new Description("work"));
        groupsRepository.createGroup(new Description("spice girls"), person);
        groupsRepository.createGroup(new Description("work"), person);

        // Group Accounts:
        Account accountCombustivel = new Account(new Denomination("combustivel"),
                new Description("gastos de combustivél"), new PersonID(new Email("personEmail@email.pt")));

        Account accountGato = new Account(new Denomination("comida de gato"),
                new Description("comida para a gatinha"), new PersonID(new Email("personEmail@email.pt")));


        //Act
        boolean result = groupsRepository.createTransactionOnSpecificGroup(person, "spice girls",
                monetaryValue100, "payment", LocalDateTime.of(2019, 12, 25, 12, 15),
                categoryFriends, accountCombustivel, accountGato, new Type(false));

        // Arrange ___________________________________________________________________________________________________
        assertTrue(result);
    }

    @Test
    @DisplayName("Create Transaction group description isn't contained in GroupList and person is a member")
    void testIfAGroupThatIsNotInTheListCanCreateTransaction() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("JUST4FUN"), person);
        try {
            //Act
            groupsRepository.createTransactionOnSpecificGroup(person, "Tarzan", amount, description,
                    LocalDateTime.of(1995,12,4,00,00), category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("There're no groups found with that description.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Trying to create transaction that member is not contained. ")
    void testIfATransactionCanBeCreatedIfMemberIsNotMember() {
        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        Person person1 = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("JUST4FUN"), person);
        try {
            //Act
            groupsRepository.createTransactionOnSpecificGroup(person1, "Tarzan", amount, description,
                    LocalDateTime.of(1995,12,4,00,00), category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("There're no groups found with that description.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Trying to create transaction that member is not contained. ")
    void testIfATransactionCanBeCreatedIfPersonIsNotAMember() {
        //Arrange:
        GroupsRepository groupsRepository = new GroupsRepository();
        Person person1 = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person notMember = new Person("Francisco", new DateAndTime(1993, 11, 13),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        groupsRepository.createGroup(new Description("TestGroup"),person1);

            //Transactions arrangement:
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("Test");
        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        try {
            //Act:
            groupsRepository.createTransactionOnSpecificGroup(notMember, "TestGroup", amount, "test transaction",
                    LocalDateTime.of(1995, 12, 4, 00, 00), category1, from, to, new Type(false));

        }
        //Assert:
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }

    /**
     * Test method createScheduleOnSpecificGroup
     * @throws InterruptedException
     */

    @Test
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("tarzan"), person);

        //Act
        boolean result = groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                new Periodicity("daily"),
                amount, description, null, category, from, to, new Type(false));

        Thread.sleep(700); // 500 x 2

        //Assert
        assertTrue(result && groupsRepository.checkAGroupsLedgerSize("tarzan") == 2);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("tarzan"), person);

        //Act
        boolean result = groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                new Periodicity("working days"),
                amount, description, null, category, from, to, new Type(false));

        Thread.sleep(1500); // 1000 x 2

        //Assert
        assertTrue(result && groupsRepository.checkAGroupsLedgerSize("tarzan") == 2);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("tarzan"), person);

        //Act
        boolean result = groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                new Periodicity("weekly"),
                amount, description, null, category, from, to, new Type(false));

        Thread.sleep(2000); // 1500 x 2

        //Assert
        assertTrue(result && groupsRepository.checkAGroupsLedgerSize("tarzan") == 2);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("tarzan"), person);

        //Act
        boolean result = groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                new Periodicity("monthly"),
                amount, description, null, category, from, to, new Type(false));

        Thread.sleep(2500); // 2000 x 2

        //Assert
        assertTrue(result && groupsRepository.checkAGroupsLedgerSize("tarzan") == 2);
    }

    @Test
    void scheduleNewTransactionNoMatch() {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("tarzan"), person);

        try {
            //Act
            groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                    new Periodicity("monthly"),
                    amount, description, null, category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }

    @Test
    @DisplayName("In case there're no groups found with that description.")
    void scheduleNewTransactionWithGroupThatDoesNotExistsInGroupList()  {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("JUST4FUN"), person);

        try {
            //Act
            groupsRepository.createScheduleOnSpecificGroup(person, "tarzan",
                    new Periodicity("monthly"),
                    amount, description, null, category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("There're no groups found with that description.", result.getMessage());
        }
    }

    @Test
    @DisplayName("In case the person is not a member of that group")
    void scheduleNewTransactionWherePersonIsNotAMember()  {

        //Arrange
        Person personMember = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person personNotMember = new Person("Julia", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("JUST4FUN"), personMember);

        try {
            //Act
            groupsRepository.createScheduleOnSpecificGroup(personNotMember, "JUST4FUN",
                    new Periodicity("monthly"),
                    amount, description, null, category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }


    /**
     * Test method to create new transaction on specific group
     */

    @Test
    @DisplayName("In case the person is not a member of that group")
    void createNewTransactionWherePersonIsNotAMember()  {

        //Arrange
        Person personMember = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        Person personNotMember = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");

        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        GroupsRepository groupsRepository = new GroupsRepository();
        groupsRepository.createGroup(new Description("JUST4FUN"), personMember);

        try {
            //Act
            groupsRepository.createTransactionOnSpecificGroup(personNotMember, "JUST4FUN",
                    amount, description, null, category, from, to, new Type(false));

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }

    /**
     * returnTransactionsFromAllGroupsAPersonIsIn tests
     */

    @Test
    @DisplayName("return transactions from two groups")
    void areTransactionsFromPersonGroupsReturned() {

        //ARRANGE:
        Person groupMember = new Person("Tiago", new DateAndTime(1994,06,17),
                new Address("Porto"),new Address("Rua xpto","Porto","4450-010"), new Email("1234@isep.pt"));

        GroupsRepository testGroupsRepository = new GroupsRepository();

            //Arrange two groups inside the GroupsList:
        Group group1 = new Group(new Description("test group 1"));
        Group group2 = new Group(new Description("test group 2"));
        testGroupsRepository.addGroupToRepository(group1);
        testGroupsRepository.addGroupToRepository(group2);

            //groupMember is member of both groups:
        group1.addMember(groupMember);
        group2.addMember(groupMember);

            //Arrange Transactions:
            //Monetary Value:
        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

            //Categories:
        Category category1 = new Category("grocery");
        Category category2 = new Category("restaurants");

            //Accounts:
        Account account1 = new Account(new Denomination("Savings"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("Pingo Doce"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));
        Account account3 = new Account(new Denomination("Savings2"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account4 = new Account(new Denomination("Pingo Doce2"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));

            //Transactions arranged:
            //Group1 transactions:
        Transaction transaction1 = new Transaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
            //Group2 transactions:
        Transaction transaction3 = new Transaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        Transaction transaction4 = new Transaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

            //Transactions arranged within the ledgers of the groups:
        group1.createGroupTransaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        group1.createGroupTransaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
        group2.createGroupTransaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        group2.createGroupTransaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

        //ACT:
        // expected:
        List<Transaction> expected = new ArrayList<Transaction>(Arrays.asList(transaction3,transaction4,transaction1,transaction2));
        //actual
        List<Transaction> actual = testGroupsRepository.returnTransactionsFromAllGroupsAPersonIsIn(groupMember,
                LocalDateTime.of(2000,1,1,0,0),
                LocalDateTime.of(2020,1,1,0,0));

        //ASSERT:
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("return transactions from two groups - null daes")
    void areTransactionsFromPersonGroupsReturnedNullDates() {

        //ARRANGE:
        Person groupMember = new Person("Tiago", new DateAndTime(1994,06,17),
                new Address("Porto"),new Address("Rua xpto","Porto","4450-010"), new Email("1234@isep.pt"));

        GroupsRepository testGroupsRepository = new GroupsRepository();

        //Arrange two groups inside the GroupsList:
        Group group1 = new Group(new Description("test group 1"));
        Group group2 = new Group(new Description("test group 2"));
        testGroupsRepository.addGroupToRepository(group1);
        testGroupsRepository.addGroupToRepository(group2);

        //groupMember is member of both groups:
        group1.addMember(groupMember);
        group2.addMember(groupMember);

        //Arrange Transactions:
        //Monetary Value:
        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        //Categories:
        Category category1 = new Category("grocery");
        Category category2 = new Category("restaurants");

        //Accounts:
        Account account1 = new Account(new Denomination("Savings"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("Pingo Doce"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));
        Account account3 = new Account(new Denomination("Savings2"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account4 = new Account(new Denomination("Pingo Doce2"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));

        //Transactions arranged:
        //Group1 transactions:
        Transaction transaction1 = new Transaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
        //Group2 transactions:
        Transaction transaction3 = new Transaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        Transaction transaction4 = new Transaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

        //Transactions arranged within the ledgers of the groups:
        group1.createGroupTransaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        group1.createGroupTransaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
        group2.createGroupTransaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        group2.createGroupTransaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

        //ACT:
        //expected:
        List<Transaction> expected = new ArrayList<Transaction>(Arrays.asList(transaction1,transaction2,transaction3,transaction4));
        //actual
        try {
            testGroupsRepository.returnTransactionsFromAllGroupsAPersonIsIn(groupMember,null,null);
        }

        //ASSERT:
        catch (IllegalArgumentException datesNull) {
            assertEquals("The dates can´t be null", datesNull.getMessage());
        }
    }
    @Test
    @DisplayName("return transactions from two groups - Not Member")
    void areTransactionsFromNonMemberPersonGroupsReturned() {

        //ARRANGE:
        Person groupMember = new Person("João", new DateAndTime(1994,06,17),
                new Address("Porto"),new Address("Rua xpto","Porto","4450-010"), new Email("1234@isep.pt"));
        Person notGroupMember = new Person("Joana", new DateAndTime(1994,06,17),
                new Address("Porto"),new Address("Rua xpto","Porto","4450-010"), new Email("1234@isep.pt"));

        GroupsRepository testGroupsRepository = new GroupsRepository();

        //Arrange two groups inside the GroupsList:
        Group group1 = new Group(new Description("test group 1"));
        Group group2 = new Group(new Description("test group 2"));
        testGroupsRepository.addGroupToRepository(group1);
        testGroupsRepository.addGroupToRepository(group2);

        //groupMember is not added

        //Arrange Transactions:
        //Monetary Value:
        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        //Categories:
        Category category1 = new Category("grocery");
        Category category2 = new Category("restaurants");

        //Accounts:
        Account account1 = new Account(new Denomination("Savings"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account2 = new Account(new Denomination("Pingo Doce"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));
        Account account3 = new Account(new Denomination("Savings2"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account account4 = new Account(new Denomination("Pingo Doce2"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));

        //Transactions arranged:
        //Group1 transactions:
        Transaction transaction1 = new Transaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        Transaction transaction2 = new Transaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
        //Group2 transactions:
        Transaction transaction3 = new Transaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        Transaction transaction4 = new Transaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

        //Transactions arranged within the ledgers of the groups:
        group1.createGroupTransaction(monetaryValue1,"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , category1,account1,account2,new Type(true));
        group1.createGroupTransaction(monetaryValue2,"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , category2, account1,account2,new Type(true));
        group2.createGroupTransaction(monetaryValue3,"grocery",LocalDateTime.of(2019, 1, 2, 12, 15)
                ,category1, account3, account4, new Type(true));
        group2.createGroupTransaction(monetaryValue4,"restaurant with friends",LocalDateTime.of(2018, 5, 3, 12, 15)
                ,category2,account3,account4,new Type(true));

        //ACT:
        // expected:
        List<Transaction> expected = new ArrayList();
        List<Transaction> actual = testGroupsRepository.returnTransactionsFromAllGroupsAPersonIsIn(notGroupMember,
                LocalDateTime.of(2000,1,1,0,0),
                LocalDateTime.of(2020,1,1,0,0));

        //ASSERT:
        assertEquals(expected,actual);
    }

    /**
     * checkAGroupsLedgerSize() method tested
     */
    @Test
    @DisplayName("Ledger size checked - happy case - ignoring case")
    void isLedgerSizeChecked(){
        //Arrange:
        GroupsRepository groupsRepository = new GroupsRepository();
        Group group2 = new Group(new Description("test group 2"));
        Group group1 = new Group(new Description("Test Group Main"));
        groupsRepository.addGroupToRepository(group2);
        groupsRepository.addGroupToRepository(group1);

            //Arranging accounts:
        Account savingsAccount = new Account(new Denomination("Savings"),
                new Description("Savings destined to food"), new PersonID(new Email("personEmail@email.pt")));
        Account pingDoceAccount = new Account(new Denomination("Pingo Doce"),
                new Description("groceries on Pingo Doce"), new PersonID(new Email("personEmail@email.pt")));
        Category shoppingForFood = new Category("shopping for food");


            //Arranging transactions inside group1's Ledger:
        group1.createGroupTransaction(new MonetaryValue(200,Currency.getInstance("EUR")),"grocery",LocalDateTime.of(2018, 1, 2, 12, 15)
                , shoppingForFood,savingsAccount,pingDoceAccount,new Type(true));
        group1.createGroupTransaction(new MonetaryValue(220,Currency.getInstance("EUR")),"restaurant with family",LocalDateTime.of(2010, 1, 2, 17, 30)
                , shoppingForFood, savingsAccount,pingDoceAccount,new Type(true));

        //Act:
        int result = groupsRepository.checkAGroupsLedgerSize("test group main");

        //Assert:
        assertEquals(2,result);
    }

    @Test
    @DisplayName("Ledger size checked - exception case - no groups in repository")
    void isLedgerSizeCheckedNoGroups(){
        //Arrange:
        GroupsRepository groupsRepository = new GroupsRepository();

        //Act:
        try {
            groupsRepository.checkAGroupsLedgerSize("test group main");
        }
        //Assert:
        catch (IllegalArgumentException e) {
            assertEquals("There're no groups found with that description.", e.getMessage());
        }
    }

    /**
     * Test method used to find a Group by its Description
     */

    @Test
    @DisplayName("getting group by its description")
    void getGroupByDescriptionTest(){
        //Arrange:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();

        personRepository.createPerson("Homer", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto", "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        groupsRepository.createGroup(new Description("BLA BLA"),personRepository.findPersonByEmail(new Email ("1234@isep.pt")));
        Group Blabla = new Group(new Description("BLA BLA"));
        Blabla.addMember(personRepository.findPersonByEmail(new Email("1234@isep.pt")));

        //Act:
        boolean result = groupsRepository.findGroupByDescription(new Description ("BLA BLA")).equals(Blabla);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("getting group by its description")
    void getGroupByDescriptionTestException(){
        //Arrange:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();

        personRepository.createPerson("Homer", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto", "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));


        groupsRepository.createGroup(new Description("BLA BLA"),personRepository.findPersonByEmail(new Email("1234@isep.pt")));


        //Act:
        try {
            groupsRepository.findGroupByDescription(new Description("BLA BLA"));
        }

        //Assert:
        catch (IllegalArgumentException error){
            assertEquals("No group was found with the given description", error.getMessage());
        }
    }

    @Test
    void findGroupByID() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupsRepository groupsRepository= new GroupsRepository();
        groupsRepository.createGroup(new Description("Familia"), person);
        Group expected= new Group(new Description("Familia"));

        //Act
        Group actual=groupsRepository.getByID(new GroupID(new Description("Familia")));

        //Assert
        assertEquals(expected, actual);

    }


    /**
     * Test if GroupID is in the Repository
     */

    @Test
    @DisplayName("Validate if GroupID is the repository - True")
    void isGroupIDOnRepositoryTrue() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupsRepository groupsRepository= new GroupsRepository();
        groupsRepository.createGroup(new Description("Familia"), person);

        //Act
        boolean groupIDexists = groupsRepository.isIDOnRepository(new GroupID(new Description("Familia")));

        //Assert
        assertTrue(groupIDexists);
    }

    @Test
    @DisplayName("Validate if GroupID is the repository - false")
    void isGroupIDOnRepositoryFalse() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupsRepository groupsRepository= new GroupsRepository();
        groupsRepository.createGroup(new Description("Familia"), person);

        //Act
        boolean groupIDexists = groupsRepository.isIDOnRepository(new GroupID(new Description("Familia")));

        //Assert
        assertTrue(groupIDexists);
    }

}