package switch2019.project.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class MemberGetsTransactionsOfAnAccountInDateRangeControllerTest {

    /**
     * US010.2
     * As a group member, i want to obtain the transactions of a specific
     * account in a given date range.
     */

    @Test
    @DisplayName("Obtain transactions from an account - case of success")
    void obtainTransactionsFromAnAccount() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - dates change")
    void obtainTransactionsFromAnAccountInvertedOrder() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountSameDay() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);

        List<Transaction> expectedTransactions = new ArrayList<>(Collections.singletonList(transaction1));

        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        List<Transaction> listOfTransactions = groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }
    
    @Test
    @DisplayName("Obtain transactions from an account - first date null")
    void obtainTransactionsFromAnAccountFirstDateNull() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)


        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("The dates can´t be null", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - second date null")
    void obtainTransactionsFromAnAccountSecondDateNull() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)


        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("The dates can´t be null", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - first date is after now")
    void obtainTransactionsFromAnAccountFirstInvalidDate() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)


        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("One of the submitted dates is not valid.", initialDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - final date is after now")
    void obtainTransactionsFromAnAccountFinalInvalidDate() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)

        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException finalDate) {
            assertEquals("One of the submitted dates is not valid.", finalDate.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - empty ledger exception")
    void obtainTransactionsFromAnAccountEmptyLedger() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

        LocalDateTime date1 = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 13, 13, 02);

        Account account5 = new Account("comida de gato", "comida para a gatinha");

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, person1);
        }

        //Assert
        catch (IllegalArgumentException ledger) {
            assertEquals("The ledger is empty. ", ledger.getMessage());
        }
    }

    @Test
    @DisplayName("Obtain transactions from an account - person not from group")
    void obtainTransactionsFromAnAccountNotAMember() {
        //Arrange
        MemberGetsTransactionsOfAnAccountInDateRangeController groupController =
                new MemberGetsTransactionsOfAnAccountInDateRangeController();

        Person father = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person mother = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person1 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), mother, father);

        Group group1 = new Group("Associação Amigos do Douro");

        group1.addMember(person1);

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

        //Type:
        boolean type1 = true; //credit (+)
        boolean type2 = false; //debit (-)

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        group1.createGroupTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        group1.createGroupTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Transactions between unchecked accounts
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        group1.createGroupTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        //Act
        try {
            groupController.getOneAccountTransactionsFromGroup(account5, date1, date2, group1, mother);
        }
        //Assert
        catch (IllegalArgumentException notMember) {
            assertEquals("You don't have access to that account.", notMember.getMessage());
        }

    }

}
