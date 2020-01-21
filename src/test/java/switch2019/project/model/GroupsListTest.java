package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupsListTest {

    /**
     * Check if group can be created
     */
    @Test
    @DisplayName("Test if Group was Created")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("John",2015,5,10,new Address("London"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup("Test Person",person1);

        //Assert
        assertTrue(wasGroupCreated);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Francis",2012,4,23,new Address("Dublin"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup(null,person1);

        //Assert
        assertFalse(wasGroupCreated);
    }

    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", 2002,5,20,new Address("Boston"));

        //Act
        groupsList.createGroup("Grupo de Teste",person1);
        boolean wasGroupCreated = groupsList.createGroup("Grupo de Teste",person1);

        //Assert
        assertFalse(wasGroupCreated);
    }

    /**
     * Check the number of Groups inside the groupList
     */
    @Test
    @DisplayName("Test if the number of groups on the list was increased")
    public void howManyGroupsTest(){
        //Arrange
        Group group1 = new Group ("Amigos");
        Group group2 = new Group ("Pokémons");
        GroupsList groupList = new GroupsList();

        //Act
        groupList.addGroupToGroupList(group1);
        groupList.addGroupToGroupList(group2);
        int result = groupList.howManyGroups();

        //Assert
        assertEquals(2,result);
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
     *  If the list of groups consists exclusively in families.
     */
    @Test
    @DisplayName("Validate if only groups of families are being returned")
    public void ifGroupListIsOnlyFamilies_SuccessCase() {

        //Arrange
        // Global Groups List
        GroupsList globalGroupsList = new GroupsList();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", 1990, 10, 10, new Address("Miragaia"));
        Person carlosDAD = new Person("Carlos", 1990, 10, 10, new Address("Porto"));
        Person oscar = new Person("Oscar", 1990, 10, 10,
                new Address("Espinho"), manuelaMOM, carlosDAD);
        Person marta = new Person("Marta", 1990, 10, 10,
                new Address("Paranhos"), manuelaMOM, carlosDAD);
        Person joao = new Person("Joao", 1990, 10, 10,
                new Address("Matosinhos"), manuelaMOM, carlosDAD);

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, carlosDAD));
        Group family = new Group("Family");
        family.addMember(manuelaMOM);
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsList.addGroupToGroupList(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", 1990, 10, 10, new Address("Springfield"));
        Person marge = new Person("Marge", 1990, 10, 10, new Address("Springfield"));
        Person bart = new Person("Bart", 1990, 10, 10,
                new Address("Springfield"), marge, homer);
        Person lisa = new Person("Lisa", 1990, 10, 10,
                new Address("Springfield"), marge, homer);
        Person maggie = new Person("Maggie", 1990, 10, 10,
                new Address("Springfield"), marge, homer);

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(marge, bart, lisa, maggie));
        Group simpsons = new Group("Simpsons");
        simpsons.addMember(homer);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsList.addGroupToGroupList(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", 1990, 10, 10, new Address("Miragaia"));
        Person diana = new Person("Diana", 1990, 10, 10,
                new Address("Porto"), null, joaoDAD);
        Person elsa = new Person("Elsa", 1990, 10, 10,
                new Address("Matosinhos"), null, joaoDAD);
        Person ines = new Person("Ines", 1990, 10, 10,
                new Address("Paranhos"), null, joaoDAD);

        // Group
        HashSet<Person> noMomMembersToAdd = new HashSet<>(Arrays.asList(diana, elsa, ines));
        Group familyWithNoMom = new Group("Family with no Mom");
        familyWithNoMom.addMember(joaoDAD);
        familyWithNoMom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsList.addGroupToGroupList(familyWithNoMom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", 1990, 10, 10, new Address("Miragaia"));
        Person martaC = new Person("Marta Cardoso", 1990, 10, 10, new Address("Matosinhos"));
        Person martaP = new Person("Marta Pinheiro", 1990, 10, 10, new Address("Porto"));

        // Group
        HashSet<Person> martasGroupMembersToAdd = new HashSet<>(Arrays.asList(martaC, martaP));
        Group martaGroup = new Group("Marta's group");
        martaGroup.addMember(martaR);
        martaGroup.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsList.addGroupToGroupList(martaGroup);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", 1990, 10, 10, new Address("Porto"));
        Person carolyn = new Person("Princess Carolyn", 1990, 10, 10, new Address("Lisboa"));
        Person todd = new Person("Todd Chavez", 1990, 10, 10, new Address("Matosinhos"));
        Person diane = new Person("Diane Nguyen", 1990, 10, 10, new Address("Espinho"));

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

}
