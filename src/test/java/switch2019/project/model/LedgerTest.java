package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.legder.Ledger;
import switch2019.project.model.legder.Transaction;

import java.time.LocalDateTime;
import java.util.*;

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
     * US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo num dado período.
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

        List<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction2, expectedTransaction1));
        //Act
        List<Transaction> real = ledger.getTransactionsInDateRange(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2019, 2, 3, 10, 40));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - EmptyLedger")
    void getLedgerTransactionsInPeriodEmptyLedger() {

        //Arrange
        Ledger ledger = new Ledger();

        List<Transaction> expected = new ArrayList<>();

        //Act
        List<Transaction> real = ledger.getTransactionsInDateRange(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2017, 12, 3, 10, 40));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Same date")
    void getLedgerTransactionsInPeriodSameDay() {
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

        List<Transaction> expected = new ArrayList<>(Collections.singletonList(expectedTransaction1));
        //Act
        List<Transaction> real = ledger.getTransactionsInDateRange(LocalDateTime.of(2018, 10, 2, 9, 10),
                LocalDateTime.of(2018, 10, 2, 9, 10));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - No transactions on the given date")
    void getLedgerTransactionsInPeriodNoTransactions() {
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
        List<Transaction> expected = new ArrayList<>();
        //Act
        List<Transaction> real = ledger.getTransactionsInDateRange(LocalDateTime.of(2012, 10, 2, 9, 10),
                LocalDateTime.of(2013, 10, 2, 9, 10));
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

        List<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction2, expectedTransaction1));
        //Act
        List<Transaction> real = ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                LocalDateTime.of(2017, 10, 2, 9, 20));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Actual Date")
    void getLedgerTransactionsInPeriodInitialDateSuperiorActualDate() {
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

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2025, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final  Date > Actual Date")
    void getLedgerTransactionsInPeriodFinalDateSuperiorActualDate() {
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

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initial Date null")
    void getLedgerTransactionsInPeriodInitialDateNull() {
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

        //Act
        try {
            ledger.getTransactionsInDateRange(null, LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The dates can´t be null", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final Date null")
    void getLedgerTransactionsInPeriodFinalDateNull() {
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

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40), null);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The dates can´t be null", getTransactionsInDateRange.getMessage());
        }
    }

    /**
     * isTransactionInLedger tests
     */
    @DisplayName("test true")
    @Test
    void checkIfTransactionIsInsideALedger() {
        //Arrange:

        Ledger ledgerToCheck = new Ledger();
        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grocery");
        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account2, true);

        ledgerToCheck.addTransactionToLedger(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account2, true);

        //Act:
        boolean isTransactionInsideLedger = ledgerToCheck.isTransactionInLedger(transaction1);

        //Assert:
        assertTrue(isTransactionInsideLedger);
    }

    @DisplayName("test false")
    @Test
    void checkIfTransactionIsInsideALedger2() {
        //Arrange:

        Ledger ledgerToCheck = new Ledger();
        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(225, Currency.getInstance("EUR"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grocery");


        Transaction transaction1 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account2, true);

        ledgerToCheck.addTransactionToLedger(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account2, true);

        //Act:
        boolean isTransactionInsideLedger = ledgerToCheck.isTransactionInLedger(transaction1);

        //Assert:
        assertFalse(isTransactionInsideLedger);
    }

    @Test
    @DisplayName("Sort Transactions in ASC by date")
    void sortTransactionsInAscendingOrderByDate() {
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
        Transaction expectedTransaction3 = new Transaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction3, expectedTransaction1, expectedTransaction2));
        //Act
        ledger.sortLedgerByTransactionDateAscending();
        //Assert
        assertEquals(expected, ledger.getLedgerTransactions());
    }

    @Test
    @DisplayName("Sort Transactions in DESC by date")
    void sortTransactionsInDescendingOrderByDate() {
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
        Transaction expectedTransaction3 = new Transaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction2, expectedTransaction1, expectedTransaction3));
        //Act
        ledger.sortLedgerByTransactionDateDescending();
        //Assert
        assertEquals(expected, ledger.getLedgerTransactions());
    }


    @Test
    @DisplayName("Get movements from one account - Success Case")
    void getMovementsFromOneAccountSuccessCase() {
        //Arrange
        Ledger ledger = new Ledger();
        Account account = new Account("Millenium", "Only for Groceries");

        //Arrange-Transaction1
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);

        //Arrange-Transaction2
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 2, 14, 11),
                new Category("grocery"), new Account("BNI", "General"),
                new Account("Millenium", "Only for Groceries"),
                false);
        Transaction transaction2 = new Transaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 2, 14, 11),
                new Category("grocery"), new Account("BNI", "General"),
                new Account("Millenium", "Only for Groceries"),
                false);

        //Arrange-Transaction3
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);
        Transaction transaction3 = new Transaction(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        List<Transaction> allTransactions = new ArrayList<>(Arrays.asList(transaction1,transaction2,transaction3));
        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2));

        //Act
        List<Transaction> listOfTransactions = ledger.getTransactionsFromOneAccount(account, allTransactions);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Get movements from one account - Null Account")
    void getMovementsFromOneAccountNullAccount() {
        //Arrange
        Ledger ledger = new Ledger();
        Account account = null;

        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);

        List<Transaction> allTransactions = new ArrayList<>(Arrays.asList(transaction1));

        //Act
        try {
            ledger.getTransactionsFromOneAccount(account, allTransactions);
        }

        //Assert
        catch (IllegalArgumentException getMovementsFromOneAccount) {
            assertEquals("The account can't be null", getMovementsFromOneAccount.getMessage());
        }
    }

    @Test
    @DisplayName("Get movements from one account - Account without movements")
    void getMovementsFromOneAccountAccountWithoutMovements() {
        //Arrange
        Ledger ledger = new Ledger();
        Account account = new Account("CaixaGeral", "General");

        //Arrange-Transaction1
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);

        List<Transaction> allTransactions = new ArrayList<>(Arrays.asList(transaction1));
        List<Transaction> expectedTransactions = new ArrayList<>();

        //Act
        List<Transaction> listOfTransactions = ledger.getTransactionsFromOneAccount(account, allTransactions);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    /**
     * * US017/18 - Get the balance of the transactions given a specific date range
     */

    @Test
    @DisplayName("Get the balance of transactions over a valid date range - Main Scenario of US17")
    void getPersonalBalanceInDateRange() {
        //Arrange
        Ledger ledger = new Ledger();

        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                true);
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 1, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);

        double expectedPersonalBalanceFromDateRange = -55.4;

        //Act
        double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of transactions for one day - valid day")
    void getBalanceForGivenDay() {
        //Arrange
        Ledger ledger = new Ledger();
        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(250, Currency.getInstance("EUR")), "Hostel Barcelona",
                LocalDateTime.of(2020, 1, 13, 13, 05),
                new Category("grocery"), new Account("Revolut", "For trips expenses"),
                new Account("Friends & Company", "Holidays"),
                true);
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "Pack of Super Bock",
                LocalDateTime.of(2020, 1, 13, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(60, Currency.getInstance("EUR")), "Car Gas",
                LocalDateTime.of(2020, 1, 18, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 13, 23, 59);

        double expectedPersonalBalanceFromDateRange = 230;

        //Act
        double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }


    @Test
    @DisplayName("Get the balance of transactions over a valid date range but initial date and final date not in order")
    void getBalanceInDateRangeWithDatesDisordered() {
        //Arrange
        Ledger ledger = new Ledger();
        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);
        LocalDateTime initialDate = LocalDateTime.of(2019, 12, 31, 00, 00);

        double expectedPersonalBalanceFromDateRange = -95.4;

        //Act
        double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance transactions over invalid date range - final date higher than today!")
    void getBalanceInDateRangeWithNotValidDate() {
        //Arrange
        Ledger ledger = new Ledger();
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance transactions over invalid date range - inicial date null")
    void getBalanceInDateRangeWithNotValidDateInitialDateNull() {
        //Arrange
        Ledger ledger = new Ledger();
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = null;
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance transactions over invalid date range - final date null")
    void getBalanceInDateRangeWithNotValidDateFinalDateNull() {
        //Arrange
        Ledger ledger = new Ledger();
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2021, 1, 27, 00, 00);
        LocalDateTime finalDate = null;


        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of ledger that is empty!")
    void getBalanceInDateRangeEmptyBalance() {
        //Arrange
        Ledger ledger = new Ledger();

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger is Empty.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions over invalid date range - final date higher than today!")
    void getBalanceInDateRangeWithInvalidDate() {
        //Arrange
        Ledger ledger = new Ledger();
        //Init Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my ledger that has zero transactions")
    void getBalanceInDateRangeOfEmptyLedger() {
        //Arrange
        Ledger ledger = new Ledger();

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger is Empty.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions over a period with zero transactions in date range")
    void getBalanceInDateRangeEmptyBalanceOverDateRange() {
        //Arrange
        Ledger ledger = new Ledger();
        //Init Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        //Act
        double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);

        // Assert
        assertEquals(0, personalBalanceInDateRange);
    }

    /**
     * tests for size of the Ledger.
     */

    @Test
    @DisplayName("Test for validating add a new transaction")
    void addTransactionToLedgerChangeSize() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();

        //Act
        int sizeBefore = ledger.getLedgerSize();
        ledger.addTransactionToLedger(monetaryValue, "payment", null, category, account1, account2, type);
        int sizeAfter = ledger.getLedgerSize();

        //Assert
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    /**
     * Test - Method To String
     */

    @Test
    @DisplayName("Test toString() Method")
    void testToStringMethod() {
        //Arrange
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        boolean type = true;
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        Ledger ledger = new Ledger();
        LocalDateTime date = LocalDateTime.of(2019, 10, 27, 00, 00);
        ledger.addTransactionToLedger(monetaryValue, "payment", date, category, account1, account2, type);

        String expected = "Ledger:[2019-10-27 00:00 | 200.0 EUR CREDIT | MERCEARIA -> TRANSPORTE | Description: \"payment\"  | GROCERY].";
        //Act

        String real = ledger.toString();

        //Assert
        assertEquals(expected, real);

    }

    @Test
    @DisplayName("Get the Ledger Size")
    void getLedgerSize() {

        //Arrange
        Ledger ledger = new Ledger();

        //Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        //Act
        int ledgerSize = ledger.getLedgerSize();

        // Assert
        assertEquals(3, ledgerSize);
    }

    @Test
    @DisplayName("Get the Ledger Size - Empty")
    void getLedgerSizeEmpty() {

        //Arrange
        Ledger ledger = new Ledger();

        //Act
        int ledgerSize = ledger.getLedgerSize();

        // Assert
        assertEquals(0, ledgerSize);
    }

}