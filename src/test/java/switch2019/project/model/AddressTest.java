package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    /**
     * Test - Validate input for Street
     */

    @Test
    @DisplayName("validate input for street - happy case")
    void setStreet_happyCase() {
        //Arrange
        Address A = new Address("Rua das Flores", null, null);

        //Act
        A.setStreet("Rua das Flores");
        String actual = A.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }
    @Test
    @DisplayName("validate input for street - nulll")
    void setStreet_null() {
        //Arrange
        Address A= new Address(null,null,null);

        //Act
        A.setStreet(null);
        String actual = A.getStreet();

        //Assert
        assertEquals(null, actual);

    }

    @Test
    @DisplayName("validate input for street - not sensitive Case")
    void setStreet_SensitiveCase() {
        //Arrange
        Address A= new Address("RUA DAS FLORES",null,null);

        //Act
        A.setStreet("Rua das Flores");
        String actual = A.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }

    @Test
    @DisplayName("validate input for street - ChangeStreet")
    void setStreet_ChangeStreet() {
        //Arrange
        Address A= new Address("Rua das Flores",null,null);

        //Act
        A.setStreet("Rua das Camelias");
        String actual = A.getStreet();

        //Assert
        assertEquals("RUA DAS CAMELIAS", actual);
    }

    /**
     * Test - Validate input for City
     */

    @Test
    @DisplayName("validate input for city - happy case")
    void setCity_happycase() {
        //Arrange
        Address A = new Address ("Rua XPTO", "Gaia", "4430-444");
        String expected = "PORTO";
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
        String expected = "PORTO";
        //Act
        A.setCity("Porto");
        //Assert
        assertEquals(expected, A.getCity());
    }


    /**
     * Test - Validate input for ZIP-CODE
     */
    @Test
    @DisplayName("validate input for ZIP-CODE - with(-) ")
    public void setZIP_standard() {
        //Arrange
        Address a1 = new Address(null,null,null);
        String zip = "4430-094";

        //Act
        a1.setZipCode("4430-094");

        //Assert
        assertEquals(zip, a1.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - without (-) ")
    public void setZIP_notStandard() {
        //Arrange
        Address a1 = new Address(null,null,null);
        String zip = "4430-094";

        //Act
        a1.setZipCode("4430094");

        //Assert
        assertEquals(zip, a1.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length")
    public void setZIP_lengthstandard() {
        //Arrange
        Address a1 = new Address(null, null, null);
        String zip = "4430-094";

        //Act
        a1.setZipCode(zip);
        int result = zip.length();

        //Assert
        assertEquals(8, result);
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length with no (-)")
    public void setZIP_length() {
        //Arrange
        Address a1 = new Address(null, null, null);
        String zip = "4430094";

        //Act
        a1.setZipCode(zip);
        int result = a1.getZipCode().length();

        //Assert
        assertEquals(8, result);
    }
    @Test
    @DisplayName("Zip code not valide - test exception")
    public void setZIP_exception() {
        //Arrange
        Address a1 = new Address(null, null, null);
        String zip = "44300945";
        try {
            //Act
            a1.setZipCode(zip);
            fail();
        }
        //Assert
        catch (IllegalArgumentException zipcode) {
            assertEquals("Zip-Code is not in the correct format! (xxxx-xxx)", zipcode.getMessage());
        }
    }

    @Test
    @DisplayName("Add hyphen to zip code - 8 numbers")
    public void addHyphenToZipCode() {
        //Arrange
        Address a1 = new Address(null, null, null);
        String zip = "44300942";

        //Act
        a1.addHyphenToZipCode(zip);
        String result = "44300942";

        //Assert
        assertEquals(zip, result);
    }

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
        Address a2 = new Address("Rua de S.Tomé, 133", "Porto", "4420-485");

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