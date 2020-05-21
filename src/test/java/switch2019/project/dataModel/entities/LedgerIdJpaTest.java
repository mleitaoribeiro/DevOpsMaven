package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerIdJpaTest {

    /**
     * Tests for the Equals method
     */

    @Test
    @DisplayName("Test Equals - Exactly The Same Object")
    void testEqualsExactlySameObject() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa = new LedgerIdJpa("test@email.com");

        // Act & Assert:
        assertEquals(ledgerIDJpa, ledgerIDJpa);

    }


    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa1 = new LedgerIdJpa("test@email.com");
        LedgerIdJpa ledgerIDJpa2 = new LedgerIdJpa("test@email.com");

        // Act & Assert:
        assertEquals(ledgerIDJpa1, ledgerIDJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentID() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa1 = new LedgerIdJpa("test@email.com");
        LedgerIdJpa ledgerIDJpa2 = new LedgerIdJpa("test2@email.com");

        //Act:
        boolean equalObjects = ledgerIDJpa1.equals(ledgerIDJpa2);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa = new LedgerIdJpa("test@email.com");
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act:
        boolean equalObjects = ledgerIDJpa.equals(accountIDJpa);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa1 = new LedgerIdJpa("test@email.com");
        LedgerIdJpa ledgerIDJpa2 = null;

        //Act:
        boolean equalObjects = ledgerIDJpa1.equals(ledgerIDJpa2);

        // Assert:
        assertFalse(equalObjects);
    }

    /**
     * Tests for the Hashcode
     */

    @Test
    @DisplayName("Test Hashcode - Same Hashcode")
    void testSameHashCode() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa1 = new LedgerIdJpa("test@email.com");
        LedgerIdJpa ledgerIDJpa2 = new LedgerIdJpa("test@email.com");


        // Act & Assert:
        assertEquals(ledgerIDJpa1.hashCode(), ledgerIDJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode")
    void testDifferentHashCode() {

        //Arrange:
        LedgerIdJpa ledgerIDJpa1 = new LedgerIdJpa("test@email.com");
        LedgerIdJpa ledgerIDJpa2 = new LedgerIdJpa("test1@email.com");


        // Act & Assert:
        assertNotEquals(ledgerIDJpa1.hashCode(), ledgerIDJpa2.hashCode());
    }

    /**
     * Test for the getOwner method
     */

    @Test
    @DisplayName("getOwner method tested")
    void testGetOwner() {

        //Arrange:
        LedgerIdJpa ledgerIdJpa = new LedgerIdJpa("test@email.com");
        String expectedOwnerId = "test@email.com";

        //Act:
        String actualOwnerId = ledgerIdJpa.getOwner();

        // Assert:
        assertEquals(expectedOwnerId, actualOwnerId);
    }

    /**
     * Test for the setOwner method
     */

    @Test
    @DisplayName("getOwner method tested")
    void testSetOwner1() {

        //Arrange:
        LedgerIdJpa ledgerIdJpa = new LedgerIdJpa("test@email.com");
        String expectedOwner = "test1@email.com";

        //Act:
        ledgerIdJpa.setOwner(expectedOwner);

        String actualOwner = ledgerIdJpa.getOwner();

        //Assert:
        assertEquals(expectedOwner, actualOwner);
    }

    /**
     * Test for the empty constructor
     */

    @Test
    @DisplayName("testing empty constructor with setOwner")
    void testSetOwner2() {

        //Arrange:
        LedgerIdJpa ledgerIdJpa1 = new LedgerIdJpa();
        LedgerIdJpa ledgerIdJpa2 = new LedgerIdJpa("test@email.com");

        //Act:
        ledgerIdJpa1.setOwner("test@email.com");

        //Assert:
        assertEquals(ledgerIdJpa1,ledgerIdJpa2);
    }
}