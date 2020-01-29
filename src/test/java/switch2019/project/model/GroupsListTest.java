package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.GetPersonalAndGroupTransactionsController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupsListTest {

    /**
     * As user , I Want to Creat a group and be admin (US002.1)
     */

    @Test
    @DisplayName("Test if Group was Created")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("John", LocalDate.of(2000, 12, 04), new Address("London"), new Address("Rua B", "Feira", "4520-233"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup("Test Person", person1);

        //Assert
        assertTrue(wasGroupCreated);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Francis", LocalDate.of(2001, 04, 12), new Address("Dublin"), new Address("Rua B", "Feira", "4520-233"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup(null, person1);

        //Assert
        assertFalse(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList - Same name and same Members")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12, 04), new Address("Boston"), new Address("Rua B", "Gaia", "4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);
        boolean wasGroupCreated = groupsList.createGroup("Grupo de Teste", person1);

        //Assert
        assertFalse(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group is created even it has the same name but different members")
    public void createGroupWithSameDescriptionAndDifferentMembers() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12, 04), new Address("Boston"), new Address("Rua B", "Gaia", "4520-233"));
        Person person2 = new Person("Marshall", LocalDate.of(1990, 12, 04), new Address("Boston"), new Address("Rua B", "Gaia", "4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = groupsList.createGroup("Grupo de Teste", person2);

        //Assert
        assertTrue(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group is created with different description but same person")
    public void createGroupWithDifferentDescriptionAndDifferentMembers() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1999, 5, 13), new Address("Boston"), new Address("Rua B", "Gaia", "4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = groupsList.createGroup("Grupo Diferente", person1);

        //Assert
        assertTrue(wasGroupCreated);
    }

    @Test
    @DisplayName("Test if group was not created when its null")
    public void testIfWasCreatedWhenNull() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = null;

        //Act
        boolean wasGroupCreated = groupsList.createGroup(null, person1);

        //Assert
        assertFalse(wasGroupCreated);
    }

    /**
     * Check the number of Groups inside the groupList
     */
    @Test
    @DisplayName("Test if the number of groups on the list was increased")
    public void howManyGroupsTest() {
        //Arrange
        Group group1 = new Group("Amigos");
        Group group2 = new Group("Pokémons");
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(group1);
        groupList.addGroupToGroupList(group2);
        int result = groupList.howManyGroups();

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
        Group group1 = new Group("Switchieees");
        Group group2 = new Group("Clube da Costura");
        GroupsList groupList = new GroupsList();

        //Act
        boolean groupAdded = groupList.addGroupToGroupList(group1);

        //Assert
        assertTrue(groupAdded);
    }

    @Test
    @DisplayName("Test if a null group is not added to the list")
    public void testGroupIsNotInList() {
        //Arrange
        Group group1 = new Group(null);
        GroupsList groupList = new GroupsList();

        //Act
        boolean groupAdded = groupList.addGroupToGroupList(group1);

        //Assert
        assertTrue(groupAdded);
    }

    @Test
    @DisplayName("Test if a null group is not added to the list")
    public void testAddGroupIsNull() {
        //Arrange
        GroupsList groupList = new GroupsList();

        //Act
        boolean groupAdded = groupList.addGroupToGroupList(null);

        //Assert
        assertFalse(groupAdded);
    }

    @Test
    @DisplayName("Test if more than one group was added is in the list")
    public void testGroupIsInList_MoreThanOne() {
        //Arrange
        Group group1 = new Group("Switchieees");
        Group group2 = new Group("Clube da Costura");
        Group group3 = new Group("Clube dos Livros");

        GroupsList groupList = new GroupsList();

        //Act
        boolean group1added = groupList.addGroupToGroupList(group1);
        boolean group2added = groupList.addGroupToGroupList(group2);
        boolean group3added = groupList.addGroupToGroupList(group3);

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
        GroupsList globalGroupsList = new GroupsList();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", LocalDate.of(1960, 10, 10), new Address("Miragaia"), new Address("Rua B", "Gaia", "4520-233"));
        Person carlosDAD = new Person("Carlos", LocalDate.of(1950, 12, 12), new Address("Porto"), new Address("Rua B", "Gaia", "4520-233"));
        Person oscar = new Person("Oscar", LocalDate.of(1990, 12, 04), new Address("Espinho"), new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD);
        Person marta = new Person("Marta", LocalDate.of(1995, 11, 05), new Address("Paranhos"), new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD);
        Person joao = new Person("Joao", LocalDate.of(2000, 01, 12), new Address("Matosinhos"), new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD);

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, carlosDAD));
        Group family = new Group("Family");
        family.addMember(manuelaMOM);
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsList.addGroupToGroupList(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", LocalDate.of(1990, 12, 04), new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"));
        Person marge = new Person("Marge", LocalDate.of(1990, 12, 04), new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"));
        Person bart = new Person("Bart", LocalDate.of(1990, 12, 04), new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"), marge, homer);
        Person lisa = new Person("Lisa", LocalDate.of(1990, 12, 04), new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"), marge, homer);
        Person maggie = new Person("Maggie", LocalDate.of(1990, 12, 04), new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"), marge, homer);

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(marge, bart, lisa, maggie));
        Group simpsons = new Group("Simpsons");
        simpsons.addMember(homer);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsList.addGroupToGroupList(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", LocalDate.of(1990, 12, 04), new Address("Miragaia"), new Address("Rua B", "Gaia", "4520-233"));
        Person diana = new Person("Diana", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD);
        Person elsa = new Person("Elsa", LocalDate.of(1990, 12, 04), new Address("Matosinhos"), new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD);
        Person ines = new Person("Ines", LocalDate.of(1990, 12, 04), new Address("Paranhos"), new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD);

        // Group
        HashSet<Person> noMomMembersToAdd = new HashSet<>(Arrays.asList(diana, elsa, ines));
        Group familyWithNoMom = new Group("Family with no Mom");
        familyWithNoMom.addMember(joaoDAD);
        familyWithNoMom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsList.addGroupToGroupList(familyWithNoMom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", LocalDate.of(1990, 12, 04), new Address("Miragaia"), new Address("Rua B", "Gaia", "4520-233"));
        Person martaC = new Person("Marta Cardoso", LocalDate.of(1990, 12, 04), new Address("Matosinhos"), new Address("Rua B", "Gaia", "4520-233"));
        Person martaP = new Person("Marta Pinheiro", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua B", "Gaia", "4520-233"));

        // Group
        HashSet<Person> martasGroupMembersToAdd = new HashSet<>(Arrays.asList(martaC, martaP));
        Group martaGroup = new Group("Marta's group");
        martaGroup.addMember(martaR);
        martaGroup.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsList.addGroupToGroupList(martaGroup);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua B", "Porto", "4520-233"));
        Person carolyn = new Person("Princess Carolyn", LocalDate.of(1990, 12, 04), new Address("Lisboa"), new Address("Rua B", "Porto", "4520-233"));
        Person todd = new Person("Todd Chavez", LocalDate.of(1990, 12, 04), new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"));
        Person diane = new Person("Diane Nguyen", LocalDate.of(1990, 12, 04), new Address("Espinho"), new Address("Rua B", "Porto", "4520-233"));

        // Group
        HashSet<Person> bojackGangMembersToAdd = new HashSet<>(Arrays.asList(carolyn, todd, diane));
        Group bojackGang = new Group("Bojack's Gang");
        bojackGang.addMember(bojack);
        bojackGang.addMultipleMembers(bojackGangMembersToAdd);
        globalGroupsList.addGroupToGroupList(bojackGang);

        //Act
        HashSet<Group> realResult = globalGroupsList.returnOnlyFamilies();
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
        GroupsList testGroupList = new GroupsList();

            //Arrange Groups:
        Group testGroup = new Group("test group");
        testGroupList.addGroupToGroupList(testGroup);

            //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        testGroup.addMember(testGroupAdmin);

        //Act:
        boolean result = testGroupList.checkIfAPersonIsAdminInAGivenGroup("test group",testGroupAdmin);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Person is not an Admin")
    void isPersonAdminOfAGivenGroupFalse() {

        //Arrange:
        GroupsList testGroupList = new GroupsList();

        //Arrange Groups:
        Group testGroup = new Group("test group");
        testGroupList.addGroupToGroupList(testGroup);

        //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        //Act:
        boolean result = testGroupList.checkIfAPersonIsAdminInAGivenGroup("test group",testGroupAdmin);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Group Description is not found")
    void isPersonAdminOfAGivenGroupException() {

        //Arrange:
        GroupsList testGroupList = new GroupsList();

        //Arrange Groups:
        Group testGroup = new Group("test group");
        testGroupList.addGroupToGroupList(testGroup);

        //Arrange Admin:
        Person testGroupAdmin = new Person("Francisco", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        testGroup.addMember(testGroupAdmin);

        //Act:
        try {
            testGroupList.checkIfAPersonIsAdminInAGivenGroup("blabla", testGroupAdmin);
        } catch (IllegalArgumentException groupNotFound) {
            assertEquals("There're no groups found with that description.", groupNotFound.getMessage());
        }
    }

    @Test
    void createTransactionOnSpecificGroup() {
        // Arrange ____________________________________________________________________________________________________

        // Person:
        Person person = new Person("Marta", LocalDate.of(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories:

        person.createCategoryAndAddToCategoryList("grocery");
        Category categoryFriends = new Category("friends");
        person.createCategoryAndAddToCategoryList("friends");

        //Type:
        boolean typeDebit = false; // debit

        //Monetary Value:
        MonetaryValue monetaryValue100 = new MonetaryValue(100, Currency.getInstance("EUR"));

        // Groups:
        GroupsList groupsList = new GroupsList();
        Group spiceGirls = new Group("spice girls");
        Group work = new Group("work");
        groupsList.createGroup("spice girls", person);
        groupsList.createGroup("work", person);

        // Group Accounts:
        Account accountCombustivel = new Account("combustivel", "gastos de combustivél");
        spiceGirls.createGroupAccount("combustivel", "gastos de combustivél");
        Account accountGato = new Account("comida de gato", "comida para a gatinha");
        spiceGirls.createGroupAccount("comida de gato", "comida para a gatinha");
        spiceGirls.createGroupAccount("dinner", "partilha de jantares");

        work.createGroupAccount("comida de gato", "comida para a gatinha");
        work.createGroupAccount("dinner", "partilha de jantares");

        //Act
        boolean result = groupsList.createTransactionOnSpecificGroup(person, "spice girls",
                monetaryValue100, "payment", LocalDateTime.of(2019, 12, 25, 12, 15),
                categoryFriends, accountCombustivel, accountGato, typeDebit);

        // Arrange ___________________________________________________________________________________________________
        assertTrue(result);
    }

    @Test
    @DisplayName("Create Transaction group description isn't contained in GroupList and person is a member")
    void testIfAGroupThatIsNotInTheListCanCreateTransaction() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("JUST4FUN", person);
        try {
            //Act
            groupsList.createTransactionOnSpecificGroup(person, "Tarzan", amount, description,
                    LocalDateTime.of(1995,12,4,00,00), category, from, to, false);

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("JUST4FUN", person);
        try {
            //Act
            groupsList.createTransactionOnSpecificGroup(person1, "Tarzan", amount, description,
                    LocalDateTime.of(1995,12,4,00,00), category, from, to, false);

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("There're no groups found with that description.", result.getMessage());
        }
    }

    @Test
    void scheduleNewTransactionDaily() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupsList.createScheduleOnSpecificGroup(person, "tarzan", "daily",
                amount, description, null, category, from, to, type);

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 10);
    }


    @Test
    void scheduleNewTransactionWorkingDays() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupsList.createScheduleOnSpecificGroup(person, "tarzan", "working days",
                amount, description, null, category, from, to, type);

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 4);
    }

    @Test
    void scheduleNewTransactionWeekly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupsList.createScheduleOnSpecificGroup(person, "tarzan", "weekly",
                amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 4);
    }


    @Test
    void scheduleNewTransactionMonthly() throws InterruptedException {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupsList.createScheduleOnSpecificGroup(person, "tarzan", "monthly",
                amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 3);
    }

    @Test
    void scheduleNewTransactionNoMatch() {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        try {
            //Act
            groupsList.createScheduleOnSpecificGroup(person, "tarzan", "monthly",
                    amount, description, null, category, from, to, type);

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
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("JUST4FUN", person);

        try {
            //Act
            groupsList.createScheduleOnSpecificGroup(person, "tarzan", "monthly",
                    amount, description, null, category, from, to, type);

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
        Person personMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Person personNotMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        personMember.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        personMember.createAccount("Wallet", "General expenses");
        personMember.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("JUST4FUN", personMember);

        try {
            //Act
            groupsList.createScheduleOnSpecificGroup(personNotMember, "JUST4FUN", "monthly",
                    amount, description, null, category, from, to, type);

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }

    @Test
    @DisplayName("In case the person is not a member of that group")
    void createNewTransactionWherePersonIsNotAMember()  {

        //Arrange
        Person personMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Person personNotMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        personMember.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        personMember.createAccount("Wallet", "General expenses");
        personMember.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("JUST4FUN", personMember);

        try {
            //Act
            groupsList.createTransactionOnSpecificGroup(personNotMember, "JUST4FUN",
                    amount, description, null, category, from, to, type);

        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }
}
