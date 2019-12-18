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


    /**
     * Test if member was removed from Group
     */


    /**
     * Test if multiple members were removed from a Group
     */

    @Test
    @DisplayName("Test if multiple members were removed from a Group - remove all ")
    void removeMultipleMembers(){
        //Arrange
        Group g1=new Group("G1",2005,2,12);
        Person p1=new Person("Pedro",1999,12,9);
        Person p2=new Person("Gabriel",1996,3,6);
        HashSet<Person>putMembers=new HashSet<>(Arrays.asList(p1,p2));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(putMembers);

        //Assert
        assertTrue(g1.getMembersList().size()==0);
    }

    @Test
    @DisplayName("Test if multiple members were removed from a Group - only the members I choose ")
    void removeMultipleMembers(){
        //Arrange
        Group g1=new Group("G1",2005,2,12);
        Person p1=new Person("Pedro",1999,12,9);
        Person p2=new Person("Gabriel",1996,3,6);
        Person p3=new Person("Laurinda",1998,3,14);
        HashSet<Person>putMembers=new HashSet<>(Arrays.asList(p1,p2,p3));

        //Act
        g1.addMultipleMembers(putMembers);
        g1.removeMultipleMembers(p2,p3);

        //Assert
        assertTrue(g1.getMembersList().size()==1);
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
