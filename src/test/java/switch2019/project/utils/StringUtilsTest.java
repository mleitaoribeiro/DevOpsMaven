package switch2019.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    @DisplayName("Test to compare dates as Strings - different day")
    void isSameMinuteNextDay(){

        String dateDay = "2020-05-29 23:59";

        String dateNextDay = "2020-05-30 23:59";

        assertFalse(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - two minutes before")
    void isSameDateTwoMinutesBefore(){

        String dateDay = "2020-05-29 23:59";

        String dateNextDay = "2020-05-29 23:57";

        assertFalse(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - day before")
    void isSameDateDayBefore(){

        String dateDay = "2020-05-29 23:59";

        String dateNextDay = "2020-05-28 23:59";

        assertFalse(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - day before")
    void isSameDateOneMinuteBefore(){

        String dateDay = "2020-05-30 00:00";

        String dateNextDay = "2020-05-29 23:59";

        assertTrue(StringUtils.isSameDate(dateDay, dateNextDay));
    }


    @Test
    @DisplayName("Test to compare dates as Strings - first date and second are equals")
    void isSameDate(){

        String dateDay = "2020-05-30 23:59";

        String dateNextDay = "2020-05-30 23:59";

        assertTrue(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to compare dates as Strings - same day - one more minute")
    void isSameDateOneMoreMinute(){

        String dateDay = "2020-05-29 23:56";

        String dateNextDay = "2020-05-29 23:55";

        assertTrue(StringUtils.isSameDate(dateDay, dateNextDay));
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - True - Different Day")
    void isCorrectDateRange(){

        //Arrange
        String initialDate = "2020-01-29 20:50";
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertTrue(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test Check If a String Is a Valid Date (yyyy-MM-dd) - True")
    void isValidDate(){

        //Arrange

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(formatter);

        //Act

        boolean isValidDate = StringUtils.isValidDate(date);

        //Assert
        assertTrue(isValidDate);
    }

    @Test
    @DisplayName("Test Check If a String Is a Valid Date (yyyy-MM-dd) - False")
    void isNotValidDate(){

        //Arrange

        String date = "2020-01-29 20:50";

        //Act

        boolean isValidDate = StringUtils.isValidDate(date);

        //Assert
        assertFalse(isValidDate);
    }

    @Test
    @DisplayName("Test Check If a String Is a Valid Date And Time (yyyy-MM-dd HH:mm) - True")
    void isValidDateAndTime(){

        //Arrange

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = LocalDateTime.now().format(formatter);

        //Act

        boolean isValidDate = StringUtils.isValidDateAndTime(date);

        //Assert
        assertTrue(isValidDate);
    }

    @Test
    @DisplayName("Test Check If a String Is a Valid Date And Time (yyyy-MM-dd HH:mm) - False")
    void isNotValidDateAndTime(){

        //Arrange

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(formatter);

        //Act

        boolean isValidDate = StringUtils.isValidDateAndTime(date);

        //Assert
        assertFalse(isValidDate);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - True - Initial Date = Final Date = Date Now")
    void isCorrectDateRangeInitialDateEqualsFinalDateEqualsDateNow(){

        //Arrange

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String initialDate = LocalDateTime.now().format(formatter);

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, initialDate);

        //Assert
        assertTrue(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - True - Initial Date = Final Date")
    void isCorrectDateRangeInitialDateEqualsFinalDate(){

        //Arrange

        String initialDate = "2020-04-10 23:55";
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertTrue(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - True - Final Date = Date Now")
    void isCorrectDateRangeFinalDateEqualsDateNow(){

        //Arrange

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String initialDate = "2003-04-10 23:55";
        String finalDate = LocalDateTime.now().format(formatter);

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertTrue(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - False - Initial Date is 1 minute after Final Date")
    void isCorrectDateRangeInitialDateOneMinuteAfterFinalDate(){

        //Arrange

        String initialDate = "2020-04-10 23:56";
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - No time input in initial Date")
    void isCorrectDateRangeNoTimeInputInInitialDate(){

        //Arrange
        String initialDate = "2020-01-29";
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - No time input in initial Date")
    void isCorrectDateRangeNoTimeInputInFinalDate(){

        //Arrange
        String initialDate = "2020-04-10 23:55";
        String finalDate = "2020-04-12";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - Initial Date Null")
    void isCorrectDateRangeInitialDateNull(){

        //Arrange
        String initialDate = null;
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - Final Date Null")
    void isCorrectDateRangeFinalDateNull(){

        //Arrange
        String initialDate = "2020-04-10 23:55";
        String finalDate = null;

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - Initial Date Empty")
    void isCorrectDateRangeInitialDateEmpty(){

        //Arrange
        String initialDate = "";
        String finalDate = "2020-04-10 23:55";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - False - Invalid Date - Final Date Empty")
    void isCorrectDateRangeFinalDateEmpty(){

        //Arrange
        String initialDate = "2020-04-10 23:55";
        String finalDate = "";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - False - Initial Date > Final Date")
    void isCorrectDateRangeInitialDateAfterFinalDate(){

        //Arrange
        String initialDate = "2020-04-10 23:55";
        String finalDate = "2020-01-2 10:01";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }

    @Test
    @DisplayName("Test to check if Date Range is correct - False - Initial Date > Date Now")
    void isCorrectDateRangeInitialDateAfterDateNow(){

        //Arrange
        String initialDate = "2050-04-10 23:55";
        String finalDate = "2051-01-2 10:01";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }


    @Test
    @DisplayName("Test to check if Date Range is correct - False - Final Date > Date Now")
    void isCorrectDateRangeFinalDateAfterDateNow(){

        //Arrange
        String initialDate = "2003-04-10 23:55";
        String finalDate = "2051-01-2 10:01";

        //Act

        boolean isCorrectDateRange = StringUtils.isCorrectDateRange(initialDate, finalDate);

        //Assert
        assertFalse(isCorrectDateRange);
    }




}