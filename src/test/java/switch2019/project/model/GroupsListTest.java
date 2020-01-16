package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
     * Test if a group was to the groupList
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
}
