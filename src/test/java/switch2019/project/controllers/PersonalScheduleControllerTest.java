package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class PersonalScheduleControllerTest {

    @Test
    @DisplayName("Schedule a personal transaction")
    void schedulePersonaTransactionDaily() throws InterruptedException {
        //Arrange
        PersonalScheduleController personalScheduleController = new PersonalScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = personalScheduleController.schedulePersonalTransaction(person, "daily",
                amount, description, null, category, from, to, type);

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && person.ledgerSize() == 10);
    }

    @Test
    void schedulePersonaTransactionWorkingDays() throws InterruptedException {
        //Arrange
        PersonalScheduleController personalScheduleController = new PersonalScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = personalScheduleController.schedulePersonalTransaction(person, "working days",
                amount, description, null, category, from, to, type);

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }

    @Test
    void schedulePersonaTransactionWeekly() throws InterruptedException {
        //Arrange
        PersonalScheduleController personalScheduleController = new PersonalScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = personalScheduleController.schedulePersonalTransaction(person, "weekly",
                amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 4);
    }

    @Test
    void schedulePersonaTransactionMonthly() throws InterruptedException {
        //Arrange
        PersonalScheduleController personalScheduleController = new PersonalScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        //Act
        boolean result = personalScheduleController.schedulePersonalTransaction(person, "monthly",
                amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && person.ledgerSize() == 3);
    }

    @Test
    void schedulePersonaTransactionNoPeriodicityMatch() throws InterruptedException {
        //Arrange
        PersonalScheduleController personalScheduleController = new PersonalScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        try {
            //Act
            personalScheduleController.schedulePersonalTransaction(person, "tomorrow",
                    amount, description, null, category, from, to, type);

            Thread.sleep(1600);
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }
}