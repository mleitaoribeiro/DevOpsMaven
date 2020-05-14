package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CategoryIdJpaTest {

    /**
     * Tests to Equals
     */

    @Test
    @DisplayName("Test Equals -  Same Object")
    void testEqualsExactlyTheSameObject() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");

        // Act & Assert
        assertEquals(categoryIdJpa, categoryIdJpa);
    }

    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        CategoryIdJpa categoryIdJpa2 = new CategoryIdJpa("SWITCH", "Montaditos");

        // Act & Assert
        assertEquals(categoryIdJpa, categoryIdJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentOwner() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        CategoryIdJpa categoryIdJpa2 = new CategoryIdJpa("Family", "Montaditos");

        // Act & Assert
        assertFalse(categoryIdJpa.equals(categoryIdJpa2));
        assertNotEquals(categoryIdJpa,categoryIdJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different denomination")
    void testEqualsDifferentDenomination() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        CategoryIdJpa categoryIdJpa2 = new CategoryIdJpa("Family", "Lan Party");


        //Act
        boolean equalObjects = categoryIdJpa.equals(categoryIdJpa2);

        // Assert
        assertFalse(equalObjects);
        assertNotEquals(categoryIdJpa,categoryIdJpa2);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Montaditos");
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");


        //Act

        boolean equalObjects = categoryIdJpa.equals(accountIDJpa);

        // Assert
        assertFalse(equalObjects);
        assertNotEquals(categoryIdJpa, accountIDJpa);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        CategoryIdJpa categoryIdJpa2 = null;

        //Act
        boolean equalObjects = categoryIdJpa.equals(null);

        // Assert
        assertFalse(equalObjects);
        assertNotEquals(categoryIdJpa,categoryIdJpa2);
    }

    /**
     * Tests for the setter of the denomination
     */
    @Test
    @DisplayName("Test setter for Denomination - use getter to obtain expected denomination")
    void testSetDenomination() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        categoryIdJpa.setDenomination("Cinema");


        // Act & Assert
        assertEquals(categoryIdJpa.getDenomination(), "Cinema");
    }

    @Test
    @DisplayName("Test setter for Denomination - test comparing with an object")
    void testSetDenominationOtherObject() {

        //Arrange
        CategoryIdJpa categoryIdJpa = new CategoryIdJpa("SWITCH", "Montaditos");
        categoryIdJpa.setDenomination("Cinema");


        // Act & Assert
        assertEquals(categoryIdJpa.getDenomination(), "Cinema");
    }

}
