package switch2019.project.model.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.PersonName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class AddressTest {

    /**
     * Test - Validate input for Street
     */

    @Test
    @DisplayName("Validate Home Address - Happy Case")
    void validateHomeAddress() {
        //Arrange
        Address homeAddress = new Address("Rua das Flores", "Porto", "4450-632");
        String expectedHomeAddress = "RUA DAS FLORES, PORTO, 4450-632";

        //Act
        String realHomeAddress = homeAddress.toString();

        //Assert
        assertEquals(expectedHomeAddress, realHomeAddress);

    }


    @Test
    @DisplayName("Validate home Address - null Street Case")
    void validateHomeAddressNullStreet() {
        try { //Act and Arrange
            Address homeAddress = new Address(null, "Porto", "4450-362");
            fail();
        }
        //Assert
        catch (IllegalArgumentException street) {
            assertEquals("The street format in your Address is not valid or it's missing. Please try again", street.getMessage());
        }
    }
    @Test
    @DisplayName("Validate home Address - empty Street Case")
    void validateHomeAddressEmptyStreet() {
        try {
            //Act and Arrange
            Address homeAddress = new Address("", "Porto", "4450-362");
            fail();
        }
        //Assert
        catch (IllegalArgumentException street) {
            assertEquals("The street format in your Address is not valid or it's missing. Please try again", street.getMessage());
        }
    }

    @Test
    @DisplayName("Validate Home Address - Street Case Insensitive")
    void validateHomeAddressStreetCaseInsensitive() {
        //Arrange
        Address homeAddress = new Address("rua de miragaia", "Porto", "4450-632");
        String expectedHomeAdress ="RUA DE MIRAGAIA, PORTO, 4450-632";

        //Act
        String realHomeAddress = homeAddress.toString();

        //Assert
        assertEquals(expectedHomeAdress, realHomeAddress);

    }
    @Test
    @DisplayName("Validate home Address - City Numeric Case")
    void validateHomeAddressWithNumeric() {
        try {
            //Arrange And Act
            Address homeAddress = new Address("Rua dos Passarinhos", "124", "4450-362");
            fail();
        }
        //Assert
        catch (IllegalArgumentException street) {
            assertEquals("The city in your Address is not valid or it's missing. Please try again.", street.getMessage());
        }
    }

    @Test
    @DisplayName("Validate Home Address - City null")
    void validateHomeAddressCityNull() {
        try {
            //Arrange And Act
            Address homeAddress = new Address("Rua dos Passarinhos", null, "4450-362");
            fail();
        }
        //Assert
        catch (IllegalArgumentException city) {
            assertEquals("The city in your Address is not valid or it's missing. Please try again.", city.getMessage());
        }
    }

    @Test
    @DisplayName("Validate Home Address - City null")
    void validateHomeAddressCityEmpty() {
        try {
            //Arrange And Act
            Address homeAddress = new Address("Rua dos Passarinhos", "", "4450-362");
            fail();
        }
        //Assert
        catch (IllegalArgumentException city) {
            assertEquals("The city in your Address is not valid or it's missing. Please try again.", city.getMessage());
        }
    }

    @Test
    @DisplayName("Validate Home Address - City case insensitive")
    void validateHomeAddresCityCaseInsensitive() {
        //Arrange
        Address homeAddress = new Address("Avenida da República", "porto", "4430-444");
        String expected = "AVENIDA DA REPÚBLICA, PORTO, 4430-444";
        //Act
       String real = homeAddress.toString();
        //Assert
        assertEquals(expected, real);
    }

    /**
     * Test - Validate input for Postal-Code
     */

    @Test
    @DisplayName("validate input for Postal-Code - with(-) ")
    public void validateHomeAddressPostalCodeFormatt() {
        //Arrange
        Address homeAddress = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String expectedHomeAddress = "RUA DA VINHA DA BOUÇA, PORTO, 4430-444";

        //Act
        String realHomeAddress = homeAddress.toString();

        //Assert
        assertEquals(expectedHomeAddress, realHomeAddress);
    }

    @Test
    @DisplayName("Validate home address - input for postal code - without (-) ")
    public void validateHomeAddressPostalCodeNotStandard() {
        //Arrange
        Address homeAddres = new Address("Rua da Vinha da Bouça", "Porto", "4430444");
        String expectedHomeAddress = "RUA DA VINHA DA BOUÇA, PORTO, 4430-444";

        //Act
        String realHomeAddress = homeAddres.toString();

        //Assert
        assertEquals(expectedHomeAddress, realHomeAddress);
    }


    @Test
    @DisplayName("Postal code not valid - test exception - Null")
    public void validateHomeAddressValidatePostalCodeNullexception() {
        //Arrange
        try {
            //Act
            Address address1 = new Address("Rua da Vinha da Bouça", "Porto", null);
            fail();
        }
        //Assert
        catch (IllegalArgumentException postalCode) {
            assertEquals("Postal Code can´t be null! (Correct Format: xxxx-xxx)", postalCode.getMessage());
        }
    }

    @Test
    @DisplayName("Postal code not valid - postal code with more than 7 char - test exception")
    public void postalCodeWithLenghtHigherThanSeven() {
        try {
            //Arrange And Act
            Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "44300945");
            fail();
        }
        //Assert
        catch (IllegalArgumentException postalCode) {
            assertEquals("Postal Code is not in the correct format! (xxxx-xxx)", postalCode.getMessage());
        }
    }

    @Test
    @DisplayName("Postal code not valid - emptyCase")
    public void postalCodeEmptyException() {
        try {
            //Arrange And Act
            Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "");
            fail();
        }
        //Assert
        catch (IllegalArgumentException postalCode) {
            assertEquals("Postal Code can´t be null! (Correct Format: xxxx-xxx)", postalCode.getMessage());
        }
    }

    /**
     * Test Equals method for the Address class
     */

    @Test
    @DisplayName("Testing if two Addresses are equal - happy case")
    void sameAddressHappyCase() {
        //Arrange
        Address address1 = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address address2 = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertTrue(address1.equals(address2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - different streets")
    void sameAddressDifferentStreets() {
        //Arrange
        Address address1 = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address address2 = new Address("Rua de S.Tome, 133", "Porto", "4050-262");

        //Act and Assert
        assertFalse(address1.equals(address2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - different cities")
    void sameAddressDifferentCities() {
        //Arrange
        Address house1 = new Address("Avenida da Republica", "Porto", "4050-262");
        Address house2 = new Address("Avenida da Republica", "Gaia", "4050-262");

        //Act and Assert
        assertFalse(house1.equals(house2));
    }


    @Test
    @DisplayName("Testing if two Addresses are equal - one address with null parameters")
    void sameAddressNullParameterAddress() {
        //Arrange
        Address address1 = new Address("Rua das Flores, 36", "Porto", "4450-897");
        Address address2 = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertFalse(address1.equals(address2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - lowCase and upperCase")
    void sameAddressUpperCaseLowerCase() {
        //Arrange
        Address address1= new Address("rua das Flores, 36", "porto", "4050-262");
        Address address2 = new Address("Rua das flores, 36", "PortO", "4050-262");

        //Act and Assert
        assertTrue(address1.equals(address2));
    }



    /**
     * Test is to address are the same -  Equals method
     */

    @Test
    @DisplayName("Test to validate if the address is the same - Same object")
    void testEqualsSameAddressSameObject() {
        //Arrange
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");

        //Act
        boolean realResult = address1.equals(address1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Test to validate if the address is the same - Other object")
    void testEqualsSameAddressOtherObject() {
        //Arrange
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");
        Address address2 = new Address("Rua da Belgica", "Gaia", "4050-262");

        //Act
        boolean realResult = address1.equals(address2);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Test to validate if the address is the same - Other object of different Class")
    public void testEqualsSameAddressOtherObjectofDifferentClass() {
        //Arrange
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");
        Person person1 = new Person(new PersonName("Miguel"), LocalDate.of(1990, 12,04), new Address("Porto"),new Address ("Rua das Flores","Porto","4450-230"));

        //Act
        boolean result = address1.equals(person1);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test to validate if the address is the same - comparison with a Null Address")
    void testEqualsNullAddress() {
        //Arrange
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");
        Address address2 = null;

        //Act
        boolean realResult = address1.equals(address2);

        //Assert
        assertFalse(realResult);
    }


    /**
     * Test if two addresses have the same Hashcode
     */

    @Test
    @DisplayName("Test if two addresses have the same Hashcode - True")
    public void testIfTwoAddressesHaveTheSameHashCode() {

        //Arrange & Act
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");
        Address address2 = new Address("Rua da Belgica", "Gaia", "4050-262");


        //Assert
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    @DisplayName("Test if two addresses have the same Hashcode - Not The Same")
    public void testIfTwoAddressesDontHaveTheSameHashCode() {

        //Arrange & Act
        Address address1 = new Address("Rua da Belgica", "Gaia", "4050-262");
        Address address2 = new Address("Avenida da Republica", "Gaia", "4050-262");

        //Assert
        assertNotEquals(address1.hashCode(), address2.hashCode());
    }

    /**
     * Test for the constructor of the birthPlace
     */
    @Test
    @DisplayName("Test for the constructor of the birthPlace")
    public void testForTheConstructorOfTheBirthPlace() {

        //Arrange
        Address birthPlace = new Address("Matosinhos");
        String expected= "MATOSINHOS";

        //Act
        String real = birthPlace.getBirthPlace();

        //Assert
        assertEquals(expected,real);
    }

    @Test
    @DisplayName("to String tested - Success")
    void homeAddressToStringTest() {

        //Arrange:
        Address addressOne = new Address("Rua das Flores", "Porto", "4430-098");

        //Act:
        String homeAddressInString = addressOne.toString();
        String expected = "RUA DAS FLORES, PORTO, 4430-098";

        //Assert:
        assertEquals(expected, homeAddressInString);
    }

    /**
     * Test to check if birtplaceToString converts a birthplace into a String.
     */

    @Test
    @DisplayName("birthplaceToString tested - Success")
    void birthplaceToStringTest() {

        //Arrange:
        Address addressOne = new Address("Porto");

        //Act:
        String birthplaceInString = addressOne.getBirthPlace();
        String expected = "PORTO";

        //Assert:
        assertEquals(expected, birthplaceInString);
    }



}