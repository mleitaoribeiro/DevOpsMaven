package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionJpaTest {

    /**
     * Tests to Equals
     */

    @Test
    @DisplayName("Test equals - Exactly The Same Object")
    void testEqualsExactlyTheSameObject() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa( new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act & Assert

        assertEquals(transactionJpa, transactionJpa);
    }

    @Test
    @DisplayName("Test equals - Equal objects")
    void testEqualsSame() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa( new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        TransactionJpa transactionJpa2 = new TransactionJpa( new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act & Assert

        assertEquals(transactionJpa, transactionJpa2);
    }

    @Test
    @DisplayName("Test equals - Different ID")
    void testEqualsDifferentID() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switcher", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        TransactionJpa transactionJpa2 = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act & Assert

        assertNotEquals(transactionJpa, transactionJpa2);
    }

    @Test
    @DisplayName("Test equals - Different Ledger_ID")
    void testEqualDifferentLedger_ID() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        TransactionJpa transactionJpa2 = new TransactionJpa(new LedgerJpa("Simpsons","2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act & Assert

        assertNotEquals(transactionJpa, transactionJpa2);
    }

    @Test
    @DisplayName("Test equals - Null")
    void testEqualNull() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        //Act

        boolean equalObjects = transactionJpa.equals(null);

        // Assert
        assertFalse(equalObjects);
    }

    @Test
    @DisplayName("Test equals - Different object")
    void testEqualDiferentObject() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        AccountJpa accountJpa = new AccountJpa("SWITCH", "Revolut", "Revolut Account", 0.0, "EUR");

        //Act

        boolean equalObjects = transactionJpa.equals(accountJpa);

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
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        TransactionJpa transactionJpa2 = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        // Act & Assert
        assertEquals(transactionJpa.hashCode(), transactionJpa2.hashCode());
    }

    @Test
    @DisplayName("Test Hashcode - Different Hashcode")
    void testDifferentHashCode() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switcher", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        TransactionJpa transactionJpa2 = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        // Act & Assert
        assertNotEquals(transactionJpa.hashCode(), transactionJpa2.hashCode());
    }

    /**
     * Tests to Getters
     */

    @Test
    @DisplayName("Test get Transaction Ledger ID")
    void getTransactionLegderID() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedTransactionIDJpa = "Switch, 2020-5-12";

        //Act
        String realTransactionIDJpa = transactionJpa.getLedgerToString();

        // Assert
        assertEquals(expectedTransactionIDJpa, realTransactionIDJpa);

    }

    @Test
    @DisplayName("Test get Amount")
    void getAmount() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        Double expectedAmount = 10.0;

        //Act
        Double realAmount = transactionJpa.getAmount();

        // Assert
        assertEquals(expectedAmount, realAmount);

    }

    @Test
    @DisplayName("Test getCurrency")
    void getCurrency() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedCurrency = "euros";

        //Act
        String realCurrency = transactionJpa.getCurrency();

        // Assert
        assertEquals(expectedCurrency, realCurrency);

    }

    @Test
    @DisplayName("Test getDescription")
    void getDescription() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedDescription = "HomeShopping";

        //Act
        String realDescription = transactionJpa.getDescription();

        // Assert
        assertEquals(expectedDescription, realDescription);

    }

    @Test
    @DisplayName("Test getDate")
    void getDate() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedDate = "20-05-2020";
        //Act
        String realDate = transactionJpa.getDate();

        // Assert
        assertEquals(expectedDate, realDate);

    }

    @Test
    @DisplayName("Test getCategory")
    void getCategory() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedCategory = "shop";
        //Act
        String realCategory = transactionJpa.getCategory();

        // Assert
        assertEquals(expectedCategory, realCategory);

    }

    @Test
    @DisplayName("Test getAccountFrom")
    void getAccountFrom() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedAccountFrom = "bcp";
        //Act
        String realAccountFrom = transactionJpa.getAccountFrom();

        // Assert
        assertEquals(expectedAccountFrom, realAccountFrom);

    }

    @Test
    @DisplayName("Test getAccountTo")
    void getAccountTo() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedAccountTo = "bpi";
        //Act
        String realAccountTo = transactionJpa.getAccountTo();

        // Assert
        assertEquals(expectedAccountTo, realAccountTo);

    }

    @Test
    @DisplayName("Test getType")
    void getType() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedType = "true";
        //Act
        String realType = transactionJpa.getType();

        // Assert
        assertEquals(expectedType, realType);

    }

    /**
     * Tests to Setters
     */

    @Test
    @DisplayName("Test getTransactionLedgerID")
    void setTransactionLegderID() {

        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        LedgerJpa expectedTransactionIDJpa = new LedgerJpa("Switch","2020-5-12");

        //Act
        LedgerJpa realTransactionIDJpa = transactionJpa.getLedger();

        transactionJpa.setLedger(expectedTransactionIDJpa);

        // Assert
        assertEquals(expectedTransactionIDJpa, realTransactionIDJpa);

    }

    @Test
    @DisplayName("Test setAmount")
    void setAmount() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa( new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        Double expectedAmount = 10.0;

        //Act
        Double realAmount = transactionJpa.getAmount();

        transactionJpa.setAmount(expectedAmount);

        // Assert
        assertEquals(expectedAmount, realAmount);

    }

    @Test
    @DisplayName("Test setCurrency")
    void setCurrency() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedCurrency = "euros";

        //Act
        String realCurrency = transactionJpa.getCurrency();

        transactionJpa.setCurrency(expectedCurrency);

        // Assert
        assertEquals(expectedCurrency, realCurrency);

    }

    @Test
    @DisplayName("Test setDescription")
    void setDescription() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedDescription = "HomeShopping";

        //Act
        String realDescription = transactionJpa.getDescription();

        transactionJpa.setDescription(expectedDescription);

        // Assert
        assertEquals(expectedDescription, realDescription);

    }

    @Test
    @DisplayName("Test setDate")
    void setDate() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedDate = "20-05-2020";

        //Act
        String realDate = transactionJpa.getDate();

        transactionJpa.setDate(expectedDate);

        // Assert
        assertEquals(expectedDate, realDate);

    }

    @Test
    @DisplayName("Test setCategory")
    void setCategory() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedCategory = "shop";

        //Act
        String realCategory = transactionJpa.getCategory();

        transactionJpa.setCategory(expectedCategory);

        // Assert
        assertEquals(expectedCategory, realCategory);

    }

    @Test
    @DisplayName("Test setAccountFrom")
    void setAccountFrom() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedAccountFrom = "bcp";

        //Act
        String realAccountFrom = transactionJpa.getAccountFrom();

        transactionJpa.setAccountFrom(expectedAccountFrom);

        // Assert
        assertEquals(expectedAccountFrom, realAccountFrom);

    }

    @Test
    @DisplayName("Test setAccountTo")
    void setAccountTo() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa( new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedAccountTo = "bpi";

        //Act
        String realAccountTo = transactionJpa.getAccountTo();

        transactionJpa.setAccountTo(expectedAccountTo);

        // Assert
        assertEquals(expectedAccountTo, realAccountTo);

    }

    @Test
    @DisplayName("Test setType")
    void setType() {
        //Arrange
        TransactionJpa transactionJpa = new TransactionJpa(new LedgerJpa("Switch", "2020-5-12"), 10.0, "euros",
                "HomeShopping", "20-05-2020", "shop", "bcp", "bpi", "true");

        String expectedType = "true";

        //Act
        String realType = transactionJpa.getType();

        transactionJpa.setType(expectedType);

        // Assert
        assertEquals(expectedType, realType);

    }

}