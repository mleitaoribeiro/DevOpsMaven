package switch2019.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {



    /**
     * Test if member was added to Group
     */


    /**
     * Test if member was removed from Group
     */


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
