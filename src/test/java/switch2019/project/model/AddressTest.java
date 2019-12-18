package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    /**
     * Test - Validate input for Street
     */


    /**
     * Test - Validate input for City
     */

    @Test
    @DisplayName("validate input for city - happy case")
    void setCity_happycase() {
        //Arrange
        Address A = new Address ("Rua XPTO", "Gaia", "4430-444");
        String expected = "Porto";
        //Act
        A.setCity("Porto");
        //Assert
        assertEquals(expected, A.getCity());
    }

    @Test
    @DisplayName("validate input for city - null")
    void setCity_null_input() {
        //Arrange
        Address A = new Address("Rua XPTO", "Gaia", "4430-444");
        //Act
        A.setCity(null);
        //Assert
        assertEquals(null, A.getCity());
    }

    @Test
    @DisplayName("validate input for city - different type")
    void setCity_different_type() {
        //Arrange
        Address A = new Address ("Rua XPTO", "Gaia", "4430-444");
        String expected = null;
        //Act
        A.setCity("211");
        //Assert
        assertEquals(null, A.getCity());
    }


    @Test
    @DisplayName("validate input for city - not case sensitive")
    void setCity_not_case_sensitive() {
        //Arrange
        Address A = new Address ("Rua XPTO", "Gaia", "4430-444");
        String expected = "Porto";
        //Act
        A.setCity("PORTO");
        //Assert
        assertEquals(expected, A.getCity());
    }


    /**
     * Test - Validate input for ZIP-CODE
     */


    /**
     * Test Equals method for the Adress class
     */

    @Test
    @DisplayName("Testing if two Addresses are equal - happy case")
    void sameAddressHappyCase () {
        //Arrange
        Address a1 = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address a2 = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertTrue(a1.equals(a2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - different addresses")
    void sameAddressDifferentAddresses () {
        //Arrange
        Address a1 = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address a2 = new Address("Rua de S.Tomé, 133", "Porto", "44200-485");

        //Act and Assert
        assertFalse(a1.equals(a2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - one address with null parameters")
    void sameAddressNullParameterAddress () {
        //Arrange
        Address a1 = new Address("Rua das Flores, 36", null, null);
        Address a2 = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertFalse(a1.equals(a2));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - lowCase and upperCase")
    void sameAddressUpperCaseLowerCase() {
        //Arrange
        Address a1 = new Address("rua das Flores, 36", "porto", "4050-262");
        Address a2 = new Address("Rua das flores, 36", "PortO", "4050-262");

        //Act and Assert
        assertTrue(a1.equals(a2));
    }


}