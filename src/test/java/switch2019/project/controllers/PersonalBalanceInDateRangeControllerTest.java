package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;
import switch2019.project.model.person.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBalanceInDateRangeControllerTest {

    /**
     * US017: As a user, I want to see the balance of my personal transactions in specific date range.
     */

    @Test
    @DisplayName("Get the balance of my own transactions over a valid date range - Main Scenario")
    void getPersonalBalanceInDateRange() {
        //Arrange
        //Initialize Person:
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //Initialize Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "car gas",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 1, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);

        //Expected Return
        double expectedPersonalBalanceFromDateRange = -95.4;

        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();
        //Act

        double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my own transactions in a specific day - valid day")
    void getPersonalBalanceForJustOneDay() {
        //Arrange
        //Initialize Person
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //Initialize Transactions
        person1.createTransaction(new MonetaryValue(250, Currency.getInstance("EUR")), "Hostel Barcelona",
                LocalDateTime.of(2020, 1, 13, 13, 05),
                new Category("grocery"), new Account("Revolut", "For trips expenses"),
                new Account("Friends & Company", "Holidays"),
                true);
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "Pack of Super Bock",
                LocalDateTime.of(2020, 1, 13, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(60, Currency.getInstance("EUR")), "Car Gas",
                LocalDateTime.of(2020, 1, 18, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 13, 23, 59);

        //Expected Return
        double expectedPersonalBalanceFromDateRange = 230;
        //Initialize Controller
        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();

        //Act
        double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }


    @Test
    @DisplayName("Get the balance of my personal transactions over a valid date range but initial date and final date not in order.")
    void getPersonalBalanceInDateRangeWithDatesNotInOrder() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //Init Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        //Initial Date is after Final Date
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 6, 00, 00);
        LocalDateTime initialDate = LocalDateTime.of(2019, 12, 31, 00, 00);

        //Expected Return
        double expectedPersonalBalanceFromDateRange = -95.4;
        //Initialize Controller
        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();

        //Act
        double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);

        //Assert
        assertEquals(expectedPersonalBalanceFromDateRange, personalBalanceInDateRange);
    }

    @Test
    @DisplayName("Get the balance of my personal transactions given an invalid date range - final date higher than present")
    void getPersonalBalanceInDateRangeWithInvalidDate() {
        //Arrange
        //Initialize Person
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //Initialize Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2021, 1, 27, 00, 00);
        //Initialize Controller
        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();

        try {
            //Act
            double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("One of the submitted dates is not valid.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance a ledger that is empty. With any transactions,")
    void getPersonalBalanceInDateRangeEmptyBalance() {
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        //Arrange
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //No transactions
        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();
        try {
            //Act
            double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);
            fail();
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("The ledger is Empty.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Get the balance of my transactions transactions over a period that has no transactions in the given date range")
    void getPersonalBalanceInDateRangeEmptyBalanceOverDateRange() {
        //Arrange
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Marta", LocalDate.of(1995, 12, 04), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"),mom,dad);

        //Initialize Transactions
        person1.createTransaction(new MonetaryValue(20, Currency.getInstance("EUR")), "2 pacs of Gurosan",
                LocalDateTime.of(2020, 1, 1, 13, 05),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(5.4, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 1, 14, 11),
                new Category("grocery"), new Account("Millenium", "Only for Groceries"),
                new Account("Continente", "Food Expenses"),
                false);
        person1.createTransaction(new MonetaryValue(70, Currency.getInstance("EUR")), "schweppes",
                LocalDateTime.of(2020, 1, 5, 17, 23),
                new Category("grocery"), new Account("CGD", "Only Gas Expenses"),
                new Account("BP", "Gas"),
                false);

        LocalDateTime initialDate = LocalDateTime.of(2019, 10, 27, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2019, 9, 20, 00, 00);

        PersonalBalanceInDateRangeController controller = new PersonalBalanceInDateRangeController();

        //Act
        double personalBalanceInDateRange = controller.getBalanceInDateRange(initialDate, finalDate, person1);

        assertEquals(0, personalBalanceInDateRange);
    }
}