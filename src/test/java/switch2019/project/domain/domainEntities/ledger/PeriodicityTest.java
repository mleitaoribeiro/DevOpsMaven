package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicityTest {

    /**
     * testing if the keywords can be converted into milliseconds to be used later
     */

    @Test
    void convertKeyWordIntoMillisecondsDaily() {
        //Arrange & Act
        Periodicity periodicity = new Periodicity("daily");
        int expected = 250;
        int actual = periodicity.getPeriodicityInMilliseconds();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertKeyWordIntoMillisecondsWeekly() {
        //Arrange & Act
        Periodicity periodicity = new Periodicity("weekly");
        int expected = 750;
        int actual = periodicity.getPeriodicityInMilliseconds();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertKeyWordIntoMillisecondsWorkingDays() {
        //Arrange & Act
        Periodicity periodicity = new Periodicity("working days");
        int expected = 500;
        int actual = periodicity.getPeriodicityInMilliseconds();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertKeyWordIntoMillisecondsMonthly() {
        //Arrange & Act
        Periodicity periodicity = new Periodicity("monthly");
        int expected = 1000;
        int actual = periodicity.getPeriodicityInMilliseconds();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertKeyWordIntoMillisecondsNoMatch() {
        //Arrange & Act
        try {
            new Periodicity("tomorrow");
        }
        //Assert
        catch (IllegalArgumentException result) {
            assertEquals("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.", result.getMessage());
        }
    }
}