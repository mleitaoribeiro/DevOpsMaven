package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {


    /**
     * Test to see if two transactions are the equals
     */

    @Test
    @DisplayName("Test if two transactions are the equals - true")

    public void testIfTwoTransactionsAreEqualsTrue() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - false")

    public void testIfTwoTransactionsAreEqualsFalse() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("bowling", "bowling NorteShopping");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = new Transaction(monetaryValue, "payment",null, category, account1, account3,false);


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two transactions are the equals - one is null")

    public void testIfTwoTransactionsAreEqualsOneIsNull() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");

        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment",null, category, account1, account2,false);
        Transaction transaction2 = null;


        //Act

        boolean result = transaction.equals(transaction2);

        //Assert
        assertEquals(false, result);
        }

        

        /**
         * Tests to validate if a transaction was created
         */

   /* @Test
    @DisplayName("Test for validating transaction - sucess case")
    void isAValidTransactionTrue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;

        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, account2, type);

        //Act
        boolean isTransactionValid = transaction.isAValidTransaction();

        //Assert
        assertTrue(isTransactionValid);
    }

    @Test
    @DisplayName("Test for validating transaction - with no automatic date - success case")
    void isAValidTransactionWithNoAutomaticDate() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime date1 = LocalDateTime.parse("2019-04-12 20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        boolean type1 = true;

        Transaction transaction = new Transaction(monetaryValue, "payment", date1, category, account1, account2, type1);

        //Act
        boolean isTransactionValid = transaction.isAValidTransaction();

        //Assert
        assertTrue(isTransactionValid);
    }

    @Test
    @DisplayName("Test for validating transaction - null monetary value")
    void isAValidTransactionFalseNullMonetaryValue() {
        //Arrange
        Category category = new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;

        Transaction transaction = new Transaction(null, "payment", category, account1, account2, type);

        //Act
        try {
            transaction.isAValidTransaction();
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null category")
    void isAValidTransactionFalseNullCategory() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;

        Transaction transaction = new Transaction(monetaryValue, "payment", null, account1, account2, type);

        //Act
        try {
            transaction.isAValidTransaction();
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null account")
    void isAValidTransactionFalseNullAccount() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category = new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        boolean type = true;

        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, null, type);

        //Act
        try {
            transaction.isAValidTransaction();
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating transaction - null type")
    void isAValidTransactionFalseNullType() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, account2,false);

        //Act
        try {
            transaction.isAValidTransaction();
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The type can´t be null. Please try again.", description.getMessage());
        }
    }*/
}