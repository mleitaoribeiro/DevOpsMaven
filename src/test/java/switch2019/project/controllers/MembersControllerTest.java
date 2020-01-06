package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Group;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MembersControllerTest {

    /**
     * Test if a member was added to a group
     */

    @Test
    @DisplayName("Validate if one person was added to the right group - happy path")
    void addMember() {
        // Arrange
        // Members to be included in the groups
        Person marieMember = new Person ("Marie", 1993, 3, 9, new Address("Viana do Castelo"));
        Person susanMember = new Person("Susan",1993,3,9, new Address("Braga"));
        Person jackMember = new Person("Jack", 1990,1,3, new Address("Setúbal"));

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");
        Group switchGroup = new Group("SWitCH");
        Group moviesGroup = new Group("Movies");

        //Act:
        // Group g1 - add just one member to the group
        just4FunGroup.addMember(jackMember);
        //Group g2 - add just one member to the group
        switchGroup.addMember(marieMember);
        //Group g3 - add just one member to the group
        moviesGroup.addMember(susanMember);

        //Assert
        assertTrue(just4FunGroup.getMembers().contains(jackMember));
        assertTrue(switchGroup.getMembers().contains(marieMember));
        assertTrue(moviesGroup.getMembers().contains(susanMember));
    }


    @Test
    @DisplayName("Validate if a member was added to a group - Person null")
    void addMemberNull() {
        // Arrange
        // Members to be included in the groups
        Person aNullPerson = null;

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");

        //Act
        just4FunGroup.addMember(aNullPerson);

        //Assert
        assertFalse(just4FunGroup.getMembers().contains(aNullPerson));
    }


    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Validate if the rigth people were added to the right group - happy path")
    void addMultipleMembers() {
        // Arrange
        // Members to be included in the groups
        Person johnPerson = new Person("John",1996,12,9, new Address ("Leiria"));
        Person annaPerson = new Person("Anna",1993,2,23, new Address("Guimarães"));
        Person susanPerson = new Person("Susan",1993,3,9, new Address("Porto"));
        Person frankPerson = new Person("Frank",1996,12,5, new Address("Guarda"));
        Person jessicaPerson = new Person("Jessica",2002,12,3, new Address("Santarém"));
        Person jackPerson = new Person("Jack", 1990,1,3, new Address("Coimbra"));

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");
        Group switchGroup = new Group("SWitCH");
        Group moviesGroup = new Group("Movies");

        //Act
        //Group g1 - add several members to the group
        HashSet<Person> groupTestJust4Fun= new HashSet<>(Arrays.asList(johnPerson,susanPerson,frankPerson));
        just4FunGroup.addMultipleMembers(groupTestJust4Fun);

        //Group g2 - add several members to the group
        HashSet<Person> groupTestSwitch= new HashSet<>(Arrays.asList(annaPerson,jessicaPerson));
        switchGroup.addMultipleMembers(groupTestSwitch);

        //Group g3 - add several members to the group
        HashSet<Person> groupTestMovies= new HashSet<>(Arrays.asList(jackPerson,annaPerson,frankPerson));
        moviesGroup.addMultipleMembers(groupTestMovies);

        //Assert
        assertTrue(just4FunGroup.getMembers().containsAll(groupTestJust4Fun));
        assertTrue(switchGroup.getMembers().containsAll(groupTestSwitch));
        assertTrue(moviesGroup.getMembers().containsAll(groupTestMovies));

    }

    @Test
    @DisplayName("Validate if the same person is not added twice")
    void addMultipleMembersNotAddedTwice() {
        // Arrange
        // Members to be included in the groups
        Person johnPerson = new Person("John",1996,12,9, new Address("Paranhos"));
        Person susanPerson = new Person("Susan",1993,3,9,new Address("Guimarães") );
        Person frankPerson = new Person("Frank",1996,12,5,new Address("Lisboa") );

        // Groups created by the system manager
        Group just4FunGroup = new Group("Just4Fun");

        //Act
        HashSet<Person> groupTestJust4Fun = new HashSet<>(Arrays.asList(johnPerson,susanPerson,frankPerson));
        just4FunGroup.addMultipleMembers(groupTestJust4Fun);

        //Assert
        assertFalse(just4FunGroup.getMembers().size() ==2);
        assertTrue(just4FunGroup.getMembers().size() ==3);
    }

    @Test
    @DisplayName("Validate if a null case is added to group")
    void addMultipleMembersOneNull() {
        // Arrange
        // Members to be included in the groups
        Person annaPerson = new Person("Anna",1993,2,23, new Address("Porto"));
        Person jessicaPerson = new Person("Jessica",2002,12,3, new Address("Caminha"));
        Person nullPerson = null;

        // Groups created by the system manager
        Group switchGroup = new Group("SWitCH");

        //Act
        HashSet<Person> groupTestSwitch = new HashSet<>(Arrays.asList(annaPerson,jessicaPerson,nullPerson));
        switchGroup.addMultipleMembers(groupTestSwitch);

        //Assert
        assertFalse(switchGroup.getMembers().contains(nullPerson));
    }

}