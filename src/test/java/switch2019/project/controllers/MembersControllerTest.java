package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Group;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MembersControllerTest {

    /**
     * User Story - Test if a member was added to a group
     * SetUp for testing
     */

    // Arrange
    // Members to be included in the groups
    Person p1 = new Person ("Marie", 2000, 10, 3);
    Person p2 = new Person("John",1996,12,9);
    Person p3 = new Person("Anna",1993,2,23);
    Person p4 = new Person("Susan",1993,3,9);
    Person p5 = new Person("Frank",1996,12,5);
    Person p6 = new Person("Jessica",2002,12,3);
    Person p7 = new Person("Jack", 1990,1,3);
    Person p8 = null;

    // Groups created by the system manager
    Group g1 = new Group("Just4Fun", 2019, 11, 10);
    Group g2 = new Group("SWitCH", 2019, 10, 7);
    Group g3 = new Group("Movies", 2015, 5, 18);


    /**
     * Test if a member was added to a group
     */

    @Test
    @DisplayName("Validate if one person was added to the right group - happy path")
    void addMember() {

        //Act:
        // Group g1 - add just one member to the group
        g1.addMember(p7);
        //Group g2 - add just one member to the group
        g2.addMember(p1);
        //Group g3 - add just one member to the group
        g3.addMember(p4);

        //Assert
        assertTrue(g1.getMembers().contains(p7));
        assertTrue(g2.getMembers().contains(p1));
        assertTrue(g3.getMembers().contains(p4));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {

        //Act
        g1.addMember(p8);

        //Assert
        assertFalse(g1.getMembers().contains(p8));
    }


    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Validate if the rigth people were added to the right group - happy path")
    void addMultipleMembers() {

        //Act
        //Group g1 - add several members to the group
        HashSet<Person> finalGroup1= new HashSet<>(Arrays.asList(p2,p4,p5));
        g1.addMultipleMembers(finalGroup1);
        //Group g2 - add several members to the group
        HashSet<Person> finalGroup2= new HashSet<>(Arrays.asList(p3,p6));
        g2.addMultipleMembers(finalGroup2);
        //Group g3 - add several members to the group
        HashSet<Person> finalGroup3= new HashSet<>(Arrays.asList(p7,p3,p5));
        g3.addMultipleMembers(finalGroup3);


        //Assert
        assertTrue(g1.getMembers().containsAll(finalGroup1));
        assertTrue(g2.getMembers().containsAll(finalGroup2));
        assertTrue(g3.getMembers().containsAll(finalGroup3));
    }

    @Test
    @DisplayName("Validate if the same person is not added twice")
    void addMultipleMembersNotAddedTwice() {

        //Act
        HashSet<Person> finalGroup1 = new HashSet<>(Arrays.asList(p2,p4,p5));
        g1.addMultipleMembers(finalGroup1);

        //Assert
        assertFalse(g1.getMembers().size() ==2);
        assertTrue(g1.getMembers().size() ==3);
    }

    @Test
    @DisplayName("Validate if a null case is added to group")
    void addMultipleMembersOneNull() {

        //Act
        HashSet<Person> finalGroup2= new HashSet<>(Arrays.asList(p3,p6,p8));
        g2.addMultipleMembers(finalGroup2);

        //Assert
        assertFalse(g2.getMembers().contains(p8));
    }
    
}