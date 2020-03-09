package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.ledger.Transaction;
import switch2019.project.model.valueObject.Address;
import switch2019.project.model.valueObject.MonetaryValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTransactionsOfAnAccountInDateRangeControllerTest {

    /**
     * US010.1
     * As a person, I want to get my transactions from a account in a specific date range
     */

    @Test
    @DisplayName("Obtain transactions from an account - Main Scenario")
    void obtainTransactionsFromAnAccountMainScenario() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"),mom,dad);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        boolean type1 = true; // credit (+)
        boolean type2 = false; // debit (-)

        person.createTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account, type1);
        person.createTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account, account1, type1);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2020, 1, 10, 12, 15), category2, account2, account1, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 9, 25, 12, 15), category2, account1, account2, type2);

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        GetTransactionsOfAnAccountInDateRangeController controller = new GetTransactionsOfAnAccountInDateRangeController();
        LocalDateTime initialDate = LocalDateTime.of(2019, 12, 13, 13, 02);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 26, 13, 02);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account, initialDate, finalDate,person);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - Dates Inverted Order ")
    void obtainTransactionsFromAnAccountDatesInvertedOrder() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"),mom,dad);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        boolean type1 = true; // credit (+)
        boolean type2 = false; // debit (-)

        person.createTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account, type1);
        person.createTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account, account1, type1);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2020, 1, 10, 12, 15), category2, account2, account1, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 9, 25, 12, 15), category2, account1, account2, type2);

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account, account1, type1);
        Transaction transaction3 = new Transaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account, type2);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1, transaction3));

        GetTransactionsOfAnAccountInDateRangeController controller = new GetTransactionsOfAnAccountInDateRangeController();
        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 26, 13, 02);
        LocalDateTime finalDate = LocalDateTime.of(2019, 12, 13, 13, 02);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account, initialDate, finalDate,person);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - same day")
    void obtainTransactionsFromAnAccountInSameDay() {

        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"),mom,dad);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        boolean type1 = true; // credit (+)
        boolean type2 = false; // debit (-)

        person.createTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2019, 10, 13, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2019, 10, 13, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account1, type2);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account1, account2, type2);

        Transaction transaction1 = new Transaction(monetaryValue1, "payment",
                LocalDateTime.of(2019, 10, 13, 13, 05), category1, account1, account5, type1);
        Transaction transaction2 = new Transaction(monetaryValue7, "payment",
                LocalDateTime.of(2019, 10, 13, 17, 22), category2, account5, account1, type1);

        List<Transaction> expectedTransactions = new ArrayList<>(Arrays.asList(transaction2, transaction1));

        GetTransactionsOfAnAccountInDateRangeController controller= new GetTransactionsOfAnAccountInDateRangeController();
        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 13, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 10, 13, 23, 59);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account5, initialDate, finalDate, person);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - No results")
    void obtainTransactionsEmptyReturn() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"),mom,dad);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        boolean type1 = true; // credit (+)
        boolean type2 = false; // debit (-)

        person.createTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        List<Transaction> expectedTransactions = new ArrayList<>();

        GetTransactionsOfAnAccountInDateRangeController controller = new GetTransactionsOfAnAccountInDateRangeController();
        LocalDateTime initialDate = LocalDateTime.of(2018, 1, 26, 23, 59);
        LocalDateTime finalDate = LocalDateTime.of(2018, 10, 26, 23, 59);

        //Act
        List<Transaction> listOfTransactions = controller.getOneAccountTransactionsFromPerson(account5, initialDate, finalDate, person);

        //Assert
        assertEquals(expectedTransactions, listOfTransactions);
    }

    @Test
    @DisplayName("Obtain transactions from an account - date null")
    void obtainTransactionsDateNull() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"),mom,dad);

        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));

        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        boolean type1 = true; // credit (+)
        boolean type2 = false; // debit (-)

        person.createTransaction(monetaryValue1, "payment",
                LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        person.createTransaction(monetaryValue7, "payment",
                LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);
        person.createTransaction(monetaryValue2, "payment",
                LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);

        GetTransactionsOfAnAccountInDateRangeController controller= new GetTransactionsOfAnAccountInDateRangeController();
        LocalDateTime date1 = null;
        LocalDateTime date2 = LocalDateTime.of(2020, 1, 26, 23, 59);

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
