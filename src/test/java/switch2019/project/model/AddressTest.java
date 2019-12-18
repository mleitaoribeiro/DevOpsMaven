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
    void setCity() {
        //Arrange
        Address A = new Address ();
        String expected = "Porto";
        //Act
        A.setCity("Porto");
        //Assert
        assertEquals(expected, A.getCity());
    }

    @Test
    @DisplayName("validate input for city - null")
    void setCity_2() {
        //Arrange
        Address A = new Address();
        //Act
        A.setCity(null);
        //Assert
        assertEquals(null, A.getCity());
    }

    @Test
    @DisplayName("validate input for city - different type")
    void setCity_3() {
        //Arrange
        Address A = new Address ();
        String expected = null;
        //Act
        A.setCity("211");
        //Assert
        assertEquals(null, A.getCity());
    }


    @Test
    @DisplayName("validate input for city - not case sensitive")
    void setCity_4() {
        //Arrange
        Address A = new Address ();
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