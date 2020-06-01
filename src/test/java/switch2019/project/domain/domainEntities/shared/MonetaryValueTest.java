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

    @Test
    @DisplayName("Test equals same object")
    void testEquals() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue1 = new MonetaryValue(5.00, Currency.getInstance("EUR"));

        //Assert
        assertEquals(monetaryValue, monetaryValue1);
    }

    @Test
    @DisplayName("Test equals same object")
    void testEqualsSameObject() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));
        //Assert
        assertEquals(monetaryValue, monetaryValue);
    }

    @Test
    @DisplayName("Test equals null object")
    void testEqualsNullObject() {
        //Arrange
        MonetaryValue monetaryValueNull = null;
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));

        //Assert
        assertNotEquals(monetaryValueNull, monetaryValue);
    }

    @Test
    @DisplayName("Test equals different object")
    void testEqualsDifferentObject() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue1 = new MonetaryValue(5.00, Currency.getInstance("USD"));
        MonetaryValue monetaryValue2 = new MonetaryValue(10.00, Currency.getInstance("EUR"));

        //Act
        boolean result = monetaryValue.equals(monetaryValue1);
        boolean result2 = monetaryValue.equals(monetaryValue2);

        //Assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Test equals different class object")
    void testEqualsDifferentClassObject() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));

        Currency currency = Currency.getInstance("EUR");

        //Act
        boolean result = monetaryValue.equals(currency);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashcode() {
        //Arrange
        MonetaryValue monetaryValue = new MonetaryValue(5.00, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue1 = new MonetaryValue(5.00, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(10.00, Currency.getInstance("EUR"));
        //Assert
        assertEquals(monetaryValue.hashCode(), monetaryValue1.hashCode());
        assertNotEquals(monetaryValue.hashCode(), monetaryValue2.hashCode());
    }

}