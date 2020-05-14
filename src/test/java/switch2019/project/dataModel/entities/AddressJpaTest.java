package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressJpaTest {

    private AddressJpa adressjpa;

    @BeforeEach
    public void setup() {
        adressjpa = new AddressJpa("Rua das Almas", "Coimbra", "4601-501");
    }

    @Test
    void getStreet() {
        //Arrange
        String expected = "Rua das Almas";

        //Act
        String result = adressjpa.getStreet();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getCity() {
        //Arrange
        String expected = "Coimbra";

        //Act
        String result = adressjpa.getCity();

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getPostalCode() {
        //Arrange
        String expected = "4601-501";

        //Act
        String result = adressjpa.getPostalCode();

        //Assert
        assertEquals(expected, result);
    }
}