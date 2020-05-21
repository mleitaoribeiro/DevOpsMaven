package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;


import static org.junit.jupiter.api.Assertions.*;

class AddressJpaTest {

    private static AddressJpa adressjpa;

    @BeforeAll
    public static void setup() {
        adressjpa = new AddressJpa("Rua das Almas", "Coimbra", "4601-501");
    }

    @Test
    @DisplayName("Test equals equal object")
    void testEquals() {
        //Arrange
        AddressJpa otherAddress = new AddressJpa("Rua das Almas", "Coimbra", "4601-501");

        //Act
        boolean result = otherAddress.equals(adressjpa);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals same object")
    void testEqualsSame() {
        //Act
        boolean result = adressjpa.equals(adressjpa);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals null object")
    void testEqualsNullObject() {
        //Arrange
        AddressJpa nullAddress = null;

        //Act
        boolean result = adressjpa.equals(nullAddress);

        //Assert
        assertNotEquals(result, adressjpa);
        }

    @Test
    @DisplayName("Test equals different object")
    void testEqualsDifferentObject() {
        //Arrange
        AddressJpa otherAddress = new AddressJpa("Avenida António Domingues", "Matosinhos", "4200-500");

        //Act
        boolean result = otherAddress.equals(adressjpa);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals different class object")
    void testEqualsDifferentClassObject() {
        //Arrange
        Address address = new Address("Rua das Almas", "Coimbra", "4601-501");

        //Act
        boolean result = address.equals(adressjpa);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashcode() {
        //Arrange
        AddressJpa sameAddress = adressjpa;
        AddressJpa otherAddress = new AddressJpa("Rua da Alegria", "Porto", "4620-555");

        //Act
        boolean result = sameAddress.equals(otherAddress);

        // Assert

        assertEquals(sameAddress.hashCode(),adressjpa.hashCode());
        assertNotEquals(sameAddress.hashCode(), otherAddress.hashCode());
    }

    @Test
    @DisplayName("Get street - success")
    void getStreet() {
        //Arrange
        String expected = "Rua das Almas";

        //Act
        String result = adressjpa.getStreet();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get city - success")
    void getCity() {
        //Arrange
        String expected = "Coimbra";

        //Act
        String result = adressjpa.getCity();

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get postal code - success ")
    void getPostalCode() {
        //Arrange
        String expected = "4601-501";

        //Act
        String result = adressjpa.getPostalCode();

        //Assert
        assertEquals(expected, result);
    }
}