package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MembersGestTheMovementsOfAnAccountInAGivenTimeController {
    /**
     * US010.2 Como membro de grupo, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain movements from an account - case of success")
    void obtainMovementsFromAnAccount() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 13, 02);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - dates change")
    void obtainMovementsFromAnAccountDateChange() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - same day")
    void obtainMovementsFromAnAccountSameDay() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 14, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 14, 23, 59);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - check before the creation of the ledger")
    void obtainMovementsFromAnAccountBeforeLedger() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2017, 12, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2018, 1, 31, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList());

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - first date null")
    void obtainMovementsFromAnAccountFirstDateNull() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("The dates can´t be null", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain movements from an account - second date null")
    void obtainMovementsFromAnAccountSecondDateNull() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = null;


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("The dates can´t be null", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain movements from an account - first date is after now")
    void obtainMovementsFromAnAccountFirstDateNotValide() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("One of the submitted dates is not valid.", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain movements from an account - final date is after now")
    void obtainMovementsFromAnAccountFinalDateNotValide() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 2, 13, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2020, 12, 13, 13, 02);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.addMember(person1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("One of the submitted dates is not valid.", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain movements from an account - empty ledger exception")
    void obtainMovementsFromAnAccountEmptyLedger() {
        //Arrange
        MemberGetsTheMovementsOfAnAccountInDateRangeController groupController = new MemberGetsTheMovementsOfAnAccountInDateRangeController();
        Group group1 = new Group("Caloteiros");
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        Account account5 = new Account("comida de gato", "comida para a gatinha");


        Person person1 = new Person("Maria", LocalDate.of(1998, 12, 5), new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4455-987"));
        group1.addMember(person1);


        //Act
        try {
            groupController.getOneAccountMovementsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException ledger) {
            assertEquals("The ledger is empty. ", ledger.getMessage());
        }
    }

}

