package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {

    /**
     * Methods to check if the number of groups in the GroupList is increased.(USER STORY - check if user was added).
     */
    @Test
    @DisplayName("Check if One group was added")
    public void wasGroupAddedToList(){
        //Arrange
        Person person1 = new Person("John", 1998, 10, 15, new Address("New York"));
        Person person2 = new Person("Frank", 1994, 10, 12, new Address("Washington D.C."));
        Group group1 = new Group ("Amigos");
        GroupsList groupList = new GroupsList();

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        groupList.addGroupToGroupList(group1);
        int result = groupList.howManyGroups();

        //Assert
        assertEquals(1,result);
    }

    /**
     * Method to check if a Group was created (with HashSet.contains function) - TRUE
     */
    @Test
    public void isGroupInList() {
        // Arrange Groups
        Group group1 = new Group("Amigos");
        GroupsList groupList = new GroupsList();

        // Act
        groupList.addGroupToGroupList(group1);

        // Assert
        assertTrue(groupList.groupListContains(group1));
    }

    /**
     * Method to check if a Group was created inside a GroupList (Compare with Group HashSet)
     */
    @Test
    public void isGroupInListCompare(){
        // Arrange Groups
        Group group1 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList = new GroupsList();

        // Act
        groupList.addGroupToGroupList(group1);
        HashSet<Group> expected = new HashSet<>(Collections.singleton(group1));

        //Assert
        assertEquals(groupList.getGroups(), expected);
    }

    /**
     * Method to check if Groups were created inside a GroupList (Compare with Group HashSet)
     */
    @Test
    public void areGroupsInListCompare(){
        // Arrange Groups
        Group group1 = new Group("Programadores");
        Group group2 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList = new GroupsList();

        // Act
        groupList.addGroupToGroupList(group1);
        groupList.addGroupToGroupList(group2);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(group1,group2));

        // Assert
        assertEquals(groupList.getGroups(), expected);
    }
}