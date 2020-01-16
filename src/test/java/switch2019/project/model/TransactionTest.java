package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    /**
     * Tests to validate if a transaction was created
     */

    @Test
    @DisplayName("Test for validating transaction - sucess case")
    void isAValidTransactionTrue() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Category category =new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Type type = new Type(true);

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
        Category category =new Category("grocery");
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime date1 = LocalDateTime.parse("2019-04-12 20:10",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Type type1 = new Type(true);

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
        Type type = new Type(true);

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
        Type type = new Type(true);

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
        Type type = new Type(true);

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

        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, account2, null);

        //Act
        try {
            transaction.isAValidTransaction();
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The type can´t be null. Please try again.", description.getMessage());
        }
    }
}