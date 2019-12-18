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

}