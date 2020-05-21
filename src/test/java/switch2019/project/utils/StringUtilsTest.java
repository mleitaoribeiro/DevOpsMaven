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
}