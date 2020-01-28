package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class GetPersonalAndGroupTransactionsControllerTest {
    @Test
    void getPersonalAndGroupTransactionsSucess() {
        // Arrange ____________________________________________________________________________________________________

        // Person:
        Person person = new Person("Marta", LocalDate.of(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories:
        Category categoryGrocery = new Category("grocery");
        person.createCategoryAndAddToCategoryList("grocery");
        Category categoryFriends = new Category("friends");
        person.createCategoryAndAddToCategoryList("friends");

        //Accounts:
        Account accountMercearia = new Account("mercearia", "mercearia Continente");
        person.createAccount("mercearia", "mercearia Continente");
        Account accountTransporte = new Account("transporte", "transporte Metro");
        person.createAccount("transporte", "transporte Metro");
        Account accountCinema = new Account("cinema", "filmes ás sextas-feiras");
        person.createAccount("cinema", "filmes ás sextas-feiras");

        //Type:
        boolean typeCredit = true; // credit
        boolean typeDebit = false; // debit

        //Monetary Value:
        MonetaryValue monetaryValue200 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue100 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue50 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue150 = new MonetaryValue(150, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue30 = new MonetaryValue(30, Currency.getInstance("EUR"));

        //Personal Transactions:
        person.createTransaction(monetaryValue200, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 5),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);
        person.createTransaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 15, 10, 07),
                categoryFriends, accountTransporte, accountCinema, typeDebit);
        person.createTransaction(monetaryValue50, "payment",
                LocalDateTime.of(2020, 1, 3, 14, 10),
                categoryGrocery, accountCinema, accountMercearia, typeCredit);
        person.createTransaction(monetaryValue150, "payment",
                LocalDateTime.of(2020, 1, 5, 3, 15),
                categoryFriends, accountCinema, accountTransporte, typeDebit);
        person.createTransaction(monetaryValue50, "payment",
                LocalDateTime.of(2019, 12, 31, 21, 01),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);

        // Groups:
        GroupsList groupsList = new GroupsList();
        Group spiceGirls = new Group("spice girls");
        Group work = new Group("work");
        groupsList.createGroup("spice girls", person);
        groupsList.createGroup("work", person);

        // Group Accounts:
        Account accountCombustivel = new Account("combustivel", "gastos de combustivél");
        spiceGirls.createGroupAccount("combustivel", "gastos de combustivél");
        Account accountGato = new Account("comida de gato", "comida para a gatinha");
        spiceGirls.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountDinner = new Account("dinner", "partilha de jantares");
        spiceGirls.createGroupAccount("dinner", "partilha de jantares");

        Account accountSweets = new Account("sweets", "doces de sexta");
        work.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountFruta = new Account("fruta", "fruta para a equipa");
        work.createGroupAccount("dinner", "partilha de jantares");

        // Group Transactions:
        groupsList.createTransactionOnSpecificGroup("spice girls", monetaryValue100, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15),
                categoryFriends, accountCombustivel, accountGato, typeDebit);
        groupsList.createTransactionOnSpecificGroup("spice girls", monetaryValue200, "payment",
                LocalDateTime.of(2019, 11, 15, 15, 04),
                categoryGrocery, accountGato, accountDinner, typeCredit);
        groupsList.createTransactionOnSpecificGroup("spice girls", monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 2, 12, 15),
                categoryFriends, accountDinner, accountCombustivel, typeDebit);

        groupsList.createTransactionOnSpecificGroup("work", monetaryValue200, "payment",
                LocalDateTime.of(2019, 11, 15, 15, 04),
                categoryGrocery, accountSweets, accountFruta, typeCredit);
        groupsList.createTransactionOnSpecificGroup("work", monetaryValue30, "payment",
                LocalDateTime.of(2020, 1, 1, 12, 05),
                categoryFriends, accountFruta, accountSweets, typeCredit);

        // Transactions:
        Transaction transaction1 = new Transaction(monetaryValue30, "payment",
                LocalDateTime.of(2020, 1, 1, 12, 05),
                categoryFriends, accountFruta, accountSweets, typeCredit);
        Transaction transaction2 = new Transaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 2, 12, 15),
                categoryFriends, accountDinner, accountCombustivel, typeDebit);
        Transaction transaction3 = new Transaction(monetaryValue50, "payment",
                LocalDateTime.of(2020, 1, 3, 14, 10),
                categoryGrocery, accountCinema, accountMercearia, typeCredit);
        Transaction transaction4 = new Transaction(monetaryValue150, "payment",
                LocalDateTime.of(2020, 1, 5, 3, 15),
                categoryFriends, accountCinema, accountTransporte, typeDebit);
        Transaction transaction5 = new Transaction(monetaryValue200, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 5),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);
        Transaction transaction6 = new Transaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 15, 10, 07),
                categoryFriends, accountTransporte, accountCinema, typeDebit);

        ArrayList<Transaction> expectedTransaction = new ArrayList<Transaction>(Arrays.asList(transaction1, transaction2,
                transaction3, transaction4, transaction5, transaction6));

        GetPersonalAndGroupTransactionsController controller = new GetPersonalAndGroupTransactionsController();

        // Act _______________________________________________________________________________________________________
        ArrayList<Transaction> selectedTransactions = controller.getPersonalAndGroupTransactions
                (person, LocalDateTime.of(2020, 1, 1, 10, 10),
                LocalDateTime.of(2020, 1, 20, 10, 10), groupsList);

        // Arrange ___________________________________________________________________________________________________
        assertEquals(expectedTransaction, selectedTransactions);
    }

    @Test
    void getPersonalAndGroupTransactionsOutOfRange() {
        // Arrange ____________________________________________________________________________________________________

        // Person:
        Person person = new Person("Marta", LocalDate.of(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories:
        Category categoryGrocery = new Category("grocery");
        person.createCategoryAndAddToCategoryList("grocery");
        Category categoryFriends = new Category("friends");
        person.createCategoryAndAddToCategoryList("friends");

        //Accounts:
        Account accountMercearia = new Account("mercearia", "mercearia Continente");
        person.createAccount("mercearia", "mercearia Continente");
        Account accountTransporte = new Account("transporte", "transporte Metro");
        person.createAccount("transporte", "transporte Metro");
        Account accountCinema = new Account("cinema", "filmes ás sextas-feiras");
        person.createAccount("cinema", "filmes ás sextas-feiras");

        //Type:
        boolean typeCredit = true; // credit
        boolean typeDebit = false; // debit

        //Monetary Value:
        MonetaryValue monetaryValue200 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue100 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue50 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue150 = new MonetaryValue(150, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue30 = new MonetaryValue(30, Currency.getInstance("EUR"));

        //Personal Transactions:
        person.createTransaction(monetaryValue200, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 5),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);
        person.createTransaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 15, 10, 07),
                categoryFriends, accountTransporte, accountCinema, typeDebit);
        person.createTransaction(monetaryValue50, "payment",
                LocalDateTime.of(2020, 1, 3, 14, 10),
                categoryGrocery, accountCinema, accountMercearia, typeCredit);
        person.createTransaction(monetaryValue150, "payment",
                LocalDateTime.of(2020, 1, 5, 3, 15),
                categoryFriends, accountCinema, accountTransporte, typeDebit);
        person.createTransaction(monetaryValue50, "payment",
                LocalDateTime.of(2019, 12, 31, 21, 01),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);

        // Groups:
        GroupsList groupsList = new GroupsList();
        Group spiceGirls = new Group("spice girls");
        Group work = new Group("work");
        groupsList.createGroup("spice girls", person);
        groupsList.createGroup("work", person);

        // Group Accounts:
        Account accountCombustivel = new Account("combustivel", "gastos de combustivél");
        spiceGirls.createGroupAccount("combustivel", "gastos de combustivél");
        Account accountGato = new Account("comida de gato", "comida para a gatinha");
        spiceGirls.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountDinner = new Account("dinner", "partilha de jantares");
        spiceGirls.createGroupAccount("dinner", "partilha de jantares");

        Account accountSweets = new Account("sweets", "doces de sexta");
        work.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountFruta = new Account("fruta", "fruta para a equipa");
        work.createGroupAccount("dinner", "partilha de jantares");

        // Group Transactions:
        groupsList.returnSpecificGroup("spice girls").createGroupTransaction(monetaryValue100, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15),
                categoryFriends, accountCombustivel, accountGato, typeDebit);
        groupsList.returnSpecificGroup("spice girls").createGroupTransaction(monetaryValue200, "payment",
                LocalDateTime.of(2019, 11, 15, 15, 04),
                categoryGrocery, accountGato, accountDinner, typeCredit);
        groupsList.returnSpecificGroup("spice girls").createGroupTransaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 2, 12, 15),
                categoryFriends, accountDinner, accountCombustivel, typeDebit);

        groupsList.returnSpecificGroup("work").createGroupTransaction(monetaryValue200, "payment",
                LocalDateTime.of(2019, 11, 15, 15, 04),
                categoryGrocery, accountSweets, accountFruta, typeCredit);
        groupsList.returnSpecificGroup("work").createGroupTransaction(monetaryValue30, "payment",
                LocalDateTime.of(2020, 1, 1, 12, 05),
                categoryFriends, accountFruta, accountSweets, typeCredit);

        // Transactions:
        Transaction transaction1 = new Transaction(monetaryValue30, "payment",
                LocalDateTime.of(2020, 1, 1, 12, 05),
                categoryFriends, accountFruta, accountSweets, typeCredit);
        Transaction transaction2 = new Transaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 2, 12, 15),
                categoryFriends, accountDinner, accountCombustivel, typeDebit);
        Transaction transaction3 = new Transaction(monetaryValue50, "payment",
                LocalDateTime.of(2020, 1, 3, 14, 10),
                categoryGrocery, accountCinema, accountMercearia, typeCredit);
        Transaction transaction4 = new Transaction(monetaryValue150, "payment",
                LocalDateTime.of(2020, 1, 5, 3, 15),
                categoryFriends, accountCinema, accountTransporte, typeDebit);
        Transaction transaction5 = new Transaction(monetaryValue200, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 5),
                categoryGrocery, accountMercearia, accountCinema, typeCredit);
        Transaction transaction6 = new Transaction(monetaryValue100, "payment",
                LocalDateTime.of(2020, 1, 15, 10, 07),
                categoryFriends, accountTransporte, accountCinema, typeDebit);

        ArrayList<Transaction> expectedTransaction = new ArrayList<Transaction>(Arrays.asList(transaction1, transaction2,
                transaction3, transaction4, transaction5, transaction6));

        GetPersonalAndGroupTransactionsController controller = new GetPersonalAndGroupTransactionsController();

        // Act _______________________________________________________________________________________________________
        ArrayList<Transaction> selectedTransactions = controller.getPersonalAndGroupTransactions
                (person, LocalDateTime.of(2020, 1, 15, 10, 10),
                        LocalDateTime.of(2020, 1, 20, 10, 10), groupsList);

        // Arrange ___________________________________________________________________________________________________
        assertNotEquals(expectedTransaction, selectedTransactions);
    }


    @Test
    void getPersonalAndGroupWithoutTransactions() {
        // Arrange ____________________________________________________________________________________________________

        // Person:
        Person person = new Person("Marta", LocalDate.of(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4520-266"));

        //Categories:
        Category categoryGrocery = new Category("grocery");
        person.createCategoryAndAddToCategoryList("grocery");
        Category categoryFriends = new Category("friends");
        person.createCategoryAndAddToCategoryList("friends");

        //Accounts:
        Account accountMercearia = new Account("mercearia", "mercearia Continente");
        person.createAccount("mercearia", "mercearia Continente");
        Account accountTransporte = new Account("transporte", "transporte Metro");
        person.createAccount("transporte", "transporte Metro");
        Account accountCinema = new Account("cinema", "filmes ás sextas-feiras");
        person.createAccount("cinema", "filmes ás sextas-feiras");

        //Type:
        boolean typeCredit = true; // credit
        boolean typeDebit = false; // debit

        //Monetary Value:
        MonetaryValue monetaryValue200 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue100 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue50 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue150 = new MonetaryValue(150, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue30 = new MonetaryValue(30, Currency.getInstance("EUR"));


        // Groups:
        GroupsList groupsList = new GroupsList();
        Group spiceGirls = new Group("spice girls");
        Group work = new Group("work");
        groupsList.createGroup("spice girls", person);
        groupsList.createGroup("work", person);

        // Group Accounts:
        Account accountCombustivel = new Account("combustivel", "gastos de combustivél");
        spiceGirls.createGroupAccount("combustivel", "gastos de combustivél");
        Account accountGato = new Account("comida de gato", "comida para a gatinha");
        spiceGirls.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountDinner = new Account("dinner", "partilha de jantares");
        spiceGirls.createGroupAccount("dinner", "partilha de jantares");

        Account accountSweets = new Account("sweets", "doces de sexta");
        work.createGroupAccount("comida de gato", "comida para a gatinha");
        Account accountFruta = new Account("fruta", "fruta para a equipa");
        work.createGroupAccount("dinner", "partilha de jantares");


        ArrayList<Transaction> expectedTransaction = new ArrayList<Transaction>(Collections.emptyList());

        GetPersonalAndGroupTransactionsController controller = new GetPersonalAndGroupTransactionsController();

        // Act _______________________________________________________________________________________________________
        ArrayList<Transaction> selectedTransactions = controller.getPersonalAndGroupTransactions
                (person, LocalDateTime.of(2020, 1, 1, 10, 10),
                        LocalDateTime.of(2020, 1, 20, 10, 10), groupsList);

        // Arrange ___________________________________________________________________________________________________
        assertEquals(expectedTransaction, selectedTransactions);
    }
    
}