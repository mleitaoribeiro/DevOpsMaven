package switch2019.project.model.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.MonetaryValue;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduledTasksListTest {

    @Test
    @DisplayName("Add a new schedule")
    void addNewSchedule() {

        //Arrange
        ScheduledTasksList scheduledTasksList = new ScheduledTasksList();
        Ledger ledger = new Ledger();

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));

        //Act
        boolean result = scheduledTasksList.addNewSchedule(ledger, new Periodicity("daily"), amount,
                description, null, category, from, to, new Type(false));

        //Assert
        assertTrue(result);
    }
}