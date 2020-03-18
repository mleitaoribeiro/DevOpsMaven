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

    @Test
    @DisplayName("Test for validating date and time input => invalid hour")
    public void validateDateWhenHourIsInvalid() {
        //Arrange and Act
        try {
            new DateAndTime(2025, 10, 03, -23, -05);
        }
        //Assert
        catch (DateTimeException description) {
            assertEquals("Invalid value for HourOfDay (valid values 0 - 23): -23", description.getMessage());
        }
    }


    /**
     * Tests to Equals Method
     */

    @Test
    @DisplayName("Test equals for same DateAndTime - yearMonthDay")
    public void equalsSameDateAndTimeYearMonthDay() {
        //Arrange
        DateAndTime birthDate1 = new DateAndTime(1989, 4, 27);
        DateAndTime birthDate2 = new DateAndTime(1989, 4, 27);

        //Act
        boolean result = birthDate1.equals(birthDate2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals for same DateAndTime - yearMonthDayHourMinute")
    public void equalsSameDateAndTimeYearMonthDayHourMinute() {
        //Arrange
        DateAndTime birthDate1 = new DateAndTime(1989, 4, 27,20,03);
        DateAndTime birthDate2 = new DateAndTime(1989, 4, 27,20,03);

        //Act
        boolean result = birthDate1.equals(birthDate2);

        //Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test equals for the same object")
    public void equalsSameObject() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27);

        //Act
        boolean result = birthDate.equals(birthDate);

        //Assert
        assertTrue(result);
    }

    /**
     * Equals Different Object Type
     */
    @Test
    @DisplayName("Test equals for different object type")
    public void equalsDifferentObjectType() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27);
        Denomination denomination = new Denomination("example");

        //Act
        boolean result = birthDate.equals(denomination);

        //Assert
        assertFalse(result);
    }

    /**
     * Equals Null Object
     */
    @Test
    @DisplayName("Test equals for null object")
    public void equalsForNullObject() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27);
        DateAndTime nullDate = null;

        //Act
        boolean result = birthDate.equals(nullDate);

        //Assert
        assertFalse(result);
    }

    /**
     * Equals hashcode LocalDate True
     */
    @Test
    @DisplayName("Test hashcode LocalDate True")
    public void validateHashcodeOnlyDateTrue() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27);
        DateAndTime birthDateCopy = new DateAndTime(1996, 4, 27);

        //Act
        boolean result = birthDate.hashCode() == (birthDateCopy.hashCode());

        //Assert
        assertTrue(result);
    }

    /**
     * Equals hashcode LocalDate False
     */
    @Test
    @DisplayName("Test hashcode LocalDate False")
    public void validateHashcodeOnlyDateFalse() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27);
        DateAndTime birthDateCopy = new DateAndTime(2000, 10, 10);

        //Act
        boolean result = birthDate.hashCode() == (birthDateCopy.hashCode());

        //Assert
        assertFalse(result);
    }

    /**
     * Equals hashcode LocalDateTime True
     */
    @Test
    @DisplayName("Test hashcode LocalDateTime True")
    public void validateHashcodeDateAndTimeTrue() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27, 10, 10);
        DateAndTime birthDateCopy = new DateAndTime(1996, 4, 27, 10, 10);

        //Act
        boolean result = birthDate.hashCode() == (birthDateCopy.hashCode());

        //Assert
        assertTrue(result);
    }

    /**
     * Equals hashcode LocalDateTime False
     */
    @Test
    @DisplayName("Test hashcode LocalDateTime False")
    public void validateHashcodeDateAndTimeFalse() {
        //Arrange
        DateAndTime birthDate = new DateAndTime(1996, 4, 27, 10, 10);
        DateAndTime birthDateCopy = new DateAndTime(2000, 1, 10, 10, 10);

        //Act
        boolean result = birthDate.hashCode() == (birthDateCopy.hashCode());

        //Assert
        assertFalse(result);
    }

    /**
     * Equals hashcode empty constructor
     */
    @Test
    @DisplayName("Test hashcode empty Constructor")
    public void validateHashcodeEmptyConstructor() {
        //Arrange
        DateAndTime birthDate = new DateAndTime();
        DateAndTime birthDateCopy = new DateAndTime();

        //Act
        boolean result = birthDate.hashCode() == (birthDateCopy.hashCode());

        //Assert
        assertTrue(result);
    }

}