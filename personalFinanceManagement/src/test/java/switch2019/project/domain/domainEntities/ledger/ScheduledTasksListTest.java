package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduledTasksListTest {

    @Test
    @DisplayName("Add a new schedule")
    void addNewSchedule() {

        //Arrange
        ScheduledTasksList scheduledTasksList = new ScheduledTasksList();

        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        CategoryID category = new CategoryID(new Denomination("General"),new PersonID(new Email("personEmail@email.com")));
        AccountID from = new AccountID(new Denomination("Wallet"), new PersonID(new Email("personEmail@email.pt")));
        AccountID to = new AccountID(new Denomination("TransportAccount"), new PersonID(new Email("personEmail@email.pt")));

        //Act
        boolean result = scheduledTasksList.addNewSchedule(ledger, new Periodicity("daily"), amount,
                new Description(description), null, category, from, to, new Type(false));

        //Assert
        assertTrue(result);
    }
}