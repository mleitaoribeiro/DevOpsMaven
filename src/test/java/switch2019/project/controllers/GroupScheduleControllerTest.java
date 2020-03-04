package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.Address;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class GroupScheduleControllerTest {

    /**
     * US020.2
     * As a group member, i want to create a schedule with daily, working days, weekly and monthly periodicity,
     * so the transactions are automatically generated.
     *
     * @throws InterruptedException
     */
    @Test
    @DisplayName("Schedule a group transaction")
    void scheduleGroupTransactionDaily() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

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

        Group group = new Group("tarzan");
        group.addMember(person);

        //Act
        boolean scheduleTransaction = groupScheduleController.scheduleGroupTransaction(group, person,
                "daily", amount, description, null, category, from, to, type);
        Thread.sleep(2400); // 250 x 10 = 2500
        boolean ledgerSize = groupScheduleController.ledgerSize(group) == 10;

        //Assert
        assertTrue(scheduleTransaction && ledgerSize);
    }

    @Test
    void scheduleGroupTransactionWorkingDays() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

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

        Group group = new Group("tarzan");
        group.addMember(person);

        //Act
        boolean scheduleTransaction = groupScheduleController.scheduleGroupTransaction(group, person,
                "working days", amount, description, null, category, from, to, type);
        Thread.sleep(1900); // 500 x 4 = 2000
        boolean ledgerSize = groupScheduleController.ledgerSize(group) == 4;

        //Assert
        assertTrue(scheduleTransaction && ledgerSize);
    }

    @Test
    void scheduleGroupTransactionWeekly() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

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

        Group group = new Group("tarzan");
        group.addMember(person);

        //Act
        boolean scheduleTransaction = groupScheduleController.scheduleGroupTransaction(group, person,
                "weekly", amount, description, null, category, from, to, type);
        Thread.sleep(2900); // 750 x 4 = 3000
        boolean ledgerSize = groupScheduleController.ledgerSize(group) == 4;

        //Assert
        assertTrue(scheduleTransaction && ledgerSize);
    }

    @Test
    void scheduleGroupTransactionMonthly() throws InterruptedException {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

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

        Group group = new Group("tarzan");
        group.addMember(person);

        //Act
        boolean scheduleTransaction = groupScheduleController.scheduleGroupTransaction(group, person,
                "monthly", amount, description, null, category, from, to, type);
        Thread.sleep(2900); // 1000 x 3 = 3000
        boolean ledgerSize = groupScheduleController.ledgerSize(group) == 3;

        //Assert
        assertTrue(scheduleTransaction && ledgerSize);
    }

    @Test
    void scheduleGroupTransactionNoPeriodicityMatch() {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

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

        Group group = new Group("tarzan");
        group.addMember(person);

        try {
            //Act
            groupScheduleController.scheduleGroupTransaction(group, person,
                    "everyday", amount, description, null, category, from, to, type);
        }

        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }

    @Test
    void scheduleGroupTransactionNotAMember() {
        //Arrange
        GroupScheduleController groupScheduleController = new GroupScheduleController();

        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person personMember = new Person("Jose", LocalDate.of(1995, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);
        Person personNotAMember = new Person("Joana", LocalDate.of(1996, 12, 13),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"), mom, dad);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        personMember.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");
        personMember.createAccount("Wallet", "General expenses");
        personMember.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit

        Group group = new Group("tarzan");
        group.addMember(personMember);

        try {
            //Act
            groupScheduleController.scheduleGroupTransaction(group, personNotAMember,
                    "working days", amount, description, null, category, from, to, type);
        }

        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You are not a member of that group.", result.getMessage());
        }
    }
}