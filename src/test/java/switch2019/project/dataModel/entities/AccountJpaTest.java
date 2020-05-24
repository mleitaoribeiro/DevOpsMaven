package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountJpaTest {


    /**
     * Tests to Equals
     */

    @Test
    @DisplayName("Test equals - Exactly The Same Object")
    void testEqualsExactlyTheSameObject() {

        //Arrange

        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act & Assert

        assertEquals(accountJpa, accountJpa);
    }

    @Test
    @DisplayName("Test equals - Equal objects")
    void testEqualsSame() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertTrue(equalObjects);
    }


    @Test
    @DisplayName("Test equals - Different owner")
    void testEqualsDifferentOwner() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("Family", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different denomination")
    void testEqualDifferentDenomination() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revo", "Revolut Account", 0.0, "EUR");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different denomination")
    void testEqualDifferentDescription() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revos", 0.0, "EUR");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertTrue(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        boolean equalObjects = accountJpa1.equals(null);

        // Assert
        assertFalse(equalObjects);
    }


    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDiferentObject() {

        //Arrange

        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountIDJpa accountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act

        boolean equalObjects = accountJpa.equals(accountIDJpa);

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

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        int accountJpa1HashCode = accountJpa1.hashCode();
        int accountJpa2Hashcode = accountJpa2.hashCode();

        // Assert
        assertEquals(accountJpa1HashCode,accountJpa2Hashcode);

    }


    @Test
    @DisplayName("Test Hashcode - Different Hashcode")
    void testDifferentHashCode() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");
        AccountJpa accountJpa2 = new AccountJpa("Family", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        int accountJpa1HashCode = accountJpa1.hashCode();
        int accountJpa2Hashcode = accountJpa2.hashCode();

        // Assert
        assertNotEquals(accountJpa1HashCode,accountJpa2Hashcode);

    }

    /**
     * Tests to Getters
     */


    @Test
    @DisplayName("Test getAccountIDJpa ")
    void getAccountIDJpa() {

        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        AccountIDJpa expectedAccountIDJpa = new AccountIDJpa("SWITCH", "Revolut");

        //Act
        AccountIDJpa realAccountIDJpa = accountJpa.getAccountIDJpa();

        // Assert
        assertEquals(expectedAccountIDJpa, realAccountIDJpa);

    }

    @Test
    @DisplayName("Test getDescription")
    void getDescription() {

        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        String expectedDescription = "Revolut Account";

        //Act
        String realDescription = accountJpa.getDescription();

        // Assert
        assertEquals(expectedDescription, realDescription);

    }

    @Test
    @DisplayName("Test getAmount")
    void getAmount() {
        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        double expectAmount = 0;

        //Act
        double realAmount = accountJpa.getAmount();

        // Assert
        assertEquals(expectAmount, realAmount);

    }

    @Test
    @DisplayName("Test getCurrency")
    void getCurrency() {

        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        String expectedBalance = "EUR";

        //Act
        String realBalance = accountJpa.getCurrency();

        // Assert
        assertEquals(expectedBalance, realBalance);
    }

    /**
     * Tests to Setters
     */

    @Test
    @DisplayName("Test setAccountIDJpa")
    void setAccountIDJpa() {
        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        AccountIDJpa expectedAccountIDJpa = new AccountIDJpa("SWITCH", "Revo");

        //Act

        accountJpa.setAccountIDJpa(expectedAccountIDJpa);

        AccountIDJpa realAccountIDJpa = accountJpa.getAccountIDJpa();

        // Assert
        assertEquals(expectedAccountIDJpa, realAccountIDJpa);

    }


    @Test
    @DisplayName("Test setDescription")
    void setDescription() {
        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        String expectedDescription = "Revo Acc";

        //Act
        accountJpa.setDescription(expectedDescription);

        String realDescription = accountJpa.getDescription();

        // Assert
        assertEquals(expectedDescription, realDescription);
    }

    @Test
    @DisplayName("Test setAmount")
    void setAmount() {
        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        double expectedBalance = 1;

        //Act
        accountJpa.setAmount(expectedBalance);

        double realBalance = accountJpa.getAmount();

        // Assert
        assertEquals(expectedBalance, realBalance);

    }

    @Test
    @DisplayName("Test setCurrency")
    void setCurrency() {
        //Arrange
        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        String expectedCurrency = "USD";

        //Act
        accountJpa.setCurrency(expectedCurrency);

        String realBalance = accountJpa.getCurrency();

        // Assert
        assertEquals(expectedCurrency, realBalance);

    }
}