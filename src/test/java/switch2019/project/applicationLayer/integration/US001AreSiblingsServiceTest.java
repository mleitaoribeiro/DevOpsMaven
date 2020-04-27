package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class US001AreSiblingsServiceTest {

    @Autowired
    private US001AreSiblingsService service;

    /**
     * US001
     * Test if two persons are siblings
     */
    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo = "manuel@isep.ipp.pt";
        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Act
        boolean expected = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void AreSiblingsSameMother() {
        //Arrange
        String emailPersonOne = "1110120@isep.ipp.pt";
        String emailPersonTwo = "1191780@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Act
        boolean expected = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void AreSiblingsSameFather() {
        //Arrange
        String emailPersonOne = "amalia@isep.ipp.pt";
        String emailPersonTwo = "antonio@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Act
        boolean expected = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(expected);
    }



    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void AreSiblingsInTheSiblingsList() {
        //Arrange
        String joseEmail = "father1@isep.ipp.pt";
        String rafaelEmail = "father2@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(joseEmail, rafaelEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(siblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        String joseEmail = "father1@isep.ipp.pt";
        String robertoEmail = "roberto@isep.ipp.pt";
        String mariaEmail = "mother1@isep.ipp.pt";
        String amaliaEmail = "amalia@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(joseEmail, robertoEmail);
        AreSiblingsDTO siblingsDTO2 = new AreSiblingsDTO(mariaEmail, amaliaEmail);

        //Act
        boolean siblings = service.areSiblings(siblingsDTO);
        boolean siblings2 = service.areSiblings(siblingsDTO2);

        //Assert
        Assertions.assertAll(
                () -> assertFalse(siblings),
                () -> assertFalse(siblings2)
        );


    }

    @Test
    @DisplayName("Test if two individuals are siblings - no person found with that email")
    void AreSiblingsNoPersonEmail() {
        //Arrange
        String joseEmail = "father@isep.ipp.pt";
        String robertoEmail = "roberto@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(joseEmail, robertoEmail);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.areSiblings(siblingsDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if two individuals are siblings - invalid email")
    void AreSiblingsInvalidEmail() {
        //Arrange
        String personEmail1 = "fatherisep.ipp.pt";
        String personEmail2 = "roberto@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(personEmail1, personEmail2);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.areSiblings(siblingsDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

}