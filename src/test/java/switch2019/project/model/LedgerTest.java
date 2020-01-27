package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

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
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        boolean result = ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, type);

        //Assert
        assertTrue(result);
    }

    /**
     * Validate if two transactions were added to ledger list
     */

    @Test
    @DisplayName("Test for validating for several new transactions")
    void addTransactionToLedgerTwoTransaction() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grossery");
        Category category2 = new Category("transport");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        boolean addedTransaction1 = ledger.addTransactionToLedger(monetaryValue, "payment", null, category1, account1, account2, type);
        boolean addedTransaction2 = ledger.addTransactionToLedger(monetaryValue, "payment", null, category2, account1, account2, type);

        //Assert
        assertTrue(addedTransaction1 && addedTransaction2);
    }

    /**
     * Validate if a transaction was added to ledger list
     * null monetary value
     */

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null monetaryValue")
    void addTransactionToLedgerTransactionNullMonetaryValue() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        boolean type = true;
        Ledger ledger = new Ledger();

        //Act
        try {
            ledger.addTransactionToLedger(null, "payment", null, category, account1, account2, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    /**
     * Validate if a transaction was added to ledger list
     * null description
     */

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null description")
    void addTransactionToLedgerTransactionNullDescription() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, null, null, category, account1, account2, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null. Please try again.", description.getMessage());
        }
    }

    /**
     * Validate if a transaction was added to ledger list
     * null category
     */

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null category")
    void addTransactionToLedgerTransactionNullCategory() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, "payment", null, null, account1, account2, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category can´t be null. Please try again.", description.getMessage());
        }
    }

    /**
     * Validate if a transaction was added to ledger list
     * null account
     */

    @Test
    @DisplayName("Test for validate if ledger is not adding invalid transactions - null account")
    void addTransactionToLedgerTransactionNullAccount() {

        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Category category = new Category("grocery");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, null, type);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts can´t be null. Please try again.", description.getMessage());
        }
    }

    /**
     * US011/US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo num dado período.
     */

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Success Case")
    void getLedgerTransactionsInPeriod() {
        //Arrange
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        Ledger ledger = new Ledger();

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        ledger.addTransactionToLedger(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        ledger.addTransactionToLedger(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        HashSet<Transaction> expected = new HashSet<>(Arrays.asList(expectedTransaction1, expectedTransaction2));
        //Act
        HashSet<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2019, 2, 3, 10, 40));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - EmptyLedger")
    void getLedgerTransactionsInPeriodEmptyLedger() {
        //Arrange
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        Ledger ledger = new Ledger();

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        ledger.addTransactionToLedger(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        ledger.addTransactionToLedger(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);


        HashSet<Transaction> expected = new HashSet<>();
        //Act
        HashSet<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2017, 12, 3, 10, 40));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Final Date")
    void getLedgerTransactionsInPeriodFalse() {
        //Arrange
        Account oneAccount = new Account("myxpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        Account anotherAccount = new Account("abc", "abc Account");

        Category oneCategory = new Category("ASD");
        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime oneLocalDate = LocalDateTime.of(2018, 10, 2, 9, 10);
        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        Ledger ledger = new Ledger();

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        ledger.addTransactionToLedger(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        ledger.addTransactionToLedger(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        HashSet<Transaction> expected = new HashSet<>(Arrays.asList(expectedTransaction1, expectedTransaction2));
        //Act
        HashSet<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40),
                LocalDateTime.of(2017, 10, 2, 9, 20));

        //Assert
        assertEquals(expected, real);
    }

}