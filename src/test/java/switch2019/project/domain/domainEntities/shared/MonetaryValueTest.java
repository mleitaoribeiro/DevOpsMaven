package switch2019.project.domain.domainEntities.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MonetaryValueTest {

    /**
     * Tests to validate if an amount is positive
     */
    @Test
    @DisplayName("Test for validate if monetaryValue is positive - true")
    void validateIfAmountIsPositiveTrue() {
        //Arrange

        MonetaryValue monetaryValue1 = new MonetaryValue(2.00, Currency.getInstance("EUR"));
        //Act
        boolean validateIfAmountIsPositive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertTrue(validateIfAmountIsPositive);
    }

    @Test
    @DisplayName("Test for validate if monetaryValue is positive - false")
    void validateIfAmountIsPositiveFalse() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(-2.00, Currency.getInstance("EUR"));

        //Act
        boolean validateIfAmountIsPositive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertFalse(validateIfAmountIsPositive);
    }

    @Test
    @DisplayName("Test for validate if monetaryValue is positive - boundary test")
    void validateIfAmountIsPositiveBoundary() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(0.00, Currency.getInstance("EUR"));

        //Act
        boolean validateIfAmountIsPositive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertEquals(false, validateIfAmountIsPositive);
    }

    /**
     * Test to getAmount method
     */

    @Test
    @DisplayName("Test for getAmount method")
    void getAmount() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(5.00, Currency.getInstance("EUR"));

        //Act
        double amount = monetaryValue1.getAmount();

        //Assert
        assertEquals(5, amount);
    }


    /**
     * Test to toString method
     */

    @Test
    @DisplayName("Test for toString method")
    void monetaryValueToString() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(6.53, Currency.getInstance("EUR"));
        String stringExpected = "6.53 EUR";

        //Act
        String monetaryValueString = monetaryValue1.toString();

        //Assert
        assertEquals(stringExpected, monetaryValueString);
    }

    /**
     * Test to constructor method
     */

    @Test
    @DisplayName("Test to constructor - Null currency")
    void testCategoryIDNullOwner() {
        //Act:
        try {
            MonetaryValue monetaryValue1 = new MonetaryValue(25, null);
        }
        //Assert:
        catch (IllegalArgumentException currency) {
            assertEquals("The currency can't be null.", currency.getMessage());
        }
    }

    /**
     * Test to getCurrency method
     */

    @Test
    @DisplayName("Test for getCurrency method")
    void getCurrency() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));

        //Act
        Currency currency = monetaryValue.getCurrency();

        //Assert
        assertEquals(Currency.getInstance("EUR"), currency);
    }

}