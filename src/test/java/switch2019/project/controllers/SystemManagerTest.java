/*package switch2019.project.controllers;

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

class SystemManagerTest {

    /**
     * USER STORY 2 - check if user was added
     * Methods to check if the number of groups in the GroupList is increased
     */
/*
    @Test
    @DisplayName("Check if One group was added")
    public void wasGroupAddedToList(){
        //Arrange
        Person person1 = new Person("John", 1998, 10, 15, new Address("New York"));
        Person person2 = new Person("Frank", 1994, 10, 12, new Address("Washington D.C."));
        Group group1 = new Group ("Amigos");
        GroupsList groupList1 = new GroupsList();

        //Act
        group1.addMember(person1);
        group1.addMember(person2);
        groupList1.addGroupToGroupList(group1);
        int result = groupList1.howManyGroups();

        //Assert
        assertEquals(1,result);
    }

    /**
     * Method to check if a Group was created (with HashSet.contains function) - TRUE
     */
/*
    @Test
    public void isGroupInList() {
        // Arrange Groups
        Group group1 = new Group("Amigos");
        GroupsList groupList1 = new GroupsList();

        // Act
        groupList1.addGroupToGroupList(group1);

        // Assert
        assertTrue(groupList1.groupListContains(group1));
    }
    /**
     * Method to check if a Group was created (with HashSet.contains function) - FALSE
     */
/*
    @Test
    public void isGroupInListFalse() {
        // Arrange Groups
        Group newGroup = new Group("Amigos");
        Group otherNewGroup = new Group("Inimigos");
        GroupsList groupList1 = new GroupsList();

        // Act
        groupList1.addGroupToGroupList(newGroup);

        // Assert
        assertFalse(groupList1.groupListContains(otherNewGroup));
    }

    /**
     * Method to check if a Group was created inside a GroupList (Compare with Group HashSet)
     */
/*
    @Test
    public void isGroupInListCompare(){
        // Arrange Groups
        Group group1 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList1 = new GroupsList();

        // Act
        groupList1.addGroupToGroupList(group1);
        HashSet<Group> expected = new HashSet<>(Collections.singleton(group1));

        //Assert
        assertEquals(groupList1.getGroupsList(), expected);
    }

    /**
     * Method to check if Groups were created inside a GroupList (Compare with Group HashSet)
     */
/*
    @Test
    public void areGroupsInListCompare(){
        // Arrange Groups
        Group group1 = new Group("Programadores");
        Group group2 = new Group("Amigos");

        // Arrange Group List
        GroupsList groupList1 = new GroupsList();

        // Act
        groupList1.addGroupToGroupList(group1);
        groupList1.addGroupToGroupList(group2);
        HashSet<Group> expected = new HashSet<>(Arrays.asList(group1,group2));

        // Assert
        assertEquals(groupList1.getGroupsList(), expected);
    }


    /**
     * USER STORY 3 - add people to a group
     * Test if a member was added to a group
     */
/*
    @Test
    @DisplayName("Validate if one person was added to the right group - happy path")
    void addMember() {
        // Arrange
        // Members to be included in the groups
        Person person1 = new Person ("Marie", 1993, 3, 9, new Address("Viana do Castelo"));
        Person person2 = new Person("Susan",1993,3,9, new Address("Braga"));
        Person person3 = new Person("Jack", 1990,1,3, new Address("Setúbal"));

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");
        Group switchGroup = new Group("SWitCH");
        Group moviesGroup = new Group("Movies");

        //Act:
        // Group g1 - add just one member to the group
        just4FunGroup.addMember(person3);
        //Group g2 - add just one member to the group
        switchGroup.addMember(person1);
        //Group g3 - add just one member to the group
        moviesGroup.addMember(person2);

        //Assert
        assertTrue(just4FunGroup.getMembers().contains(person3) && just4FunGroup.getAdmins().contains(person3));
        assertTrue(switchGroup.getMembers().contains(person1) && switchGroup.getAdmins().contains(person1));
        assertTrue(moviesGroup.getMembers().contains(person2) && moviesGroup.getAdmins().contains(person2));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {
        // Arrange
        // Members to be included in the groups
        Person person1 = null;

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");

        //Act
        just4FunGroup.addMember(person1);

        //Assert
        assertFalse(just4FunGroup.getMembers().contains(person1));
    }


    /**
     * Test if multiple members were added to Group
     */
