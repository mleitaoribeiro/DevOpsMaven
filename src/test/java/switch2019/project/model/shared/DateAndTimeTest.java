package switch2019.project.model.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;

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

    @Test
    @DisplayName("Test for validating birth date input => invalid month ")
    public void validateBirthDateWhenMonthIsInvalid() {
        //Arrange and Act
        try {
            new DateAndTime(-2020, -2, -2);
        }
        //Assert
        catch (DateTimeException description) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): -2", description.getMessage());
        }
    }


    @Test
    @DisplayName("Test for validating date input => invalid month ")
    public void validateDateWhenMonthIsInvalid() {
        //Arrange and Act
        try {
            new DateAndTime(2025, 13, 35);
        }
        //Assert
        catch (DateTimeException description) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 13", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test for validating date input => invalid day")
    public void validateDateWhenDayIsInvalid() {
        //Arrange and Act
        try {
            new DateAndTime(2025, 10, 35);
        }
        //Assert
        catch (DateTimeException description) {
            assertEquals("Invalid value for DayOfMonth (valid values 1 - 28/31): 35", description.getMessage());
        }
    }

}