package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;
import switch2019.project.model.person.Person;
import switch2019.project.model.Legder.Transaction;
import switch2019.project.model.valueobject.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalTransactionsInDateRangeControllerTest {

    @Test
    @DisplayName("Test if a person get their transactions in a given period - success case - one transaction -  US011")
    void returnPersonLedgerFromPeriodSuccessCaseOneTransaction() {
        //Arrange
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category = new Category("General");
        person1.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person1.createAccount("Wallet", "General expenses");
        person1.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit
        person1.createTransaction(amount, "payment", dateTransaction1, category, from, to, type);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 23, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 20, 23, 00);

        Transaction transaction1 = new Transaction(amount, "payment", dateTransaction1, category, from, to, type);
        List<Transaction> expectedListOfPersonalTransactions = new ArrayList<>();
        expectedListOfPersonalTransactions.add(transaction1);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> listOfPersonalTransactions = controller.returnPersonLedgerInDateRange(initialDate, finalDate, person1);

        //Assert
        assertEquals(expectedListOfPersonalTransactions, listOfPersonalTransactions);

    }


    @Test
    @DisplayName("Test if a person get their transactions in a given period - success case - several transactions -  US011")
    void returnPersonLedgerFromPeriodSuccessCaseSeveralTransactions() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - Transaction2
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, false);
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, false);

        //Arrange - Transaction3
        LocalDateTime dateTransaction3 = LocalDateTime.of(2020, 1, 16, 13, 00);
        MonetaryValue amount3 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category3 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount3, "payment", dateTransaction3, category3, from, to, false);
        Transaction transaction3 = new Transaction(amount3, "payment", dateTransaction3, category3, from, to, false);

        //Arrange
        ArrayList<Transaction> expectedListOfPersonalTransactions = new ArrayList<>(Arrays.asList(transaction3, transaction1, transaction2));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 17, 00, 00);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> listOfPersonalTransactions = controller.returnPersonLedgerInDateRange(initialDate, finalDate, person);

        //Assert
        assertEquals(expectedListOfPersonalTransactions, listOfPersonalTransactions);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - exchanged dates  -  US011")
    void returnPersonLedgerFromPeriodExchangedDates() {
        //Arrange
        Person person = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Account1", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Account1", "General expenses");
        person.createAccount("Account2", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - Transaction2
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, false);
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, false);

        //Arrange - ExpectedResult
        ArrayList<Transaction> expectedListOfPersonalTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 17, 00, 00);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> listOfPersonalTransactions = controller.returnPersonLedgerInDateRange(finalDate, initialDate, person);

        //Assert
        assertEquals(expectedListOfPersonalTransactions, listOfPersonalTransactions);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - same day  -  US011")
    void returnPersonLedgerFromPeriodSameDay() {
        //Arrange
        Person person = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Account1", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Account1", "General expenses");
        person.createAccount("Account2", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult
        ArrayList<Transaction> expectedListOfPersonalTransactions = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 14, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 14, 23, 59);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> listOfPersonalTransactions = controller.returnPersonLedgerInDateRange(finalDate, initialDate, person);

        //Assert
        assertEquals(expectedListOfPersonalTransactions, listOfPersonalTransactions);
    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - emptyLedger -  US011")
    void returnPersonLedgerFromPeriodEmptyLegder() {
        //Arrange
        Person person = new Person("Miguel", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Account1", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Account1", "General expenses");
        person.createAccount("Account2", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult
        ArrayList<Transaction> expectedListOfPersonalTransactions = new ArrayList<>();

        LocalDateTime initialDate = LocalDateTime.of(2018, 1, 14, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 1, 14, 23, 59);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> listOfPersonalTransactions = controller.returnPersonLedgerInDateRange(finalDate, initialDate, person);

        //Assert
        assertEquals(expectedListOfPersonalTransactions, listOfPersonalTransactions);
    }


    @Test
    @DisplayName("Test if a person get their transactions in a given period - null date -  US011")
    void returnPersonLedgerFromPeriodNullDate() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);

        //Act
        try {
           controller.returnPersonLedgerInDateRange(initialDate, null, person);
        }

        //Assert
        catch (IllegalArgumentException listOfPersonalTransactions) {
            assertEquals("The dates can´t be null", listOfPersonalTransactions.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a person get their transactions in a given period - invalid date -  US011")
    void returnPersonLedgerFromPeriodInvalidDate() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> expectedListOfPersonalTransactions = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2030, 1, 9, 00, 00);

        //Act
        try {
            controller.returnPersonLedgerInDateRange(initialDate, finalDate, person);
        }

        //Assert
        catch (IllegalArgumentException listOfPersonalTransactions) {
            assertEquals("One of the submitted dates is not valid.", listOfPersonalTransactions.getMessage());
        }
    }

}
