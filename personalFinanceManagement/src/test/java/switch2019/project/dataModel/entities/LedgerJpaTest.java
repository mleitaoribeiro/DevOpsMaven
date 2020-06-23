package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerJpaTest {

    /**
     * Tests for the Equals method
     */

    @Test
    @DisplayName("Test Equals - Exactly The Same Object")
    void testEqualsExactlySameObject() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");

        // Act & Assert:
        assertEquals(ledgerJpa, ledgerJpa);

    }


    @Test
    @DisplayName("Test Equals -  Equal objects")
    void testEqualsEqualObjects() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa("test@gmail.com", "2019-12-10");

        // Act & Assert:
        assertEquals(ledgerJpa1, ledgerJpa2);
    }

    @Test
    @DisplayName("Test Equals -  Different owner")
    void testEqualsDifferentID1() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa("test2@gmail.com", "2019-12-10");

        //Act:
        boolean equalObjects = ledgerJpa1.equals(ledgerJpa2);

        // Assert:
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDifferentObject() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");
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
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        String ledgerJpa2 = null;

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
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa("test@gmail.com", "2019-12-10");


        // Act & Assert:
        assertEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode - ledgerIdJpa")
    void testDifferentHashCode1() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa("test@gmail2.com", "2019-12-10");


        // Act & Assert:
        assertNotEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode - creationDate")
    void testDifferentHashCode2() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa("test@gmail.com", "2019-12-10");
        LedgerJpa ledgerJpa2 = new LedgerJpa("test@gmail.com", "2020-1-10");


        // Act & Assert:
        assertNotEquals(ledgerJpa1.hashCode(), ledgerJpa2.hashCode());
    }

    /**
     * Tests for the getter methods
     */

    @Test
    @DisplayName ("Test getLedgerIdJpa method")
    void getLedgerIdJpaTest() {
        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");
        String expectedLedgerIdJpa = "test@gmail.com";

        //Act:
        String actualLedgerIdJpa = ledgerJpa.getOwner();

        //Assert:
        assertEquals(expectedLedgerIdJpa, actualLedgerIdJpa);
    }

    @Test
    @DisplayName ("Test getCreationDate method")
    void getCreationDateTest() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");
        String expectedCreationDate = "2019-12-10";

        //Act:
        String actualCreationDate = ledgerJpa.getCreationDate();

        //Assert:
        assertEquals(expectedCreationDate, actualCreationDate);
    }

    /**
     * Tests for the setter methods
     */

    @Test
    @DisplayName ("Test for the setLedgerIdJpa method")
    void setLedgerIdJpaTest() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("bla@gmail.com", "2020-5-10");
        String expectedLedgerIdJpa = "test@gmail.com";

        //Act:
        ledgerJpa.setOwner("test@gmail.com");

        //Assert:
        assertEquals(expectedLedgerIdJpa, ledgerJpa.getOwner());
    }

    @Test
    @DisplayName("Test for the setCreationDate method")
    void setCreationDateTest() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");
        String expectedCreationDate = "2020-12-10";

        //Act:
        ledgerJpa.setCreationDate("2020-12-10");

        //Assert:
        assertEquals(expectedCreationDate, ledgerJpa.getCreationDate());
    }

    @Test
    @DisplayName("test for the both Setter methods")
    void testBothSetters() {

        //Arrange:
        LedgerJpa ledgerJpa1 = new LedgerJpa();
        LedgerJpa ledgerJpa2 = new LedgerJpa("test@gmail.com", "2019-12-10");

        //Act:
        ledgerJpa1.setOwner("test@gmail.com");
        ledgerJpa1.setCreationDate("2019-12-10");

        //Assert:
        assertEquals(ledgerJpa2,ledgerJpa1);
    }

    /**
     * Test for the toString method
     */

    @Test
    @DisplayName("Test toString method")
    void toStringTest() {

        //Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("test@gmail.com", "2019-12-10");
        String expectedOutput = "test@gmail.com, 2019-12-10";

        //Act:
        String actualOutput = ledgerJpa.toString();

        //Assert:
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("Test getTransactionsFromLedgerJpa method")
    void getTransactionsFromLedgerJpa() {

        // Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("Switch", new DateAndTime().yearMonthDayToString());

        TransactionJpa transaction1 = new TransactionJpa(new LedgerJpa("SWITCH", "2020-05-26"),
                100.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "LIDL SUPERMARKET", "DEBIT");
        TransactionJpa transaction2 = new TransactionJpa(new LedgerJpa("SWITCH", "2020-05-26"),
                200.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "PINGO DOCE SUPERMARKET", "DEBIT");
        TransactionJpa transaction3 = new TransactionJpa(new LedgerJpa("SWITCH", "2020-05-26"),
                300.0, "EUR", "payment", "2020-01-13 11:00", "GROCERY",
                "BPI", "CONTINENTE SUPERMARKET", "DEBIT");

        ledgerJpa.addTransaction(transaction1);
        ledgerJpa.addTransaction(transaction2);
        ledgerJpa.addTransaction(transaction3);

        List<TransactionJpa> transactionsExpected = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        // Act:
        List<TransactionJpa> transactions = ledgerJpa.getTransactionsFromLedgerJpa();

        // Assert:
        assertEquals(transactionsExpected, transactions);
    }

    @Test
    @DisplayName("Test getTransactionsFromLedgerJpa method - Empty")
    void getTransactionsFromLedgerJpaEmpty() {

        // Arrange:
        LedgerJpa ledgerJpa = new LedgerJpa("James", new DateAndTime().yearMonthDayToString());

        List<TransactionJpa> transactionsExpected = new ArrayList<>(); // empty array

        // Act:
        List<TransactionJpa> transactions = ledgerJpa.getTransactionsFromLedgerJpa();

        // Assert:
        assertEquals(transactionsExpected, transactions);
    }
}