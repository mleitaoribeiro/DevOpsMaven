package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.valueObject.MonetaryValue;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MonetaryValueTest {

    /**
     * Validate if an amount is positive
     */
    @Test
    @DisplayName("Test for validating monetaryValue Positive-true")
    void validateIfAmountIsPositive() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(2.00, Currency.getInstance("EUR"));
        //Act
        boolean validateIfAmountIsPostive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertTrue(validateIfAmountIsPostive);
    }


    @Test
    @DisplayName("Test for validating monetaryValue Negative-false")
    void validateIfAmountIsNegative() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(-2.00, Currency.getInstance("EUR"));
        //Act
        boolean validateIfAmountIsPositive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertFalse(validateIfAmountIsPositive);
    }

    @Test
    @DisplayName("Test for validating monetaryValue Positive-boundaryTest")
    void validateIfAmountIsPositiveBoundary() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(0.00, Currency.getInstance("EUR"));

        //Act
        boolean validateIfAmountIsPositive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertEquals(false, validateIfAmountIsPositive);
    }

}