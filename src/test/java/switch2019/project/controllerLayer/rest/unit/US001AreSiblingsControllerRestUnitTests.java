package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.SiblingsDTO;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.controllerLayer.rest.US001AreSiblingsControllerRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US001AreSiblingsControllerRestUnitTests {
    @Mock
    private US001AreSiblingsService service;
    @Autowired
    private US001AreSiblingsControllerRest controller;

    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo = "manuel@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOexpected, HttpStatus.OK);

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

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOexpected, HttpStatus.OK);

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

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        ResponseEntity responseEntityExpected = new ResponseEntity<>(siblingsDTOexpected, HttpStatus.OK);
        //Act
        ResponseEntity<SiblingsDTO> responseEntity = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(responseEntityExpected, responseEntity);
    }
}