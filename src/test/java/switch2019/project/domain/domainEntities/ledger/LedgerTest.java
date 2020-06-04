package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

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
        AccountID account1 = new AccountID(new Denomination("mercearia"),
               new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        Transaction expected = new Transaction(monetaryValue, new Description("payment"),
                null, category, account1, account2, new Type(true));

        //Act
        Transaction result = ledger.addTransactionToLedger(monetaryValue, new Description("payment"),
                null, category, account1, account2, new Type(true));

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Validate if two transactions were added to ledger list
     */

    @Test
    @DisplayName("Test for validating for several new transactions")
    void addTransactionToLedgerTwoTransaction() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category1 = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        CategoryID category2 = new CategoryID(new Denomination("transport"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        Transaction expectedTransaction1 = new Transaction(monetaryValue, new Description("payment"),
                null, category1, account1, account2, new Type(true));
        Transaction expectedTransaction2 = new Transaction( monetaryValue, new Description("payment"),
                null, category2, account1, account2, new Type(true));
        //Act
        Transaction addedTransaction1 = ledger.addTransactionToLedger(monetaryValue, new Description("payment"),
                null, category1, account1, account2, new Type(true));
        Transaction addedTransaction2 = ledger.addTransactionToLedger(monetaryValue, new Description("payment"),
                null, category2, account1, account2, new Type(true));

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedTransaction1,addedTransaction1),
                () -> assertEquals(expectedTransaction2,addedTransaction2)
        );
    }

    /**
     * Validate if a transaction was added to ledger list
     * null monetary value
     */

    @Test
    @DisplayName("Test for validating ledger not adding invalid transactions - null monetaryValue")
    void addTransactionToLedgerTransactionNullMonetaryValue() {

        //Arrange
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        try {
            ledger.addTransactionToLedger(null, new Description("payment"), null,
                    category, account1, account2, new Type(true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value cannot be null.", description.getMessage());
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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, null, null, category, account1, account2, new Type(true));
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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, new Description("payment"), null,
                    null, account1, account2, new Type(true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category cannot be null.", description.getMessage());
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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        try {
            ledger.addTransactionToLedger(monetaryValue, new Description("payment"), null,
                    category, account1, null, new Type(true));
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The accounts cannot be null.", description.getMessage());
        }
    }

    /**
     * US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo num dado período.
     */

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Success Case")
    void getLedgerTransactionsInPeriod() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, new Description("payment"),
                oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, new Description("xpto"),
                otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));

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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

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
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, new Description("payment"),
                oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));

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
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

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
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, new Description("payment"),
                oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, new Description("xpto"),
                otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));

        //Act

        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("One of the specified dates is not valid.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initial Date > Actual Date")
    void getLedgerTransactionsInPeriodInitialDateSuperiorActualDate() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2025, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("One of the specified dates is not valid.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final  Date > Actual Date")
    void getLedgerTransactionsInPeriodFinalDateSuperiorActualDate() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("One of the specified dates is not valid.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initial Date null")
    void getLedgerTransactionsInPeriodInitialDateNull() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Act
        try {
            ledger.getTransactionsInDateRange(null, LocalDateTime.of(2030, 10, 2, 9, 20));
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The specified dates cannot be null.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final Date null")
    void getLedgerTransactionsInPeriodFinalDateNull() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Act
        try {
            ledger.getTransactionsInDateRange(LocalDateTime.of(2019, 2, 3, 10, 40), null);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The specified dates cannot be null.", getTransactionsInDateRange.getMessage());
        }
    }

    /**
     * isTransactionInLedger tests
     */
    @DisplayName("test true")
    @Test
    void checkIfTransactionIsInsideALedger() {

        //Arrange:
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category1 = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));
        Transaction transaction1 = new Transaction(monetaryValue1, new Description("payment"),
                new DateAndTime(2020, 1, 14, 13, 05), category1, account1, account2, new Type(true));

        ledger.addTransactionToLedger(monetaryValue1, new Description("payment"),
                new DateAndTime(2020, 1, 14, 13, 05), category1, account1, account2, new Type(true));

        //Act:
        boolean isTransactionInsideLedger = ledger.isTransactionInLedger(transaction1);

        //Assert:
        assertTrue(isTransactionInsideLedger);
    }

    @DisplayName("test false")
    @Test
    void checkIfTransactionIsInsideALedger2() {

        //Arrange:
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue monetaryValue1 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(225, Currency.getInstance("EUR"));
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category1 = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));


        Transaction transaction1 = new Transaction(monetaryValue2, new Description("payment"),
                new DateAndTime(2020, 1, 14, 13, 05), category1, account1, account2, new Type(true));

        ledger.addTransactionToLedger(monetaryValue1, new Description("payment"),
                new DateAndTime(2020, 1, 14, 13, 05), category1, account1, account2, new Type(true));

        //Act:
        boolean isTransactionInsideLedger = ledger.isTransactionInLedger(transaction1);

        //Assert:
        assertFalse(isTransactionInsideLedger);
    }

    @Test
    @DisplayName("Sort Transactions in ASC by date")
    void sortTransactionsInAscendingOrderByDate() {

        //Arrange
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, new Description("payment"),
                oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, new Description("xpto"),
                otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        Transaction expectedTransaction3 = new Transaction(oneMonetaryValue, new Description("abc"),
                anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        AccountID oneAccount = new AccountID(new Denomination("myxpto"), new PersonID(new Email("personEmail@email.pt")));
        AccountID otherAccount = new AccountID(new Denomination("xyz"), new PersonID(new Email("personEmail@email.pt")));
        AccountID anotherAccount = new AccountID(new Denomination("abc"), new PersonID(new Email("personEmail@email.pt")));

        CategoryID oneCategory = new CategoryID(new Denomination("ASD"), new PersonID(new Email("personEmail@email.com")));
        CategoryID otherCategory = new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        DateAndTime oneLocalDate = new DateAndTime(2018, 10, 2, 9, 10);
        DateAndTime otherLocalDate = new DateAndTime(2019, 1, 2, 10, 40);
        DateAndTime anotherLocalDate = new DateAndTime(2015, 10, 2, 10, 40);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Add Transactions to Ledger
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("payment"), oneLocalDate,
                oneCategory, oneAccount, otherAccount, new Type(true));
        ledger.addTransactionToLedger(otherMonetaryValue, new Description("xpto"), otherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(false));
        ledger.addTransactionToLedger(oneMonetaryValue, new Description("abc"), anotherLocalDate,
                otherCategory, anotherAccount, oneAccount, new Type(true));

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, new Description("payment"),
                oneLocalDate, oneCategory, oneAccount, otherAccount, new Type(true));
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, new Description("xpto"),
                otherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(false));
        Transaction expectedTransaction3 = new Transaction(oneMonetaryValue, new Description("abc"),
                anotherLocalDate, otherCategory, anotherAccount, oneAccount, new Type(true));

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
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account = new AccountID(new Denomination("Millenium"), person.getID());

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Arrange-Transaction1
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")),
                new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), person.getID()),
                new AccountID(new Denomination("Millenium"), person.getID()),
                new AccountID(new Denomination("Continente"), person.getID()),
                new Type(false));
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")),
                new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), person.getID()),
                new AccountID(new Denomination("Millenium"), person.getID()),
                new AccountID(new Denomination("Continente"), person.getID()),
                new Type(false));

        //Arrange-Transaction2
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")),
                new Description("schweppes"),
                new DateAndTime(2020, 1, 2, 14, 11),
                new CategoryID(new Denomination("grocery"), person.getID()),
                new AccountID(new Denomination("BNI"), person.getID()),
                new AccountID(new Denomination("Millenium"), person.getID()),
                new Type(false));
        Transaction transaction2 = new Transaction(new MonetaryValue(5.4, Currency.getInstance("EUR")),
                new Description("schweppes"),
                new DateAndTime(2020, 1, 2, 14, 11),
                new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("BNI"), person.getID()),
                new AccountID(new Denomination("Millenium"), person.getID()),
                new Type(false));

        //Arrange-Transaction3
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("car gas"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), person.getID()),
                new AccountID(new Denomination("BP"), person.getID()),
                new Type(false));
        Transaction transaction3 = new Transaction(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("car gas"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), person.getID()),
                new AccountID(new Denomination("BP"), person.getID()),
                new Type(false));

        List<Transaction> allTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));
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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);
        AccountID account = null;

        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("QWERTY"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"),
                new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        List<Transaction> allTransactions = new ArrayList<>(Arrays.asList(transaction1));

        //Act
        try {
            ledger.getTransactionsFromOneAccount(account, allTransactions);
        }

        //Assert
        catch (IllegalArgumentException getMovementsFromOneAccount) {
            assertEquals("The account cannot be null.", getMovementsFromOneAccount.getMessage());
        }
    }

    @Test
    @DisplayName("Get movements from one account - Account without movements")
    void getMovementsFromOneAccountAccountWithoutMovements() {

        //Arrange
        Person person = new Person("Jose", new DateAndTime(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        AccountID account = new AccountID(new Denomination("CaixaGeral"), person.getID());

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Arrange-Transaction1
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), person.getID()), new AccountID(new Denomination("Millenium"), person.getID()),
                new AccountID(new Denomination("Continente"), person.getID()),
                new Type(false));
        Transaction transaction1 = new Transaction(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), person.getID()), new AccountID(new Denomination("Millenium"), person.getID()),
                new AccountID(new Denomination("Continente"), person.getID()),
                new Type(false));

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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"),
                        new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(true));
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("car gas"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"),
                       new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(250, Currency.getInstance("EUR")), new Description("Hostel Barcelona"),
                new DateAndTime(2020, 1, 13, 13, 05),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Revolut"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Friends & Company"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(true));
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("Pack of Super Bock"),
                new DateAndTime(2020, 1, 13, 14, 11),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(60, Currency.getInstance("EUR")), new Description("Car Gas"),
                new DateAndTime(2020, 1, 18, 17, 23),
                new CategoryID(new Denomination("general"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"),
                        new PersonID(new Email("personEmail@email.pt"))), new Type(false));

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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Init Transactions
        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"),  new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(5.4, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

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
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"),new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            double personalBalanceInDateRange = ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the specified dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance transactions over invalid date range - inicial date null")
    void getBalanceInDateRangeWithNotValidDateInitialDateNull() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        LocalDateTime initialDate = null;
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the specified dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance transactions over invalid date range - final date null")
    void getBalanceInDateRangeWithNotValidDateFinalDateNull() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        ledger.addTransactionToLedger(new MonetaryValue(20, Currency.getInstance("EUR")), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger(new MonetaryValue(70, Currency.getInstance("EUR")), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2021, 1, 27, 00, 00);
        LocalDateTime finalDate = null;


        try {
            //Act
            ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the specified dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of ledger that is empty!")
    void getBalanceInDateRangeEmptyBalance() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        try {
            //Act
            ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger has no Transactions.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions over invalid date range - final date higher than today!")
    void getBalanceInDateRangeWithInvalidDate() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Init Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"),
                  new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium")
                        , new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);

        try {
            //Act
            ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the specified dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my ledger that has zero transactions")
    void getBalanceInDateRangeOfEmptyLedger() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        try {
            //Act
            ledger.getBalanceInDateRange(initialDate, finalDate);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger has no Transactions.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my own transactions over a period with zero transactions in date range")
    void getBalanceInDateRangeEmptyBalanceOverDateRange() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Init Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"),new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        int sizeBefore = ledger.getLedgerSize();
        ledger.addTransactionToLedger(monetaryValue, new Description("payment"), null,
                category, account1, account2, new Type(true));
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
        AccountID account1 = new AccountID(new Denomination("mercearia"), new PersonID(new Email("personEmail@email.pt")));
        AccountID account2 = new AccountID(new Denomination("transporte"), new PersonID(new Email("personEmail@email.pt")));
        CategoryID category = new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com")));

        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        DateAndTime date = new DateAndTime(2019, 10, 27, 00, 00);

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        ledger.addTransactionToLedger(monetaryValue, new Description("payment"), date, category,
                account1, account2, new Type(true));

        String expected = "Ledger:[2019-10-27 00:00 | 200.0 EUR CREDIT | MERCEARIA -> TRANSPORTE | Description: \"PAYMENT\"  | GROCERY].";
        //Act

        String real = ledger.toString();

        //Assert
        assertEquals(expected, real);

    }

    @Test
    @DisplayName("Get the Ledger Size")
    void getLedgerSize() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Transactions
        ledger.addTransactionToLedger((new MonetaryValue(20, Currency.getInstance("EUR"))), new Description("2 pacs of Gurosan"),
                new DateAndTime(2020, 1, 1, 13, 05),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(5.4, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 1, 14, 11),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("Millenium"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("Continente"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));
        ledger.addTransactionToLedger((new MonetaryValue(70, Currency.getInstance("EUR"))), new Description("schweppes"),
                new DateAndTime(2020, 1, 5, 17, 23),
                new CategoryID(new Denomination("grocery"), new PersonID(new Email("personEmail@email.com"))),
                new AccountID(new Denomination("CGD"), new PersonID(new Email("personEmail@email.pt"))),
                new AccountID(new Denomination("BP"), new PersonID(new Email("personEmail@email.pt"))),
                new Type(false));

        //Act
        int ledgerSize = ledger.getLedgerSize();

        // Assert
        assertEquals(3, ledgerSize);
    }

    @Test
    @DisplayName("Schedule a Transaction")
    void scheduleNewTransaction() throws InterruptedException {

        // Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);
        int before = ledger.getLedgerSize();

        // Act
        boolean newTransactionScheduled = ledger.scheduleNewTransaction(new Periodicity("daily"),
                new MonetaryValue(5, Currency.getInstance("EUR")),
                new Description("lunch at work"), new DateAndTime(2020, 1, 1, 13, 5),
                        new CategoryID(new Denomination("food"), new PersonID(new Email("marta@email.com"))),
                        new AccountID(new Denomination("Millenium"),
                        new PersonID(new Email("marta@email.pt"))), new AccountID(new Denomination("Continente"),
                        new PersonID(new Email("marta@email.pt"))), new Type(false));

        Thread.sleep(2400); // daily = 500
        int after = ledger.getLedgerSize();

        //Assert
        assertEquals(0, before);
        assertEquals(5, after);
        assertTrue(newTransactionScheduled);

    }

    @Test
    @DisplayName("Get the Ledger Size - Empty")
    void getLedgerSizeEmpty() {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        //Act
        int ledgerSize = ledger.getLedgerSize();

        // Assert
        assertEquals(0, ledgerSize);
    }

    @Test
    @DisplayName("hashcode test equal ledger")
    void equalsTestHashcode(){
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);
        Ledger ledger1 = new Ledger(ownerID);

        boolean resultHash = ledger.hashCode() == ledger1.hashCode();

        assertTrue(resultHash);
    }

    @Test
    @DisplayName("Equals test same object")
    void equalsTestSameObj(){
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        boolean resultTrue = ledger.equals(ledger);

        assertTrue(resultTrue);

    }

    @Test
    @DisplayName("Equals test, instance of")
    void equalsTestInstance(){
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        boolean result = ledger.equals(ownerID);

        assertFalse(result);

    }

    @Test
    @DisplayName("hashcode test equal ledger")
    void equalsTestHashcodeFalse(){
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);
        Ledger ledger1 = new Ledger(new GroupID(new Description("amigos")));

        boolean resultHash = ledger.hashCode() == ledger1.hashCode();

        assertFalse(resultHash);
    }

    /**
     * Test getCreationDateToString
     */

    @Test
    @DisplayName("get creationDate as String test")
    void getCreationDateToStringTest() {

        //Arrange:
        Ledger ledger = new Ledger(new PersonID(new Email("test@gmail.com")));
        String expectedCreationDate = new DateAndTime().yearMonthDayToString();

        //Act:
        String actualCreationDate = ledger.getCreationDateToString();

        //Assert:
        assertEquals(expectedCreationDate, actualCreationDate);
    }
}