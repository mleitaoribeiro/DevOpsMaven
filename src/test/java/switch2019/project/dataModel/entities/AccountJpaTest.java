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

        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");

        //Act & Assert

        assertEquals(accountJpa, accountJpa);
    }

    @Test
    @DisplayName("Test equals - Equal objects")
    void testEqualsSame() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertTrue(equalObjects);
    }


    @Test
    @DisplayName("Test equals - Different owner")
    void testEqualsDifferentOwner() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("Family", "Revolut", "Revolut Account", "0");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different denomination")
    void testEqualDifferentDenomination() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revo", "Revolut Account", "0");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different denomination")
    void testEqualDifferentDescription() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revos", "0");

        //Act

        boolean equalObjects = accountJpa1.equals(accountJpa2);

        // Assert
        assertTrue(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {

        //Arrange

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");

        //Act

        boolean equalObjects = accountJpa1.equals(null);

        // Assert
        assertFalse(equalObjects);
    }


    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDiferentObject() {

        //Arrange

        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
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

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");

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

        AccountJpa accountJpa1 = new AccountJpa("SWITCH", "Revolut", "Revolut Account", "0");
        AccountJpa accountJpa2 = new AccountJpa("Family", "Revolut", "Revolut Account", "0");

        //Act

        int accountJpa1HashCode = accountJpa1.hashCode();
        int accountJpa2Hashcode = accountJpa2.hashCode();

        // Assert
        assertNotEquals(accountJpa1HashCode,accountJpa2Hashcode);

    }


    @Test
    void getAccountIDJpa() {
    }

    @Test
    void setAccountIDJpa() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void getAmount() {
    }

    @Test
    void setBalance() {
    }

    @Test
    void getAccountKeyJpa() {
    }


}