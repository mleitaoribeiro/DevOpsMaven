package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US011ControllerTest {

    @Test
    @DisplayName("Test if a person get their movements in a given period - success case - one transaction -  US011")
    void returnPersonLedgerFromPeriodSuccessCaseOneTransaction() {
        //Arrange
        Person person = new Person("Jose", LocalDate.of(1995, 12, 13), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));

        LocalDateTime dateTransaction1 = LocalDateTime.of(2020, 1, 14, 13, 00);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account("Wallet", "General expenses");
        Account to = new Account("Account2", "Transport expenses");
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");
        boolean type = false; //debit
        person.createTransaction(amount, "payment", dateTransaction1, category, from, to, type);

        LocalDateTime initialDate = LocalDateTime.of(2020, 1, 13, 23, 00);
        LocalDateTime finalDate = LocalDateTime.of(2020, 1, 20, 23, 00);

        Transaction transaction1 = new Transaction(amount, "payment", dateTransaction1, category, from, to, type);
        ArrayList<Transaction> expectedResult = new ArrayList<>();
        expectedResult.add(transaction1);

        //Act
       // ArrayList<Transaction> personLedgerMovements = US011.returnPersonLedgerFromPeriod(initialDate, finalDate);

        //Assert
       // assertEquals(personLedgerMovements, expectedResult);

    }
}
