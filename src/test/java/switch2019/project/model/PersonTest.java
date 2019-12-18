package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    /**
     * Validate Input for Name
     */

    @Test
    @DisplayName("Test for validating imput's name")
    public void validateName(){
        //Arrange
        Person A = new Person(null, null);

        //Act
        A.setName("Alex");
        String expected = "Alex";

        //Assert
        assertEquals(expected, A.getName());
    }

    /**
     *Validate Input for Birthday Date
     */


    /**
     *Test if two individuals are the same or not
     */


    /**
     *Test if multiple siblings were added to siblings list
     */


    /**
     *Validate if a sibling was added to siblings list
     */

    /**
     * Test if multiple siblings were removed to siblings list
     */

    /**
     * Validate if a sibling was added to siblings list
     */

    /**
     * Test if two people have the same siblings
     */
}