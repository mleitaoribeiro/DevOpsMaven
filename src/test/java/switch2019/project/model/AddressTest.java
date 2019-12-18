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
        int result = zip.length();

        //Assert
        assertEquals(8, result);
    }



    /**
     * Test Equals method for the Adress class
     */

}