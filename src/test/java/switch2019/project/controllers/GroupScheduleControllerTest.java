package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class GroupScheduleControllerTest {

    @Test
    @DisplayName("Schedule a group transaction")
    void scheduleGroupTransactionDaily() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", person,
                "daily", amount, description, null, category, from, to, type);

        Thread.sleep(2400); // 250 x 10 = 2500

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 10);
    }

    @Test
    void scheduleGroupTransactionWorkingDays() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", person,
                "working days", amount, description, null, category, from, to, type);

        Thread.sleep(1900); // 500 x 4 = 2000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 4);
    }

    @Test
    void scheduleGroupTransactionWeekly() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", person,
                "weekly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 750 x 4 = 3000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 4);
    }

    @Test
    void scheduleGroupTransactionMonthly() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        //Act
        boolean result = groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", person,
                "monthly", amount, description, null, category, from, to, type);

        Thread.sleep(2900); // 1000 x 3 = 3000

        //Assert
        assertTrue(result && groupsList.checkIfAGroupsLedgerSize("tarzan") == 3);
    }

    @Test
    void scheduleGroupTransactionNoGroupMatch() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person person = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", person);

        try {
            //Act
            groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", person,
                    "monthly", amount, description, null, category, from, to, type);

        }

        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }

    @Test
    @DisplayName("Schedule a group transaction")
    void scheduleGroupTransactionNotAMember() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person personMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Person personNotAMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        personMember.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        personMember.createAccount("Wallet", "General expenses");
        personMember.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        GroupsList groupsList = new GroupsList();
        groupsList.createGroup("tarzan", personMember);

        try {
            //Act
            groupScheduleController.scheduleGroupTransaction(groupsList, "tarzan", personNotAMember,
                    "daily", amount, description, null, category, from, to, type);

        }

        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("There're no groups found with that description.", result.getMessage());
        }
    }
}