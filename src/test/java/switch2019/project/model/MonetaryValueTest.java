package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MonetaryValueTest {

    @Test
    @DisplayName("Test for validating monetarValue Positive-true")
    void validateIfAmountIsPositive() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(2.00, Currency.getInstance("eur"));
        //Act
        boolean validateIfAmountIsPostive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertTrue(validateIfAmountIsPostive);
    }


    @Test
    @DisplayName("Test for validating monetarValue Negative-false")
    void validateIfAmountIsNegative() {
        //Arrange
        MonetaryValue monetaryValue1 = new MonetaryValue(-2.00, Currency.getInstance("eur"));
        //Act
        boolean validateIfAmountIsPostive = monetaryValue1.validateIfAmountIsPositive();

        //Assert
        assertFalse(validateIfAmountIsPostive);
    }
}