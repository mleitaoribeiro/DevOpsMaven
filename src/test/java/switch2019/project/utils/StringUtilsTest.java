package switch2019.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isEmailTrue() {

        // Arrange & Act
        boolean result = StringUtils.isEmail("marta@isep.ipp.pt");

        // Assert
        assertTrue(result);
    }

    @Test
    void isEmailFalse() {

        // Arrange & Act
        boolean result = StringUtils.isEmail("marta.isep.ipp.pt");

        // Assert
        assertFalse(result);
    }

    @Test
    void removeExtraSpaces() {

        // Arrange
        String expected = "marta pinheiro";

        // Act
        String result = StringUtils.removeExtraSpaces("    marta    pinheiro   ");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void normalizePersonName() {

        // Arrange
        String expected = "Marta Gomes de Lemos Pinheiro";

        // Act
        String result = StringUtils.normalizePersonName("    maRTa    GOmes de  lemOS    piNHEiro   ");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void normalizeDenomination() {

        // Arrange
        String expected = "GAMING SPACE";

        // Act
        String result = StringUtils.normalizeDenomination("&´Gaming#!%()`    SPácé <@;");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if string is converted to date and time")
    void toDateAndTimeTrue() {
        //Arrange
        DateAndTime expectedDate = new DateAndTime(2020, 5, 21);

        //Act
        DateAndTime resultDate = StringUtils.toDateAndTime("2020-05-21");

        //Assert
        assertEquals(expectedDate, resultDate);
    }

    @Test
    @DisplayName("Test if string is converted to date and time")
    void toDateAndTimeFalse() {
        //Arrange
        DateAndTime expectedDate = new DateAndTime(2020, 5, 21);

        //Act
        DateAndTime resultDate = StringUtils.toDateAndTime("2000-05-21");

        //Assert
        assertNotEquals(expectedDate, resultDate);
    }

    @Test
    @DisplayName("Test if string is converted to date and time with hour and minute")
    void toDateHourMinute() {
        //Arrange
        DateAndTime expectedDate = new DateAndTime(2020, 1, 13, 11, 00);

        //Act
        DateAndTime resultDate = StringUtils.toDateHourMinute("2020-01-13 11:00");

        //Assert
        assertEquals(expectedDate, resultDate);
    }

    @Test
    @DisplayName("Test if string is converted to date and time with hour and minute")
    void toDateHourMinuteFalse() {
        //Arrange
        DateAndTime expectedDate = new DateAndTime(2020, 1, 13, 11, 00);

        //Act
        DateAndTime resultDate = StringUtils.toDateHourMinute("2021-01-13 11:00");

        //Assert
        assertNotEquals(expectedDate, resultDate);
    }

    @Test
    @DisplayName("Test to compare dates as Strings, one minute before, true")
    void isSameDateOne(){

        String date = "2020-05-29 16:55";

        String date2 = "2020-05-29 16:56";

        assertTrue(StringUtils.isSameDate(date, date2));

    }

    @Test
    @DisplayName("Test to compare dates as Strings, one minute after, true")
    void isSameDateTwo(){

        String date3 = "2020-05-29 16:57";

        String date2 = "2020-05-29 16:56";

        assertTrue(StringUtils.isSameDate(date3, date2));

    }

    @Test
    @DisplayName("Test to compare dates as Strings - two minutes apart, false")
    void isSameDateThree(){

        String date = "2020-05-29 16:55";

        String date3 = "2020-05-29 16:57";

        assertFalse(StringUtils.isSameDate(date, date3));

    }

    @Test
    @DisplayName("Test to compare dates as Strings - different day")
    void isSameDateNextDay(){

        String dateDay = "2020-05-29 23:59";

        String dateNextDay = "2020-05-30 00:00";

        assertTrue(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - System date")
    void isSameDateSysDate(){

        String sysDateNow = new DateAndTime().yearMonthDayHourMinuteToString();

        String sysDateNow2 = new DateAndTime().yearMonthDayHourMinuteToString();

        assertTrue(StringUtils.isSameDate(sysDateNow, sysDateNow2));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - different day")
    void isSameDateNextDayFalse(){

        String dateDay = "2020-05-29 23:59";

        String dateNextDay = "2020-05-30 00:01";

        assertFalse(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - same day - false")
    void isSameDateSameDayFalse(){

        String dateDay = "2020-05-29 23:55";

        String dateNextDay = "2020-05-29 23:57";

        assertFalse(StringUtils.isSameDate(dateDay, dateNextDay));
    }



}