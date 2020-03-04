package switch2019.project.model;

import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.category.Category;
import switch2019.project.model.ledger.Schedule;
import switch2019.project.model.valueObject.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    /**
     * testing if the keywords can be converted into milliseconds to be used later
     */

    @Test
    void convertKeyWordIntoMillisecondsDaily() {

        //Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        String periodicity = "daily";
        Schedule oneSchedule = new Schedule(person1, periodicity, monetaryValue, "agendamento", date, category, account1, account2, true );

        //Act
        int x = oneSchedule.convertKeyWordIntoMilliseconds("daily");

        //Assert
        assertEquals(250, x);
    }

    @Test
    void convertKeyWordIntoMillisecondsWeekly() {
        //Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        String periodicity = "daily";
        Schedule oneSchedule = new Schedule(person1, periodicity, monetaryValue, "agendamento", date, category, account1, account2, true );

        //Act
        int x = oneSchedule.convertKeyWordIntoMilliseconds("weekly");

        //Assert
        assertEquals(750, x);
    }

    @Test
    void convertKeyWordIntoMillisecondsWorkingDays() {
        //Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        String periodicity = "daily";
        Schedule oneSchedule = new Schedule(person1, periodicity, monetaryValue, "agendamento", date, category, account1, account2, true );

        //Act
        int x = oneSchedule.convertKeyWordIntoMilliseconds("working days");

        //Assert
        assertEquals(500, x);
    }

    @Test
    void convertKeyWordIntoMillisecondsMonthly() {
        //Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        String periodicity = "daily";
        Schedule oneSchedule = new Schedule(person1, periodicity, monetaryValue, "agendamento", date, category, account1, account2, true );

        //Act
        int x = oneSchedule.convertKeyWordIntoMilliseconds("monthly");

        //Assert
        assertEquals(1000, x);
    }

    @Test
    void convertKeyWordIntoMillisecondsNoMatch() {
        //Arrange
        Person person1 = new Person("Alex", LocalDate.of(1995, 12, 04), new Address("Lisboa"), new Address("Rua X", "Porto", "4520-266"));
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category = new Category("grocery");
        MonetaryValue monetaryValue = new MonetaryValue(200, Currency.getInstance("EUR"));
        LocalDateTime date = LocalDateTime.of(2020, 1, 13, 13, 02);
        String periodicity = "daily";
        Schedule oneSchedule = new Schedule(person1, periodicity, monetaryValue, "agendamento", date, category, account1, account2, true );

        //Act
        try {
            oneSchedule.convertKeyWordIntoMilliseconds("tomorrow");
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }

    }
}