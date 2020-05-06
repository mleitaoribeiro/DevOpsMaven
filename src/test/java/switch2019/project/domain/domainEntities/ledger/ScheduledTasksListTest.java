package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;
import switch2019.project.domain.domainEntities.shared.PersonID;

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
        Category category = new Category(new Denomination("General"),new PersonID(new Email("personEmail@email.com")));
        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"), new PersonID(new Email("personEmail@email.pt")));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        boolean result = scheduledTasksList.addNewSchedule(ledger, new Periodicity("daily"), amount,
                new Description(description), null, category, from, to, new Type(false));

        //Assert
        assertTrue(result);
    }
}