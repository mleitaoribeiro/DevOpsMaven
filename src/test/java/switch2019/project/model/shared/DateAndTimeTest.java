package switch2019.project.model.shared;

import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.PersonName;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateAndTimeTest {

    /**
     * Validate Input for yearMonthDay
     */
    @Test
    @DisplayName("Test for validating date input - happy case")
    public void validateYearMonthDay() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1991, 05, 06);
        String expected = "1991-05-06";

        //Act
        String result = birthDate.getYearMonthDay();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Validate Input for yearMonthDayHourMinute
     */
    @Test
    @DisplayName("Test for validating date ant time input - happy case")
    public void validateYearMonthDayHourMinute() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1991, 05, 06, 00, 00);
        String expected = "1991-05-06 00:00";

        //Act
        String result = birthDate.getYearMonthDayHourMinute();

        //Assert
        assertEquals(expected, result);
    }

    //nuul


    @Test
    @DisplayName("Test for validating birth date input => invalid month ")
    public void validateBirthDateWhenMonthIsInvalid() {
        //Arrange and Act
        try {
            new DateAndTime(1991, 13, 06);
        }
        //Assert
        catch (DateTimeException description) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 13", description.getMessage());
        }
    }

}