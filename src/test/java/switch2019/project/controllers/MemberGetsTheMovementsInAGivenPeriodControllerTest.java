package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MemberGetsTheMovementsInAGivenPeriodControllerTest {

    /**
     * US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo num dado período.
     */

    @Test
    @DisplayName("Get Group Ledger Transactions in a given period - Success Case")
    void getLedgerTransactionsInPeriod() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));


        oneGroup.setAdmin(onePerson);

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

        //Add Transactions to Group Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction1, expectedTransaction2));

        //Act
        ArrayList<Transaction> real = controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2019, 2, 3, 10, 40), oneGroup, onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - EmptyLedger")
    void getLedgerTransactionsInPeriodEmptyLedger() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        ArrayList<Transaction> expected = new ArrayList<>();

        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        //Act
        ArrayList<Transaction> real = controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2017, 10, 2, 9, 20),
                LocalDateTime.of(2017, 12, 3, 10, 40),oneGroup, onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Same date")
    void getLedgerTransactionsInPeriodSameDay() {

        //Arrange
        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);

        ArrayList<Transaction> expected = new ArrayList<>(Collections.singletonList(expectedTransaction1));
        //Act
        ArrayList<Transaction> real = controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2018, 10, 2, 9, 10),
                LocalDateTime.of(2018, 10, 2, 9, 10),oneGroup, onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - No transactions on the given date")
    void getLedgerTransactionsInPeriodNoTransactions() {

        //Arrange

        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

        Account oneAccount = new Account("myxpto", "xpto Account");
        Account anotherAccount = new Account("abc", "abc Account");


        Category otherCategory = new Category("QWERTY");

        boolean oneType = true; //Credit
        boolean otherType = false; //Debit

        MonetaryValue oneMonetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue otherMonetaryValue = new MonetaryValue(10, Currency.getInstance("EUR"));

        LocalDateTime otherLocalDate = LocalDateTime.of(2019, 1, 2, 10, 40);
        LocalDateTime anotherLocalDate = LocalDateTime.of(2015, 10, 2, 10, 40);

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        ArrayList<Transaction> expected = new ArrayList<>();
        //Act
        ArrayList<Transaction> real = controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2012, 10, 2, 9, 10),
                LocalDateTime.of(2013, 10, 2, 9, 10),oneGroup, onePerson);
        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Final Date")
    void getLedgerTransactionsInPeriodFalse() {

        //Arrange
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Expected Transactions
        Transaction expectedTransaction1 = new Transaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        Transaction expectedTransaction2 = new Transaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);

        ArrayList<Transaction> expected = new ArrayList<>(Arrays.asList(expectedTransaction1, expectedTransaction2));
        //Act
        ArrayList<Transaction> real = controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40),
                LocalDateTime.of(2017, 10, 2, 9, 20), oneGroup, onePerson);

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initital Date > Actual Date")
    void getLedgerTransactionsInPeriodInitialDateSuperiorActualDate() {

        //Arrange
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2025, 2, 3, 10, 40),
                    LocalDateTime.of(2017, 10, 2, 9, 20),oneGroup, onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Final  Date > Actual Date")
    void getLedgerTransactionsInPeriodFinalDateSuperiorActualDate() {

        //Arrange
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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

        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40),
                    LocalDateTime.of(2030, 10, 2, 9, 20),oneGroup, onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("One of the submitted dates is not valid.", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Initial Date null")
    void getLedgerTransactionsInPeriodInitialDateNull() {

        //Arrange
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            controller.returnGroupLedgerFromPeriod(null, LocalDateTime.of(2030, 10, 2, 9, 20), oneGroup, onePerson);
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
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        oneGroup.setAdmin(onePerson);

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


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            controller.returnGroupLedgerFromPeriod(LocalDateTime.of(2019, 2, 3, 10, 40), null,oneGroup, onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("The dates can´t be null", getTransactionsFromPeriod.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger Transactions in a given period - Person is not admin")
    void getLedgerTransactionsInPeriodPersonNotAdmin() {

        //Arrange
        MemberGetsTheMovementsInAGivenPeriodController controller = new MemberGetsTheMovementsInAGivenPeriodController();

        Group oneGroup = new Group("XPTO");

        Person onePerson = new Person("Alex", LocalDate.of(1995, 12, 04),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

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


        //Add Transactions to Ledger
        oneGroup.createGroupTransaction(oneMonetaryValue, "payment", oneLocalDate, oneCategory, oneAccount, otherAccount, oneType);
        oneGroup.createGroupTransaction(otherMonetaryValue, "xpto", otherLocalDate, otherCategory, anotherAccount, oneAccount, otherType);
        oneGroup.createGroupTransaction(oneMonetaryValue, "abc", anotherLocalDate, otherCategory, anotherAccount, oneAccount, oneType);

        //Act
        try {
            controller.returnGroupLedgerFromPeriod(null, LocalDateTime.of(2030, 10, 2, 9, 20),oneGroup, onePerson);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsFromPeriod) {
            assertEquals("Person is not a member of the group.", getTransactionsFromPeriod.getMessage());
        }
    }

}