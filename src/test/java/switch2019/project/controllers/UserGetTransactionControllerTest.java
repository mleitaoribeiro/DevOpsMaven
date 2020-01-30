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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetTransactionControllerTest {

    /**
     * US010.1 Como utilizador, quero obter os movimentos de determinada conta num dado período.
     */

    @Test
    @DisplayName("Obtain movements from an account - Main Scenario")
    void obtainMovementsFromAnAccountMainScenario() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        UserGetsTransactionsController controller= new UserGetsTransactionsController();
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
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account5, date1, date2,person);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - same day")
    void obtainMovementsFromAnAccountInSameDay() {

        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        UserGetsTransactionsController controller= new UserGetsTransactionsController();
        LocalDateTime date1 = LocalDateTime.of(2019, 12, 13, 00, 00);
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account5, date1, date2,person);


        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain movements from an account - date null")
    void obtainMovementsDateNull() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        UserGetsTransactionsController controller= new UserGetsTransactionsController();
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);


        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        Transaction transaction1 = new Transaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        person.createTransaction(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        //Act
        try {
            controller.getOneAccountTransactionsFromPerson(account5, date1, date2,person);
        }

        //Assert
        catch (IllegalArgumentException initialDate) {
            assertEquals("The dates can´t be null", initialDate.getMessage());
        }
    }
}
