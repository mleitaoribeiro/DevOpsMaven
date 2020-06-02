package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo= "1191780@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Act
        // boolean siblings = service.areSiblings(siblingsDTO);
        boolean siblings2 = service.areSiblings(siblingsDTO);

        //Assert
        assertFalse(siblings2);
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
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
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

    @Test
    @DisplayName("Test getSiblings")
    public void getSiblings() {
        //Arrange
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo = "manuel@isep.ipp.pt";
        PersonID otherSiblingID = new PersonID(new Email(emailPersonTwo));

        Set<PersonIDDTO> expectedSiblingsList = new HashSet<>(Arrays.asList(PersonDTOAssembler.createPersonIDDTO(otherSiblingID)));

        //Act
        Set<PersonIDDTO> realSiblingList = service.getSiblings(emailPersonOne);

        //Assert
        assertEquals(expectedSiblingsList, realSiblingList);
    }

    @Test
    @DisplayName("Test getSiblings - simpsons")
    public void getSiblings2() {
        //Arrange
        String emailPersonOne = "bart.simpson@gmail.com";
        String emailPersonTwo = "liza.simpson@hotmail.com";
        String emailPersonThree = "maggie.simpson@gmail.com";

        PersonID otherSiblingID = new PersonID(new Email(emailPersonTwo));
        PersonID anotherSiblingID = new PersonID(new Email(emailPersonThree));

        Set<PersonIDDTO> expectedSiblingsList = new HashSet<>(Arrays.asList(PersonDTOAssembler.createPersonIDDTO(otherSiblingID), PersonDTOAssembler.createPersonIDDTO(anotherSiblingID)));

        //Act
        Set<PersonIDDTO> realSiblingList = service.getSiblings(emailPersonOne);

        //Assert
        assertEquals(expectedSiblingsList, realSiblingList);
    }

    @Test
    @DisplayName("Test getPersonID")
    public void getPerson() {
        //Arrange
        String emailPersonOne = "bart.simpson@gmail.com";

        PersonIDDTO expectedPersonDTO = new PersonIDDTO(emailPersonOne);

        //Act
        PersonIDDTO realPersonDTO = service.getPersonByEmail(emailPersonOne);
        //Assert
        assertEquals(expectedPersonDTO, realPersonDTO);
    }
}