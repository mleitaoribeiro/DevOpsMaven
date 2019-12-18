package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {



    /**
     * Test if member was added to Group
     */

    @Test
    @DisplayName("Validate is a member was added to a group")
    void addMember() {

        //Arrange
        Person marta = new Person("Marta", 2000, 10, 10);
        Group A = new Group("OsMaisFixes", 2019, 10, 10);

        //Act
        A.addMember(marta);

        //Assert
        assertTrue(A.members.contains(marta));
    }

    /**
     * Test if multiple members were added to Group
     */

    @Test
    @DisplayName("Test if all members were added to Group => Sucess Case")
    void addMultipleMembers_Sucess() {
        //Arrange
        Group newGroup = new Group("M'Nation", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = new Person("Mariana",1986,12,01);
        Person person3 = new Person("Marisa",2000,8,27);

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person3,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertTrue(newGroup.getMembers().containsAll(finalGroup));
    }

    @Test
    @DisplayName("Test if the same person is not added twice")
    void addMultipleMembers__ErrorCase() {
        //Arrange
        Group newGroup = new Group("Maria's Group", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = new Person("Maria", 1994, 05, 01);

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertFalse(newGroup.getMembers().containsAll(finalGroup));
        assertTrue(newGroup.getMembers().contains(person1));
    }

    @Test
    @DisplayName("Test if a null case is added to group")
    void addMultipleMembers__ErrorCase2() {
        //Arrange
        Group newGroup = new Group("Grupo das M'Nation", 2019,12, 18 );

        Person person1 = new Person("Maria", 1994, 05, 01);
        Person person2 = null;

        HashSet<Person> finalGroup= new HashSet<>(Arrays.asList(person1,person2));

        //Act
        newGroup.addMultipleMembers(finalGroup);

        //Assert
        assertTrue(newGroup.getMembers().contains(person1));
    }

    /**
     * Test if member was removed from Group
     */


    /**
     * Test if multiple members were removed from a Group
     */

    @Test
    @DisplayName("Test if multiple members were removed from a Group - remove all ")
    void removeMultipleMembersFromAGroup(){
        //Arrange
        Group g1=new Group("G1",2005,2,12);
        Person p1=new Person("Pedro",1999,12,9);
        Person p2=new Person("Gabriel",1996,3,6);
        HashSet<Person>putMembers=new HashSet<>(Arrays.asList(p1,p2));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(putMembers);

        //Assert
        assertTrue(g1.getMembers().size()==0);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers() {
        //Arrange
        Group g1 = new Group("G1", 2005, 2, 12);
        Person p1 = new Person("Pedro", 1999, 12, 9);
        Person p2 = new Person("Gabriel", 1996, 3, 6);
        Person p3 = new Person("Laurinda", 1998, 3, 14);
        HashSet<Person> putMembers = new HashSet<>(Arrays.asList(p1, p2, p3));
        HashSet<Person> removeSome = new HashSet<>(Arrays.asList(p2, p3));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(removeSome);

        //Assert
        assertTrue(g1.getMembers().size() == 1);
    }
    /**
     * Check if member was promoted to Admin
     */


    /**
     * Check if member was demoted from Admin
     */


    /**
     * Test if a Group is a family
     */


    /**
     * Test Equals method for the Group class
     */
    @Test
    void equalsGroupClass_true() {
        //Arrange
       // Person p1=new Person("Elsa",null,null,null);

        Group g1=new Group("Familia",1987,01,16);
        Group g2= new Group("Familia",1987,01,16);

        //Act
        boolean result= g1.equals(g2);

        //Assert
        assertTrue(result);

    }
}
