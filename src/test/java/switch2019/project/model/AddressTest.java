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
        Address casa = new Address("Rua das Flores", "Porto", "4450-632");

        //Act
        casa.setStreet("Rua das Flores");
        String actual = casa.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }
    @Test
    @DisplayName("validate input for street - null")
    void setStreet_null() {
        //Arrange
        Address casa= new Address(null,"Porto","4450-362");

        //Act
        casa.setStreet(null);
        String actual = casa.getStreet();

        //Assert
        assertEquals(null, actual);

    }

    @Test
    @DisplayName("validate input for street - not sensitive Case")
    void setStreet_SensitiveCase() {
        //Arrange
        Address casa= new Address("RUA DAS FLORES","Porto","4450-852");

        //Act
        casa.setStreet("Rua das Flores");
        String actual = casa.getStreet();

        //Assert
        assertEquals("RUA DAS FLORES", actual);

    }

    @Test
    @DisplayName("validate input for street - ChangeStreet")
    void setStreet_ChangeStreet() {
        //Arrange
        Address casa= new Address("Rua das Flores","Porto","4450-562");

        //Act
        casa.setStreet("Rua das Camelias");
        String actual = casa.getStreet();

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
        Address armazem = new Address ("Avenida da República", "Gaia", "4430-444");
        String expected = "PORTO";
        //Act
        armazem.setCity("Porto");
        //Assert
        assertEquals(expected, armazem.getCity());
    }

    @Test
    @DisplayName("validate input for city - null")
    void setCity_null_input() {
        //Arrange
        Address armazem = new Address("Beco do Paniceiro", "Gaia", "4430-444");
        //Act
        armazem.setCity(null);
        //Assert
        assertEquals(null, armazem.getCity());
    }

    @Test
    @DisplayName("validate input for city - different type")
    void setCity_different_type() {
        //Arrange
        Address casino = new Address ("Largo 5 de Outubro", "Gaia", "4430-444");
        String expected = null;
        //Act
        casino.setCity("211");
        //Assert
        assertEquals(null, casino.getCity());
    }


    @Test
    @DisplayName("validate input for city - not case sensitive")
    void setCity_not_case_sensitive() {
        //Arrange
        Address tanatório = new Address ("Avenida dos Condenados", "Gaia", "4430-444");
        String expected = "PORTO";
        //Act
        tanatório.setCity("Porto");
        //Assert
        assertEquals(expected, tanatório.getCity());
    }


    /**
     * Test - Validate input for ZIP-CODE
     */
    @Test
    @DisplayName("validate input for ZIP-CODE - with(-) ")
    public void setZIP_standard() {
        //Arrange
        Address casaDoAlberto = new Address("Rua da Vinha da Bouça","Porto","4430-444");
        String zip = "4430-094";

        //Act
        casaDoAlberto.setZipCode("4430-094");

        //Assert
        assertEquals(zip, casaDoAlberto.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - without (-) ")
    public void setZIP_notStandard() {
        //Arrange
        Address casaDoAlberto  = new Address("Rua da Vinha da Bouça","Porto","4430-444");
        String zip = "4430-094";

        //Act
        casaDoAlberto.setZipCode("4430094");

        //Assert
        assertEquals(zip, casaDoAlberto.getZipCode());
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length")
    public void setZIP_lengthstandard() {
        //Arrange
        Address casaDoAlberto = new Address("Rua da Vinha da Bouça","Porto", "4430-444");
        String zip = "4430-094";

        //Act
        casaDoAlberto.setZipCode(zip);
        int result = zip.length();

        //Assert
        assertEquals(8, result);
    }

    @Test
    @DisplayName("validate input for ZIP-CODE - validate Length with no (-)")
    public void setZIP_length() {
        //Arrange
        Address casaDoAlberto = new Address("Rua da Vinha da Bouça","Porto", "4430-444");
        String zip = "4430094";

        //Act
        casaDoAlberto.setZipCode(zip);
        int result = casaDoAlberto.getZipCode().length();

        //Assert
        assertEquals(8, result);
    }
    @Test
    @DisplayName("Zip code not valide - test exception")
    public void setZIP_exception() {
        //Arrange
        Address casaDoAlberto = new Address("Rua da Vinha da Bouça","Porto", "4430-444");
        String zip = "44300945";
        try {
            //Act
            casaDoAlberto.setZipCode(zip);
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
        Address casaDoAlberto = new Address("Rua da Vinha da Bouça","Porto", "4430-444");
        String zip = "44300942";

        //Act
        casaDoAlberto.addHyphenToZipCode(zip);
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
        Address casaDaAna = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address casaDaMaria = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertTrue(casaDaAna.equals(casaDaMaria));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - different addresses")
    void sameAddressDifferentAddresses () {
        //Arrange
        Address casaDaAna = new Address("Rua das Flores, 36", "Porto", "4050-262");
        Address casaDaRita = new Address("Rua de S.Tomé, 133", "Porto", "4420-485");

        //Act and Assert
        assertFalse(casaDaAna.equals(casaDaRita));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - one address with null parameters")
    void sameAddressNullParameterAddress () {
        //Arrange
        Address casaDaAna = new Address("Rua das Flores, 36", "Porto", "4450-897");
        Address casaDoPedro = new Address("Rua das Flores, 36", "Porto", "4050-262");

        //Act and Assert
        assertFalse(casaDaAna.equals(casaDoPedro));
    }

    @Test
    @DisplayName("Testing if two Addresses are equal - lowCase and upperCase")
    void sameAddressUpperCaseLowerCase() {
        //Arrange
        Address casaDoPedro = new Address("rua das Flores, 36", "porto", "4050-262");
        Address casaDoRui = new Address("Rua das flores, 36", "PortO", "4050-262");

        //Act and Assert
        assertTrue(casaDoPedro.equals(casaDoRui));
    }

}