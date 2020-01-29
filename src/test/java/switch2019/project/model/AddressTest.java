package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static switch2019.project.model.Address.isNumeric;

class AddressTest {

    /**
     * Test - Validate input for Street
     */

    @Test
    @DisplayName("validate input for street - happy case")
    void setStreetHappyCase() {
        //Arrange
        Address house = new Address("Rua das Flores", "Porto", "4450-632");

        //Act
        house.setStreet("Rua das Flores");
        String actual = house.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }

    @Test
    @DisplayName("validate input for street - null")
    void setStreetHull() {
        //Arrange
        Address house = new Address("Rua 1", "Porto", "4450-362");
        String street = null;
        try {
            //Act
            house.setStreet(street);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("The street format in your Address is not valid or it's missing. Please try again", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("validate input for street - numeric")
    void setStreetNumeric() {
        //Arrange
        Address house = new Address("Rua 1", "Porto", "4450-362");
        String street = "162723";
        try {
            //Act
            house.setStreet(street);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("The street format in your Address is not valid or it's missing. Please try again", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("validate input for street - not sensitive Case")
    void setStreetSensitiveCase() {
        //Arrange
        Address house = new Address("RUA DAS FLORES", "Porto", "4450-852");

        //Act
        house.setStreet("Rua das Flores");
        String actual = house.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }

    @Test
    @DisplayName("validate input for street - ChangeStreet")
    void setStreetChangeStreet() {
        //Arrange
        Address house = new Address("Rua das Flores", "Porto", "4450-562");

        //Act
        house.setStreet("Rua das Camelias");
        String actual = house.getStreet();

        //Assert
        assertEquals("RUA DAS CAMELIAS", actual);
    }


    /**
     * Test - Validate input for City
     */

    @Test
    @DisplayName("validate input for city - happy case")
    void setCityHappycase() {
        //Arrange
        Address address1 = new Address("Avenida da República", "Gaia", "4430-444");
        String expected = "PORTO";
        //Act
        address1.setCity("Porto");
        //Assert
        assertEquals(expected, address1.getCity());
    }

    @Test
    @DisplayName("validate input for city - null")
    void setCityNullInput() {
        //Arrange
        Address address1 = new Address("Beco do Paniceiro", "Gaia", "4430-444");
        String city = null;
        try {
            //Act
            address1.setCity(city);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("The city in your Address is not valid or it's missing. Please try again.", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("validate input for city - numeric")
    void setCityNumericInput() {
        //Arrange
        Address address1 = new Address("Beco do Paniceiro", "Gaia", "4430-444");
        String city = "1234";
        try {
            //Act
            address1.setCity(city);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("The city in your Address is not valid or it's missing. Please try again.", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("validate input for city - not case sensitive")
    void setCityNotCaseSensitive() {
        //Arrange
        Address address1 = new Address("Avenida dos Condenados", "Gaia", "4430-444");
        String expected = "PORTO";
        //Act
        address1.setCity("Porto");
        //Assert
        assertEquals(expected, address1.getCity());
    }


    /**
     * Test - Validate input for ZIP-CODE
     */
    @Test
    @DisplayName("validate input for ZIP-CODE - with(-) ")
    public void setZIPStandard() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = "4430-094";

        //Act
        address1.setZipCode("4430-094");

        //Assert
        assertEquals(zip, address1.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - without (-) ")
    public void setZIPNotStandard() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = "4430-094";

        //Act
        address1.setZipCode("4430094");

        //Assert
        assertEquals(zip, address1.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length")
    public void setZipLengthStandard() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = "4430-094";

        //Act
        address1.setZipCode(zip);
        int result = zip.length();

        //Assert
        assertEquals(8, result);
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length with no (-)")
    public void setZipLength() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = "4430094";

        //Act
        address1.setZipCode(zip);
        int result = address1.getZipCode().length();

        //Assert
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Zip code not valid - test exception - Null")
    public void setZipNullexception() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = null;
        try {
            //Act
            address1.setZipCode(zip);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("Zip-Code can´t be null! (Correct Format: xxxx-xxx)", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("Zip code not valide - test exception")
    public void setZipException() {
        //Arrange
        Address address1 = new Address("Rua da Vinha da Bouça", "Porto", "4430-444");
        String zip = "44300945";
        try {
            //Act
            address1.setZipCode(zip);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("Zip-Code is not in the correct format! (xxxx-xxx)", zipcode.getMessage());
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
        Address address1 = new Address("rua das Flores, 36", "porto", "4050-262");
        Address address2 = new Address("Rua das flores, 36", "PortO", "4050-262");

        //Act and Assert
        assertTrue(address1.equals(address2));
    }

    /**
     * Test to validate if string city is numeric
     */

    @Test
    @DisplayName("Test to validate if a string city is numeric - True Case")
    void isNumericTrue() {
        //Arrange
        String city = "PORTO";

        //Act
        boolean expected = isNumeric(city);

        //Assert
        assertEquals(false, expected);
    }

    @Test
    @DisplayName("Test to validate if a string city is numeric - False Case")
    void isNumericFalse() {
        //Arrange
        String city = "4245";

        //Act
        boolean expected = isNumeric(city);

        //Assert
        assertEquals(true, expected);
    }

    @Test
    @DisplayName("Test to validate if a string city is numeric - Null Case")
    void isNumericNullString() {
        //Arrange
        String city = null;

        //Act
        boolean expected = isNumeric(city);

        //Assert
        assertEquals(false, expected);
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
        Person person1 = new Person("Miguel", LocalDate.of(1990, 12, 04), new Address("Porto"), new Address("Rua das Flores", "Porto", "4450-230"));

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
     * Test to check if homeAdressToString converts a homeAddress into a String.
     */

    @Test
    @DisplayName("homeAdressToString tested - Success")
    void homeAddressToStringTest() {

        //Arrange:
        Address addressOne = new Address("Rua das Flores", "Porto", "4430-098");

        //Act:
        String homeAddressInString = addressOne.homeAddressToString();
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
        String birthplaceInString = addressOne.birthplaceToString();
        String expected = "Porto";

        //Assert:
        assertEquals(expected, birthplaceInString);
    }

}