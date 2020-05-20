package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionIDJpaTest {

    /**
     * Tests to Equals
     */

    @Test
    @DisplayName("Test Equals - Exactly The Same Object")
    void testEqualsExactlySameObject() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");

        // Act & Assert
        assertEquals(transactionIDJpa, transactionIDJpa);

    }


    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange
        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        TransactionIDJpa transactionIDJpa2 = new TransactionIDJpa(1, "Switc");

        // Act & Assert
        assertEquals(transactionIDJpa, transactionIDJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentID() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        TransactionIDJpa transactionIDJpa2 = new TransactionIDJpa(2, "Switc");
        //Act
        boolean equalObjects = transactionIDJpa.equals(transactionIDJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentLedger_ID() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Simpsons");
        TransactionIDJpa transactionIDJpa2 = new TransactionIDJpa(1, "Switc");
        //Act
        boolean equalObjects = transactionIDJpa.equals(transactionIDJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Simpsons");
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act
        boolean equalObjects = transactionIDJpa.equals(accountIDJpa);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange
        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Simpsons");

        //Act
        boolean equalObjects = transactionIDJpa.equals(null);

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

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        TransactionIDJpa transactionIDJpa2 = new TransactionIDJpa(1, "Switc");


        // Act & Assert
        assertEquals(transactionIDJpa.hashCode(), transactionIDJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode")
    void testDifferentHashCode() {

        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        TransactionIDJpa transactionIDJpa2 = new TransactionIDJpa(1, "simpsons");


        // Act & Assert
        assertNotEquals(transactionIDJpa.hashCode(), transactionIDJpa2.hashCode());
    }

    /**
     * Tests to Getters
     */
    @Test
    @DisplayName("Test getID")
    void getID() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        long expectedID = 1;

        //Act
        long realID = transactionIDJpa.getId();

        // Assert
        assertEquals(expectedID, realID);

    }

    @Test
    @DisplayName("Test getLedger_id")
    void getLeder_ID() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switch");
        String expectedLedger_ID = "Switch";

        //Act
        String realLedger_ID = transactionIDJpa.getLedger_id();

        // Assert
        assertEquals(expectedLedger_ID, realLedger_ID);

    }

    /**
     * Tests to Setters
     */

    @Test
    @DisplayName("Test setID")
    void setID() {

        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switc");
        long expectedID = 1;

        //Act
        transactionIDJpa.setId(expectedID);

        long realID = transactionIDJpa.getId();
        // Assert
        assertEquals(expectedID, realID);

    }

    @Test
    @DisplayName("Test setLedger_ID")
    void setLedger_ID() {
        //Arrange

        TransactionIDJpa transactionIDJpa = new TransactionIDJpa(1, "Switch");
        String expectedLedger_ID = "Switch";

        //Act
        transactionIDJpa.setLedger_id(expectedLedger_ID);
        String realLedger_ID = transactionIDJpa.getLedger_id();

        // Assert
        assertEquals(expectedLedger_ID, realLedger_ID);

    }
}