/*
    @Test
    @DisplayName("Validate if the rigth people were added to the right group - happy path")
    void addMultipleMembers() {
        // Arrange
        // Members to be included in the groups
        Person person1 = new Person("John",1996,12,9, new Address ("Leiria"));
        Person person2 = new Person("Anna",1993,2,23, new Address("Guimarães"));
        Person person3 = new Person("Susan",1993,3,9, new Address("Porto"));
        Person person4 = new Person("Frank",1996,12,5, new Address("Guarda"));
        Person person5 = new Person("Jessica",2002,12,3, new Address("Santarém"));
        Person person6 = new Person("Jack", 1990,1,3, new Address("Coimbra"));

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");
        Group switchGroup = new Group("SWitCH");
        Group moviesGroup = new Group("Movies");

        //Act
        //Group g1 - add several members to the group
        HashSet<Person> groupTestJust4Fun= new HashSet<>(Arrays.asList(person1,person3,person4));
        just4FunGroup.addMultipleMembers(groupTestJust4Fun);

        //Group g2 - add several members to the group
        HashSet<Person> groupTestSwitch= new HashSet<>(Arrays.asList(person2,person5));
        switchGroup.addMultipleMembers(groupTestSwitch);

        //Group g3 - add several members to the group
        HashSet<Person> groupTestMovies= new HashSet<>(Arrays.asList(person6,person2,person4));
        moviesGroup.addMultipleMembers(groupTestMovies);

        //Assert
        assertTrue(just4FunGroup.getMembers().containsAll(groupTestJust4Fun) && just4FunGroup.getAdmins().size() == 1);
        assertTrue(switchGroup.getMembers().containsAll(groupTestSwitch) && switchGroup.getAdmins().size() == 1);
        assertTrue(moviesGroup.getMembers().containsAll(groupTestMovies) && moviesGroup.getAdmins().size() == 1);
    }

    @Test
    @DisplayName("Validate if the same person is not added twice")
    void addMultipleMembersNotAddedTwice() {
        // Arrange
        // Members to be included in the groups
        Person person1 = new Person("John",1996,12,9, new Address("Paranhos"));
        Person person2 = new Person("Susan",1993,3,9,new Address("Guimarães") );
        Person person3 = new Person("Frank",1996,12,5,new Address("Lisboa") );

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");

        //Act
        HashSet<Person> groupTestJust4Fun = new HashSet<>(Arrays.asList(person1,person2,person3));
        just4FunGroup.addMultipleMembers(groupTestJust4Fun);

        //Assert
        assertFalse(just4FunGroup.getMembers().size() ==2);
        assertTrue(just4FunGroup.getMembers().size() ==3 && just4FunGroup.getAdmins().size() == 1);
    }

    @Test
    @DisplayName("Validate if a null case is added to group")
    void addMultipleMembersOneNull() {
        // Arrange
        // Members to be included in the groups
        Person person1 = new Person("Anna",1993,2,23, new Address("Porto"));
        Person person2 = new Person("Jessica",2002,12,3, new Address("Caminha"));
        Person person3 = null;

        // Groups created by the system manager
        Group group1 = new Group("SWitCH");

        //Act
        HashSet<Person> groupTestSwitch = new HashSet<>(Arrays.asList(person1,person2,person3));
        group1.addMultipleMembers(groupTestSwitch);

        //Assert
        assertFalse(group1.getMembers().contains(person3));
    }

}*/