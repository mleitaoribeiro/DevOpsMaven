package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDJpaTest {

    /**
     * Tests to Equals
     */

    @Test
    @DisplayName("Test Equals - Exactly The Same Object")
    void testEqualsExactlyTheSameObject() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        // Act & Assert
        assertEquals(accountIDJpa, accountIDJpa);


    }

    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountIDJpa accountIDJpa2 = new AccountIDJpa("SWITCH", "Revolut");


        //Act
        boolean equalObjects = accountIDJpa.equals(accountIDJpa2);

        // Assert
        assertTrue(equalObjects);

    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentOwner() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountIDJpa accountIDJpa2 = new AccountIDJpa("Family", "Revolut");


        //Act
        boolean equalObjects = accountIDJpa.equals(accountIDJpa2);

        // Assert
        assertFalse(equalObjects);

    }

    @Test
    @DisplayName("Test Equals -  Different denomination")
    void testEqualsDifferentDenomination() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountIDJpa accountIDJpa2 = new AccountIDJpa("SWITCH", "Revo");


        //Act
        boolean equalObjects = accountIDJpa.equals(accountIDJpa2);

        // Assert
        assertFalse(equalObjects);

    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");


        //Act

        boolean equalObjects = accountIDJpa.equals(accountJpa);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act
        boolean equalObjects = accountIDJpa.equals(null);

        // Assert
        assertFalse(equalObjects);
    }


    /**
     * Tests to HashCode
     */


    @Test
    @DisplayName("Test Hashcode - Same Hashcode")
    void testSameHashCode() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountIDJpa accountIDJpa2 = new AccountIDJpa("SWITCH", "Revolut");

        //Act
        int accountJpa1HashCode = accountIDJpa.hashCode();
        int accountJpa2Hashcode = accountIDJpa2.hashCode();

        // Assert
        assertEquals(accountJpa1HashCode,accountJpa2Hashcode);

    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode")
    void testDifferentHashCode() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");
        AccountIDJpa accountIDJpa2 = new AccountIDJpa("SWITCH", "Revo");

        //Act
        int accountJpa1HashCode = accountIDJpa.hashCode();
        int accountJpa2Hashcode = accountIDJpa2 .hashCode();

        // Assert
        assertNotEquals(accountJpa1HashCode,accountJpa2Hashcode);

    }

    /**
     * Tests to Getters
     */

    @Test
    @DisplayName("Test getDenomination")
    void getDenomination() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        String expectedDenomination = "Revolut";

        //Act
        String realDenomination = accountIDJpa.getDenomination();

        // Assert
        assertEquals(expectedDenomination, realDenomination);

    }


    @Test
    @DisplayName("Test getOwner")
    void getOwner() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        String expectedOwner = "SWITCH";

        //Act
        String realOwner = accountIDJpa.getOwner();

        // Assert
        assertEquals(expectedOwner, realOwner);

    }


    /**
     * Tests to Setters
     */

    @Test
    @DisplayName("Test setDenomination")
    void setDenomination() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        String expectedDenomination = "Revo";

        //Act
        accountIDJpa.setDenomination(expectedDenomination);

        String realDenomination = accountIDJpa.getDenomination();

        // Assert
        assertEquals(expectedDenomination, realDenomination);
    }

    @Test
    @DisplayName("Test setOwner")
    void setOwner() {

        //Arrange
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        String expectedOwner = "blablabla";

        //Act
        accountIDJpa.setOwner(expectedOwner);

        String realOwner = accountIDJpa.getOwner();

        // Assert
        assertEquals(expectedOwner, realOwner);

    }
}