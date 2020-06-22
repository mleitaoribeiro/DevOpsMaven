package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.controllerLayer.rest.US001AreSiblingsControllerRest;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest


public class US001AreSiblingsControllerRestUnitTests {
    @Mock
    private US001AreSiblingsService service;

    @InjectMocks
    private US001AreSiblingsControllerRest controller;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo = "manuel@isep.ipp.pt";

        //Arrange AreSiblingsDTO: 
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange siblingsDTOExpected:
        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(true);

        //Arrange Mockito:
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true).
                thenThrow(new IllegalArgumentException("No person found with that email."));

        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);

        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void AreSiblingsSameMother() {
        //Arrange
        String emailPersonOne = "1110120@isep.ipp.pt";
        String emailPersonTwo = "1191780@isep.ipp.pt";

        //Arrange of SiblingsDTO:
        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(true);

        //Arrange of the areSiblingsDTO:
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);

        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void AreSiblingsSameFather() {
        //Arrange
        String emailPersonOne = "amalia@isep.ipp.pt";
        String emailPersonTwo = "antonio@isep.ipp.pt";

        //Arrange SiblingsDTO
        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(true);

        //Arrange AreSiblingsDTO:
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);

        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void AreSiblingsInTheSiblingsList() {
        //Arrange
        String emailPersonOne = "father1@isep.ipp.pt";
        String emailPersonTwo = "father2@isep.ipp.pt";

        //Arrange SiblingsDTO
        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(true);

        //Arrange AreSiblingsDTO:
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);

        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - They are not siblings")
    void AreNotSiblings() {
        //Arrange
        String emailPersonOne = "rick@gmail.com";
        String emailPersonTwo = "antonio@isep.ipp.pt";

        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(false);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(false);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);

        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - person email not found on Person Repository")
    void AreSiblingsInvalidEmail() {
        //Arrange
        String emailPersonOne = "";
        String emailPersonTwo = "antonio@isep.ipp.pt";

        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(false);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenThrow(new IllegalArgumentException("No person found with that email."));
        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.areSiblings(emailPersonOne, emailPersonTwo);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");

    }

    @Test
    @DisplayName("Test if two individuals are siblings - Null person email")
    void AreSiblingsNullEmail() {
        //Arrange
        String emailPersonOne = null;
        String emailPersonTwo = "antonio@isep.ipp.pt";

        SiblingsDTO siblingsDTOExpected = new SiblingsDTO(false);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arrange Mockito
        Mockito.when(service.areSiblings(siblingsDTO)).thenThrow(new IllegalArgumentException("The email can't be null."));
        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOExpected, HttpStatus.OK);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.areSiblings(emailPersonOne, emailPersonTwo);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");

    }

    @Test
    @DisplayName("Test getSiblings - Happy Case")
    public void getSiblings() {
        //Arrange
        String emailSibling = "beatriz.azevedo@gmail.com";
        String emailRelatedSiblings = "hugo.azevedo@gmail.com";

        PersonID idRelatedSibling = new PersonID(new Email(emailRelatedSiblings));

        Set<PersonIDDTO> expectedSiblingsList = new HashSet<>(Arrays.asList(PersonDTOAssembler.createPersonIDDTO(idRelatedSibling)));

        //Act
        Mockito.when(service.getSiblings(emailSibling)).thenReturn(expectedSiblingsList);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(expectedSiblingsList, HttpStatus.OK);

        //Act
        ResponseEntity<Object> responseEntity = controller.getSiblings(emailSibling);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test getSiblings - Person Not Found")
    public void getSiblingsPersonNotFound() {
        //Arrange
        String emailSibling = "bea.azevedo@gmail.com";
        String emailRelatedSiblings = "hugo.azevedo@gmail.com";

        PersonID idRelatedSibling = new PersonID(new Email(emailRelatedSiblings));

        Set<PersonIDDTO> expectedSiblingsList = new HashSet<>(Arrays.asList(PersonDTOAssembler.createPersonIDDTO(idRelatedSibling)));

        //Act
        Mockito.when(service.getSiblings(emailSibling)).thenThrow(new IllegalArgumentException("No person found with that email."));
        ResponseEntity responseEntityExpected = new ResponseEntity<>(expectedSiblingsList, HttpStatus.UNPROCESSABLE_ENTITY);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getSiblings(emailSibling);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test getPersonEmail - Happy Case")
    public void getPersonEmail() {
        //Arrange
        String emailSibling = "beatriz.azevedo@gmail.com";

        PersonIDDTO expectedPersonIdDTO = new PersonIDDTO(emailSibling);

        //Act
        Mockito.when(service.getPersonByEmail(emailSibling)).thenReturn(expectedPersonIdDTO);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(expectedPersonIdDTO, HttpStatus.OK);

        //Act
        ResponseEntity<Object> responseEntity = controller.getPersonByEmail(emailSibling);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }

    @Test
    @DisplayName("Test getPersonEmail - Person Not Found")
    public void getPersonEmailPersonNotFound() {
        //Arrange
        String emailSibling = "bea.azevedo@gmail.com";

        PersonIDDTO expectedPersonIdDTO = new PersonIDDTO(emailSibling);

        //Act
        Mockito.when(service.getPersonByEmail(emailSibling)).thenThrow(new IllegalArgumentException("No person found with that email."));
        ResponseEntity responseEntityExpected = new ResponseEntity<>(expectedPersonIdDTO, HttpStatus.UNPROCESSABLE_ENTITY);

        //Act


        Throwable thrown = catchThrowable(() -> {
            controller.getPersonByEmail(emailSibling);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }


}