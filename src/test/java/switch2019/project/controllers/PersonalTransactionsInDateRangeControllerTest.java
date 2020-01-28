package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalTransactionsInDateRangeControllerTest {

    @Test
    @DisplayName("Test if a person get their movements in a given period - success case - one transaction -  US011")
    void returnPersonLedgerFromPeriodSuccessCaseOneTransaction() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit
        person.createTransaction(amount, "payment", dateTransaction1, category, from, to, type);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 23, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 20, 23, 00);

        Transaction transaction1 = new Transaction(amount, "payment", dateTransaction1, category, from, to, type);
        List<Transaction> expectedResult = new ArrayList<>();
        expectedResult.add(transaction1);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> personLedgerMovements = controller.returnPersonLedgerInDateRange(initialDate, finalDate, person);

        //Assert
        assertEquals(personLedgerMovements, expectedResult);

    }


    @Test
    @DisplayName("Test if a person get their movements in a given period - success case - several transactions -  US011")
    void returnPersonLedgerFromPeriodSuccessCaseSeveralTransactions() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - Transaction2//
        LocalDateTime dateTransaction2 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount2 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category2 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount2, "payment", dateTransaction2, category2, from, to, false);
        Transaction transaction2 = new Transaction(amount2, "payment", dateTransaction2, category2, from, to, false);

        //Arrange - Transaction3//
        LocalDateTime dateTransaction3 = LocalDateTime.of(2020, 1, 16, 13, 00);
        MonetaryValue amount3 = new MonetaryValue(22, Currency.getInstance("EUR"));
        Category category3 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount3, "payment", dateTransaction3, category3, from, to, false);
        Transaction transaction3 = new Transaction(amount3, "payment", dateTransaction3, category3, from, to, false);

        //Arrange - ExpectedResult//
        ArrayList<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1, transaction2,transaction3));
        expectedResult.sort(Comparator.comparing(Transaction::getDate));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 17, 00,00);

        //Act
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> personLedgerMovements = controller.returnPersonLedgerInDateRange(initialDate, finalDate,person);

        //Assert
        assertEquals(personLedgerMovements,expectedResult);
    }

    @Test
    @DisplayName("Test if a person get their movements in a given period - null date -  US011")
    void returnPersonLedgerFromPeriodNullDate() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult//
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);

        //Act
        try {
            List<Transaction> personLedgerMovements = controller.returnPersonLedgerInDateRange(initialDate, null,person);
        }

        //Assert
        catch (IllegalArgumentException returnPersonLedgerInDateRange) {
            assertEquals("The dates can´t be null", returnPersonLedgerInDateRange.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a person get their movements in a given period - invalid date -  US011")
    void returnPersonLedgerFromPeriodInvalidDate() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");

        //Arrange - Transaction1//
        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 15, 13, 00);
        MonetaryValue amount1 = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category1 = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        person.createTransaction(amount1, "payment", dateTransaction1, category1, from, to, false);
        Transaction transaction1 = new Transaction(amount1, "payment", dateTransaction1, category1, from, to, false);

        //Arrange - ExpectedResult//
        PersonalTransactionsInDateRangeController controller = new PersonalTransactionsInDateRangeController();
        List<Transaction> expectedResult = new ArrayList<>(Arrays.asList(transaction1));

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 9, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2030, 1, 9, 00, 00);

        //Act
        try {
            List<Transaction> personLedgerMovements = controller.returnPersonLedgerInDateRange(initialDate, finalDate,person);
        }

        //Assert
        catch (IllegalArgumentException returnPersonLedgerInDateRange) {
            assertEquals("One of the submitted dates is not valid.", returnPersonLedgerInDateRange.getMessage());
        }
    }


}
