package switch2019.project.model.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.PersonName;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.MonetaryValue;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.person.Address;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledTasksListTest {

    @Test
    @DisplayName("Add a new schedule")
    void addNewSchedule() {

        //Arrange
        ScheduledTasksList scheduledTasksList = new ScheduledTasksList();
        Person person = new Person("Jose", new DateAndTime(1995,12,13),
                new Address("Lisboa"),new Address ("Rua X", "Porto", "4520-266"));

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("General");
        person.createCategoryAndAddToCategoryList("General");
        Account from = new Account(new Denomination("Wallet"),
                new Description("General expenses"));
        Account to = new Account(new Denomination("TransportAccount"),
                new Description("Transport expenses"));
        person.createAccount("Wallet", "General expenses");
        person.createAccount("TransportAccount", "Transport expenses");


        //Act
        boolean result = scheduledTasksList.addNewSchedule(person, "daily", amount,
                description, null, category, from, to, new Type(false));

        //Assert
        assertTrue(result);
    }
}