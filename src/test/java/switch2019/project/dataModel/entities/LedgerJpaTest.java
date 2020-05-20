package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerJpaTest {

    /**
     * Tests for the Equals method
     */

    @Test
    @DisplayName("Test Equals - Exactly The Same Object")
    void testEqualsExactlySameObject() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");

        // Act & Assert:
        assertEquals(ledgerJpa, ledgerJpa);

    }


    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");

        // Act & Assert:
        assertEquals(ledgerJpa1, ledgerJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentID1() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test2@gmail.com"), "2019-12-10");

        //Act:
        boolean equalObjects = ledgerJpa1.equals(ledgerJpa2);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test Equals -  Different creationDate")
    void testEqualsDifferentID2() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2020-1-10");

        //Act:
        boolean equalObjects = ledgerJpa1.equals(ledgerJpa2);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act:
        boolean equalObjects = ledgerJpa.equals(accountIDJpa);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerIdJpa ledgerJpa2 = null;

        //Act:
        boolean equalObjects = ledgerJpa1.equals(ledgerJpa2);

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
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");


        // Act & Assert:
        assertEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode - ledgerIdJpa")
    void testDifferentHashCode1() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test@gmail2.com"), "2019-12-10");


        // Act & Assert:
        assertNotEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode - creationDate")
    void testDifferentHashCode2() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa(new LedgerIdJpa("test@gmail.com"), "2020-1-10");


        // Act & Assert:
        assertNotEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }


}
