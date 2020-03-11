package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.shared.Address;
import switch2019.project.model.shared.MonetaryValue;
import switch2019.project.repository.GroupsRepository;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MemberCreatesGroupTransactionControllerTest {

    /**
     * US008.1
     * As a group member, i want to create a group transaction by atribuing a value, a description, a category,
     * a debit account and a credit account
     *
     */
    @Test
    @DisplayName("Test if a group transaction was created - success case")
    void createGroupTransactionSuccessCase() {
        //Arrange
        MemberCreatesGroupTransactionController groupTransaction = new MemberCreatesGroupTransactionController();

        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);

        GroupsRepository groupList1 = new GroupsRepository();
        groupList1.createGroup("Test group", person3);
        Group group1 = new Group("Test group");
        group1.addMember(person4);

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        boolean transactionCreated = groupTransaction.memberCreatesAGroupTransaction(amount, description, null,
                category, from, to, type, group1, person4);

        //Assert
        assertTrue(transactionCreated);
    }

    @Test
    @DisplayName("Test if a group transaction was created - monetary value is negative")
    void createGroupTransactionAccountNegativeMonetaryValue() {
        //Arrange
        MemberCreatesGroupTransactionController groupTransaction = new MemberCreatesGroupTransactionController();

        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);

        GroupsRepository groupList1 = new GroupsRepository();
        groupList1.createGroup("Test group", person3);
        Group group1 = new Group("Test group");
        group1.addMember(person4);

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description1 = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        try {
            groupTransaction.memberCreatesAGroupTransaction(amountNegative, description1, null, category, from,
                    to, type, group1, person4);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if a group transaction was created - transaction null")
    void createGroupTransactionNull() {
        //Arrange
        MemberCreatesGroupTransactionController groupTransaction = new MemberCreatesGroupTransactionController();

        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);

        GroupsRepository groupList1 = new GroupsRepository();
        groupList1.createGroup("Test group", person3);
        Group group1 = new Group("Test group");
        group1.addMember(person4);

        //Act
        try {
            groupTransaction.memberCreatesAGroupTransaction(null, null, null, null, null,
                    null, false, group1, person4);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The monetary value can´t be null or negative. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if a transaction was created - person is not a member of the group")
    void createGroupTransactionNotMember() {
        //Arrange
        MemberCreatesGroupTransactionController groupTransaction = new MemberCreatesGroupTransactionController();

        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person2 = new Person("Francisca", LocalDate.of(2000, 12, 12), new Address("Lisboa"),
                new Address("Rua dos Flores", "Porto", "4450-852"));
        Person person3 = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);
        Person person4 = new Person("Francisco", LocalDate.of(1995, 12, 13), new Address("Lisboa"),
                new Address("Rua X", "Porto", "4520-266"), person2, person1);

        GroupsRepository groupList1 = new GroupsRepository();
        groupList1.createGroup("Test group", person3);
        Group group1 = new Group("Test group");

        if (group1.isGroupAdmin(person3)) {
            group1.createGroupAccount("Wallet", "General expenses");
            group1.createGroupAccount("TransportAccount", "Transport expenses");
        }

        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("TransportAccount", "Transport expenses");

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));

        String description = "payment";

        Category category = new Category("General");
        person4.createCategoryAndAddToCategoryList("General");

        boolean type = false; //debit

        //Act
        boolean transactionCreated = groupTransaction.memberCreatesAGroupTransaction(amount, description, null,
                category, from, to, type, group1, person4);

        //Assert
        assertFalse(transactionCreated);
    }

}