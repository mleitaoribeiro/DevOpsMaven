package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        Person person1 = new Person("John", LocalDate.of(2000, 12,04), new Address("London"),new Address("Rua B","Feira","4520-233"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup("Test Person", person1);

        //Assert
        assertTrue(wasGroupCreated);

    }

    /**
     * Test if Group is not created when its description is null
     */

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Francis",  LocalDate.of(2001, 04,12), new Address("Dublin"),new Address("Rua B","Feira","4520-233"));

        //Act
        boolean wasGroupCreated = groupsList.createGroup(null, person1);

        //Assert
        assertFalse(wasGroupCreated);
    }

    /**
     * Test if group was not created when it is already contained within a groupsList - Same name and same Members
     */

    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList - Same name and same Members")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);
        boolean wasGroupCreated = groupsList.createGroup("Grupo de Teste", person1);

        //Assert
        assertFalse(wasGroupCreated);
    }

    /**
     * Test if group is created even it has the same name but different members
     */

    @Test
    @DisplayName("Test if group is created even it has the same name but different members")
    public void createGroupWithSameDescriptionAndDifferentMembers() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));
        Person person2 = new Person("Marshall",LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = groupsList.createGroup("Grupo de Teste", person2);

        //Assert
        assertTrue(wasGroupCreated);
    }

    /**
     * Test if group is created with different description but same person
     */

    @Test
    @DisplayName("Test if group is created with different description but same person")
    public void createGroupWithDifferentDescriptionAndDifferentMembers() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy",  LocalDate.of(1999, 5,13), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = groupsList.createGroup("Grupo Diferente", person1);

        //Assert
        assertTrue(wasGroupCreated);
    }

    /**
     * Test if a null group was not created by a null person
     */

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
        Person manuelaMOM = new Person("Manuela",  LocalDate.of(1960, 10,10),new Address("Miragaia"),new Address("Rua B","Gaia","4520-233"));
        Person carlosDAD = new Person("Carlos", LocalDate.of(1950, 12,12), new Address("Porto"),new Address("Rua B","Gaia","4520-233"));
        Person oscar = new Person("Oscar",  LocalDate.of(1990, 12,04), new Address("Espinho"),new Address("Rua B","Gaia","4520-233"), manuelaMOM, carlosDAD);
        Person marta = new Person("Marta", LocalDate.of(1995, 11,05), new Address("Paranhos"),new Address("Rua B","Gaia","4520-233"), manuelaMOM, carlosDAD);
        Person joao = new Person("Joao", LocalDate.of(2000, 01,12),new Address("Matosinhos"),new Address("Rua B","Gaia","4520-233"), manuelaMOM, carlosDAD);

        // Group
        HashSet<Person> familyMembersToAdd = new HashSet<>(Arrays.asList(oscar, marta, joao, carlosDAD));
        Group family = new Group("Family");
        family.addMember(manuelaMOM);
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsList.addGroupToGroupList(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", LocalDate.of(1990, 12,04), new Address("Springfield"),new Address("Rua B","Porto","4520-233"));
        Person marge = new Person("Marge", LocalDate.of(1990, 12,04), new Address("Springfield"),new Address("Rua B","Porto","4520-233"));
        Person bart = new Person("Bart", LocalDate.of(1990, 12,04), new Address("Springfield"),new Address("Rua B","Porto","4520-233"), marge, homer);
        Person lisa = new Person("Lisa", LocalDate.of(1990, 12,04), new Address("Springfield"), new Address("Rua B","Porto","4520-233"),marge, homer);
        Person maggie = new Person("Maggie", LocalDate.of(1990, 12,04), new Address("Springfield"),new Address("Rua B","Porto","4520-233"), marge, homer);

        // Group
        HashSet<Person> simpsonsMembersToAdd = new HashSet<>(Arrays.asList(marge, bart, lisa, maggie));
        Group simpsons = new Group("Simpsons");
        simpsons.addMember(homer);
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsList.addGroupToGroupList(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", LocalDate.of(1990, 12,04), new Address("Miragaia"),new Address("Rua B","Gaia","4520-233"));
        Person diana = new Person("Diana", LocalDate.of(1990, 12,04), new Address("Porto"),new Address("Rua B","Gaia","4520-233"), null, joaoDAD);
        Person elsa = new Person("Elsa", LocalDate.of(1990, 12,04), new Address("Matosinhos"),new Address("Rua B","Gaia","4520-233"), null, joaoDAD);
        Person ines = new Person("Ines", LocalDate.of(1990, 12,04), new Address("Paranhos"),new Address("Rua B","Gaia","4520-233" ),null, joaoDAD);

        // Group
        HashSet<Person> noMomMembersToAdd = new HashSet<>(Arrays.asList(diana, elsa, ines));
        Group familyWithNoMom = new Group("Family with no Mom");
        familyWithNoMom.addMember(joaoDAD);
        familyWithNoMom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsList.addGroupToGroupList(familyWithNoMom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", LocalDate.of(1990, 12,04), new Address("Miragaia"),new Address("Rua B","Gaia","4520-233"));
        Person martaC = new Person("Marta Cardoso", LocalDate.of(1990, 12,04), new Address("Matosinhos"),new Address("Rua B","Gaia","4520-233"));
        Person martaP = new Person("Marta Pinheiro", LocalDate.of(1990, 12,04), new Address("Porto"),new Address("Rua B","Gaia","4520-233"));

        // Group
        HashSet<Person> martasGroupMembersToAdd = new HashSet<>(Arrays.asList(martaC, martaP));
        Group martaGroup = new Group("Marta's group");
        martaGroup.addMember(martaR);
        martaGroup.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsList.addGroupToGroupList(martaGroup);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", LocalDate.of(1990, 12,04), new Address("Porto"),new Address("Rua B","Porto","4520-233"));
        Person carolyn = new Person("Princess Carolyn", LocalDate.of(1990, 12,04), new Address("Lisboa"),new Address("Rua B","Porto","4520-233"));
        Person todd = new Person("Todd Chavez", LocalDate.of(1990, 12,04), new Address("Matosinhos"),new Address("Rua B","Porto","4520-233"));
        Person diane = new Person("Diane Nguyen", LocalDate.of(1990, 12,04), new Address("Espinho"),new Address("Rua B","Porto","4520-233"));

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
