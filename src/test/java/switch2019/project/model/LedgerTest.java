package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction1, expectedTransaction2));
        //Act
        ArrayList<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2019, 2, 3, 10, 40));
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - EmptyLedger")
    void getLedgerTransactionsInPeriodEmptyLedger() {

        //Arrange
        Ledger ledger = new Ledger();

        ArrayList<Transaction> expected = new ArrayList<>();

        //Act
        ArrayList<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
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

        ArrayList<Transaction> expected = new ArrayList<>(Collections.singletonList(expectedTransaction1));
        //Act
        ArrayList<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2018, 10, 2, 9, 10),
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
        ArrayList<Transaction> expected = new ArrayList<>();
        //Act
        ArrayList<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2012, 10, 2, 9, 10),
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

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction1, expectedTransaction2));
        //Act
        ArrayList<Transaction> real = ledger.getTransactionsFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40),
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
            ledger.getTransactionsFromPeriod(LocalDateTime.of(2025, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid", getTransactionsFromPeriod.getMessage());
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
            ledger.getTransactionsFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid", getTransactionsFromPeriod.getMessage());
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
            ledger.getTransactionsFromPeriod(null, LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("The dates can´t be null", getTransactionsFromPeriod.getMessage());
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
            ledger.getTransactionsFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40), null);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("The dates can´t be null", getTransactionsFromPeriod.getMessage());
        }
    }

    /**
     * isTransactionInLedger tests
     */
    @DisplayName("test true")
    @Test
    void checkIfTransactionIsInsideALedger(){
        //Arrange:

        Ledger ledgerToCheck = new Ledger();
        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grocery");
        Transaction transaction1 = new Transaction(monetaryValue1,"payment",LocalDateTime.of(2020, 1, 14,13,05),category1,account1,account2,true);

        ledgerToCheck.addTransactionToLedger(monetaryValue1,"payment",LocalDateTime.of(2020, 1, 14,13,05),category1,account1,account2,true);

        //Act:
        boolean isTransactionInsideLedger = ledgerToCheck.isTransactionInLedger(transaction1);

        //Assert:
        assertTrue(isTransactionInsideLedger);
    }

    @DisplayName("test false")
    @Test
    void checkIfTransactionIsInsideALedger2(){
        //Arrange:

        Ledger ledgerToCheck = new Ledger();
        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(225, Currency.getInstance("EUR"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grocery");


        Transaction transaction1 = new Transaction(monetaryValue2,"payment",LocalDateTime.of(2020, 1, 14,13,05),category1,account1,account2,true);

        ledgerToCheck.addTransactionToLedger(monetaryValue1,"payment",LocalDateTime.of(2020, 1, 14,13,05),category1,account1,account2,true);

        //Act:
        boolean isTransactionInsideLedger = ledgerToCheck.isTransactionInLedger(transaction1);

        //Assert:
        assertFalse(isTransactionInsideLedger);
    }
}