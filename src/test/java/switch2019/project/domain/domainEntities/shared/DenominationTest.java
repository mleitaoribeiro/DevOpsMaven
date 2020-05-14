package switch2019.project.domain.domainEntities.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.crypto.Des;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;

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
            assertEquals("The denomination can't be null or empty.", setterEx.getMessage());
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
            assertEquals("The denomination can't be null or empty.", setterEx.getMessage());
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
    @DisplayName("Verify if two denominations are the same - different class")
    void twoDenominationAreEqualsDifferentClass() {
        //Arrange
        Denomination denomination = new Denomination("Health");
        Description description = new Description("Gym");

        //Assert
        assertNotEquals(denomination, description);
    }


    @Test
    @DisplayName("Verify if equals method verifies if object is not of the same type")
    public void testIfADenominationIsNotEqualToAnotherObject() {
        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Denomination denomination = new Denomination("Dinner");
        Group group = new Group(new Description("Friends"), person.getID());

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

    @Test
    @DisplayName("Test to method: getDenomination")
    void getDenominationValue() {
        //Arrange:
        Denomination denomination = new Denomination("Expenses");

        //Act
        String denominationResult = denomination.getDenominationValue();

        //Assert
        assertEquals("EXPENSES", denominationResult);
    }
}