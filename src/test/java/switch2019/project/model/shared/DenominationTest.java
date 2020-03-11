package switch2019.project.model.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.group.Group;

import static org.junit.jupiter.api.Assertions.*;

class DenominationTest {

    @Test
    @DisplayName("Validate denomination - Case Insenstive")
    void validateDenominationUpperCaseInsensitive() {
        //Arrange
        Denomination denomination = new Denomination("Health");
        String expected = ("HEALTH");

        //Act
        String real = denomination.toString();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Validate Denomination - Numeric Descriptions Acceptable")
    void validateDenominationValidateNumericInput() {
        //Arrange
        Denomination denomination = new Denomination("123");
        String expected = "123";

        //Act
        String real = denomination.toString();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Validate Denomination - Ignore Special Characters")
    void validateDenominationSpecialCharacter() {
        //Arrange
        Denomination denomination = new Denomination("saúde");
        String expected = "SAUDE";

        //Act
        String real = denomination.toString();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Validate Denomination description - null case")
    void validateDenominationNullCase() {
        try {
            //Arrange & Act
            Denomination denomination = new Denomination(null);
            fail();
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The denomination can´t be null or empty!", setterEx.getMessage());
        }
    }

    @Test
    @DisplayName("Validate Denomination - empty case")
    void validateEmptyDenomination() {
        try {
            //Arrange & Act
            Denomination denomination = new Denomination("");
            fail();
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The denomination can´t be null or empty!", setterEx.getMessage());
        }
    }

    /**
     * Check if two denomination are the same
     */
    @Test
    @DisplayName("Verfiy if two denomination are the same - ignore case - Main Scenario")
    void twoDenominationAreEqualsIgnoreCase() {
        //Arrange
        Denomination denomination = new Denomination("Health");
        Denomination otherDenomination = new Denomination("HEALTH");

        //Act
        boolean realResult = denomination.equals(otherDenomination);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two denomination are the same - ignore special Character - Main Scenario")
    void twoDenominationAreEqualsIgnoreSpecialCharacter() {
        //Arrange
        Denomination denomination = new Denomination("cão");
        Denomination otherDenomination = new Denomination("cao");

        //Act
        boolean realResult = denomination.equals(otherDenomination);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two denomination are the same - numeric characters - Main Scenario")
    void twoDenominationAreEqualsNumericCategories() {
        //Arrange
        Denomination denomination = new Denomination("01Denomination");
        Denomination otherDenomination = new Denomination("01DENOMINATION");

        //Act
        boolean realResult = denomination.equals(otherDenomination);

        //Assert
        assertEquals(true, realResult);
    }

    @Test
    @DisplayName("Verfiy if two denominations are the same - Not the same")
    void twoCategoriesAreNotTheSame() {
        //Arrange
        Denomination denomination = new Denomination("Health");
        Denomination otherDenomination = new Denomination("Education");

        //Act
        boolean realResult = denomination.equals(otherDenomination);

        //Assert
        assertEquals(false, realResult);
    }

    @Test
    @DisplayName("Verfiy if two denominations are the same - same object")
    void twoDenominationAreEqualsSame() {
        //Arrange
        Denomination denomination = new Denomination("Health");

        //Assert
        assertEquals(denomination, denomination);
    }



    @Test
    @DisplayName("Verify if equals method verifies if object is not of the same type")
    public void testIfADenominationIsNotEqualToAnotherObject() {
        //Arrange:
        Denomination denomination = new Denomination("Dinner");
        Group group = new Group("Friends");

        //Act:
        boolean result = denomination.equals(group);

        //Assert:
        assertEquals(false, result);
    }

    /**
     * Test if two categories have the same Hashcode
     */

    @Test
    @DisplayName("Test if two denomination are the same - same HashCode")
    public void testIfTwoDenominationHaveTheSameHashCodeSame() {

        //Arrange & Act
        Denomination denomination = new Denomination("Health");
        Denomination otherDenomination = new Denomination("Health");

        //Assert
        assertEquals(denomination.hashCode(), otherDenomination.hashCode());
    }

    @Test
    @DisplayName("Test if two denominations are the same - not the same HashCode")
    public void testIfTwoDenominationHaveTheSameHashCodeNotSame() {

        //Arrange & Act
        Denomination denomination = new Denomination("Health");
        Denomination otherDenomination = new Denomination("Education");

        //Assert
        assertNotEquals(denomination.hashCode(), otherDenomination.hashCode());
    }

}