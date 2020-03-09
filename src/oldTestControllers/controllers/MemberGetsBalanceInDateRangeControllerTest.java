package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.valueObject.Address;
import switch2019.project.model.valueObject.MonetaryValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MemberGetsBalanceInDateRangeControllerTest {

    /**
     * US018
     * Tests to evaluate getGroupBalanceInDateRange
     */

    @Test
    @DisplayName("Happy Case - Get group balance between dates")
    void getGroupBalanceBetweenDates(){
        //ARRANGE:
            //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

            //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

            //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

            //Add groupMember(person) to group:
        testGroup.addMember(groupMember);

            //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

            //Arrange Search Dates:

        LocalDateTime fromDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime toDate = LocalDateTime.of(2011, 1, 1, 0, 0, 30);

        // ACT:
        double groupBalance = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember);
        double expected = 200;

        // ASSERT:
        assertEquals(expected,groupBalance);
    }

    @Test
    @DisplayName("Happy Case - Dates get reordered")
    void getGroupBalanceBetweenDatesWithDatesReorder(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add groupMember(person) to group:
        testGroup.addMember(groupMember);

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime toDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime fromDate = LocalDateTime.of(2011, 1, 1, 0, 0, 30);

        // ACT:
        double groupBalance = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember);
        double expected = 200;

        // ASSERT:
        assertEquals(expected,groupBalance);
    }

    @Test
    @DisplayName( "Person is not member")
    void getGroupBalanceBetweenDatesNotMember(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime fromDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime toDate = LocalDateTime.of(2011, 1, 1, 0, 0, 30);

        //ACT && ASSERT:
        try {
             balanceController.getGroupBalanceInDateRange(fromDate, toDate, testGroup, groupMember);
        }
        catch (IllegalArgumentException description){
            assertEquals("The group member is not valid.",description.getMessage());
        }
    }

    @Test
    @DisplayName("Happy Case - veryfy if different group members can get group balance between dates ")
    void getGroupBalanceBetweenDatesByMultipleMembers(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 9, 11),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Person groupMember2 = new Person("Jessica", LocalDate.of(1990, 5, 22),
                new Address("Porto"), new Address("Rua y", "Vila Nova de Gaia", "4520-266"));
        Person groupMember3 = new Person("Johnathan", LocalDate.of(2003, 7, 28),
                new Address("Porto"), new Address("Rua y", "Vila Nova de Gaia", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add groupMember(person) to group:
        testGroup.addMember(groupMember);
        testGroup.addMember(groupMember2);
        testGroup.addMember(groupMember3);

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime fromDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime toDate = LocalDateTime.of(2011, 1, 1, 0, 0, 30);

        // ACT:
        double groupBalance = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember);
        double groupBalanceByMember2 = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember2);
        double groupBalanceByMember3 = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember3);
        double expected = 200;

        boolean areAllObtained = ((groupBalance == groupBalanceByMember2) && (groupBalanceByMember2 == groupBalanceByMember3)
        && groupBalanceByMember3 == expected);
        // ASSERT:
        assertTrue(areAllObtained);
    }

    @Test
    @DisplayName("Happy Case - Results in negative balance")
    void getGroupBalanceBetweenDatesNegativeResult(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add groupMember(person) to group:
        testGroup.addMember(groupMember);

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime toDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime fromDate = LocalDateTime.of(2012, 1, 1, 0, 0, 30);

        // ACT:
        double groupBalance = balanceController.getGroupBalanceInDateRange(fromDate,toDate,testGroup,groupMember);
        double expected = -700;

        // ASSERT:
        assertEquals(expected,groupBalance);
    }

    @Test
    @DisplayName("Try to use method on a empty Ledger")
    void getEmptyLedger() {

        //ASSERT:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Group testGroup = new Group("Test Group");

            // Member added to Group:
        testGroup.addMember(groupMember);

            // Arrange search dates:
        LocalDateTime toDate = LocalDateTime.of(2010, 1, 1, 0, 0, 30);
        LocalDateTime fromDate = LocalDateTime.of(2012, 1, 1, 0, 0, 30);

        //ACT && ASSERT
        try {
            balanceController.getGroupBalanceInDateRange(fromDate, toDate, testGroup, groupMember);
        }
        catch (IllegalArgumentException description){
            assertEquals("The ledger is Empty.",description.getMessage());
        }
    }

    @Test
    @DisplayName("Happy Case - Get group balance between null dates")
    void getGroupBalanceBetweenNullDates(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add groupMember(person) to group:
        testGroup.addMember(groupMember);

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;

        //ACT && ASSERT:
        try {
            balanceController.getGroupBalanceInDateRange(fromDate, toDate, testGroup, groupMember);
        }
        catch (IllegalArgumentException description){
            assertEquals("One of the submitted dates is not valid.",description.getMessage());
        }
    }

    @Test
    @DisplayName("Happy Case - Get group balance between null dates")
    void getGroupBalanceBetweenDateAfterCurrentDate(){
        //ARRANGE:
        //Create object from controller class:
        MemberGetsBalanceInDateRangeController balanceController = new MemberGetsBalanceInDateRangeController();

        //Arrange transactions:
        MonetaryValue monetaryValue1 = new MonetaryValue(250, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(125, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(175, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        LocalDateTime localDateTime1 = LocalDateTime.of(2010, 3, 20, 13, 12, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2010, 4, 21, 8, 47, 40);
        LocalDateTime localDateTime3 = LocalDateTime.of(2010, 7, 3, 20, 15, 45);
        LocalDateTime localDateTime4 = LocalDateTime.of(2011, 3, 10, 23, 20, 50);

        Category category1 = new Category("grocery");

        Account account1 = new Account("groceries", "mercearia Continente");
        Account account2 = new Account("groceries", "mercearia Pingo Doce");

        boolean creditType = true; //Credit from account 1 to account 2
        boolean debitType = false; //Debit from account 1 to account 2

        //Arrange Person and Group:
        Person groupMember = new Person("Francis", LocalDate.of(1999, 7, 22),
                new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        Group testGroup = new Group("Test Group");

        //Add groupMember(person) to group:
        testGroup.addMember(groupMember);

        //Add transactions to the group's Ledger:
        testGroup.createGroupTransaction(monetaryValue1,"onions",
                localDateTime1,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue2,"potato chips",
                localDateTime2,category1,account1,account2,creditType);

        testGroup.createGroupTransaction(monetaryValue3,"beer",
                localDateTime3,category1,account1,account2,debitType);

        testGroup.createGroupTransaction(monetaryValue4,"oranges",
                localDateTime4,category1,account1,account2,debitType);

        //Arrange Search Dates:

        LocalDateTime fromDate = LocalDateTime.of(2022, 3, 20, 13, 12, 30);
        LocalDateTime toDate = LocalDateTime.of(2023, 3, 20, 13, 12, 30);

        //ACT && ASSERT:
        try {
            balanceController.getGroupBalanceInDateRange(fromDate, toDate, testGroup, groupMember);
        }
        catch (IllegalArgumentException description){
            assertEquals("One of the submitted dates is not valid.",description.getMessage());
        }
    }

}