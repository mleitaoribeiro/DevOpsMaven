package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Currency;
import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {
    /**
     * Validate if a transaction was added to ledger list
     */

    @Test
    @DisplayName("Test for validating add a new transaction")
    void addTransactionToLedgerOneTransaction() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        Type type = new Type(true);
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, account2, type);
        Ledger ledger = new Ledger();

        //Act
        boolean result = ledger.addTransactionToLedger(transaction);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for validating for several new transactions")
    void addTransactionToLedgerTwoTransaction() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grossery");
        Category category2 = new Category("transport");
        Type type = new Type(true);
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction1 = new Transaction(monetaryValue, "payment", category1, account1, account2, type);
        Transaction transaction2 = new Transaction(monetaryValue, "payment", category2, account1, account2, type);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction1 = ledger.addTransactionToLedger(transaction1);
        boolean addedTransaction2 = ledger.addTransactionToLedger(transaction2);

        //Assert
        assertTrue(addedTransaction1 && addedTransaction2);
    }

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null monetaryValue")
    void addTransactionToLedgerNullTransactionNullMonetaryValue() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        Type type = new Type(true);
        Transaction transaction = new Transaction(null, "payment", category, account1, account2, type);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction = ledger.addTransactionToLedger(transaction);

        //Assert
        assertFalse(addedTransaction);
    }

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null description")
    void addTransactionToLedgerNullTransactionNullDescription() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        Type type = new Type(true);
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(monetaryValue, null, category, account1, account2, type);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction = ledger.addTransactionToLedger(transaction);

        //Assert
        assertFalse(addedTransaction);
    }

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null category")
    void addTransactionToLedgerNullTransactionNullCategory() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Type type = new Type(true);
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(monetaryValue, "payment", null, account1, account2, type);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction = ledger.addTransactionToLedger(transaction);

        //Assert
        assertFalse(addedTransaction);
    }

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null account")
    void addTransactionToLedgerNullTransactionNullAccount() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Category category = new Category("grocery");
        Type type = new Type(true);
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, null, type);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction = ledger.addTransactionToLedger(transaction);

        //Assert
        assertFalse(addedTransaction);
    }

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null type")
    void addTransactionToLedgerNullTransactionNullType() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(monetaryValue, "payment", category, account1, account2, null);
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction = ledger.addTransactionToLedger(transaction);

        //Assert
        assertFalse(addedTransaction);
    }
}