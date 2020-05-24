package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryJpaTest {

    /**
     * Tests to Equals Method
     */

    @Test
    void testEquals() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Act
        boolean result = categoryJpa.equals(categoryJpa2);

        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsNull() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = null;

        //Act
        boolean result = categoryJpa.equals(categoryJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("rick@gmail.com", "test");

        //Act
        boolean result = categoryJpa.equals(categoryJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects2() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        AccountJpa accountJpa = new AccountJpa("owner","denomination","description",0.0, "EUR");

        //Act
        boolean result = categoryJpa.equals(accountJpa);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsSameObjects() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Assert
        assertEquals(categoryJpa, categoryJpa);
    }


    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentOwner() {

        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191762@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Act
        boolean equalObjects = categoryJpa.equals(categoryJpa2);

        // Assert
        assertFalse(equalObjects);

    }

    @Test
    @DisplayName("Test Equals -  Different Denomination")
    void testEqualsDifferentDenomination() {

        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "shop");

        //Act
        boolean equalObjects = categoryJpa.equals(categoryJpa2);

        // Assert
        assertFalse(equalObjects);

    }

    @Test
    @DisplayName("Test Equals -  Different Objects")
    void testEqualsDifferent() {

        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryIdJpa categoryIdJpa2 = new CategoryIdJpa("1191765@isep.ipp.pt", "shop");

        //Act
        boolean equalObjects = categoryJpa.equals(categoryIdJpa2);

        // Assert
        assertFalse(equalObjects);

    }

    /**
     * Tests to compare Hash code
     */

    @Test
    void testHashCode() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Assert
        assertEquals(categoryJpa.hashCode(), categoryJpa2.hashCode());

    }

    @Test
    void testHashCodeFalse() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "gg");

        //Assert
        assertNotEquals(categoryJpa.hashCode(), categoryJpa2.hashCode());
    }

    @Test
    void testToString() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "test");

        //Assert
        assertEquals(categoryJpa.toString(), categoryJpa2.toString());
    }

    @Test
    void testToStringFalse() {
        //Arrange
        CategoryJpa categoryJpa = new CategoryJpa("1191765@isep.ipp.pt", "test");

        CategoryJpa categoryJpa2 = new CategoryJpa("1191765@isep.ipp.pt", "gg");

        //Assert
        assertNotEquals(categoryJpa.toString(), categoryJpa2.toString());
    }
}
