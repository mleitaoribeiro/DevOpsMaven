package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.SiblingsDTO;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US001AreSiblingsControllerCliUnitTests {
    @Mock
    private US001AreSiblingsService service;

    @InjectMocks
    private US001AreSiblingsController controller;

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

        //Arranging SiblingsDTO
        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        //Arranging AreSiblingsDTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arranging Mockito:
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        //Act
        SiblingsDTO siblingsDTOresult = controller.areSiblings(emailPersonOne, emailPersonTwo);
        //Assert
        assertEquals(siblingsDTOexpected, siblingsDTOresult);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same mother")
    void AreSiblingsSameMother() {
        //Arrange
        String emailPersonOne = "1110120@isep.ipp.pt";
        String emailPersonTwo = "1191780@isep.ipp.pt";

        //Arranging SiblingsDTO
        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        //Arranging AreSiblingsDTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arranging Mockito:
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        //Act
        SiblingsDTO siblingsDTOresult = controller.areSiblings(emailPersonOne, emailPersonTwo);
        //Assert
        assertEquals(siblingsDTOexpected, siblingsDTOresult);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - same father")
    void AreSiblingsSameFather() {
        //Arrange
        String emailPersonOne = "amalia@isep.ipp.pt";
        String emailPersonTwo = "antonio@isep.ipp.pt";

        //Arranging SiblingsDTO
        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        //Arranging AreSiblingsDTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arranging Mockito:
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        //Act
        SiblingsDTO siblingsDTOresult = controller.areSiblings(emailPersonOne, emailPersonTwo);
        //Assert
        assertEquals(siblingsDTOexpected, siblingsDTOresult);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - in each other list")
    void AreSiblingsInTheSiblingsList() {
        //Arrange
        String emailPersonOne = "father1@isep.ipp.pt";
        String emailPersonTwo = "father2@isep.ipp.pt";

        //Arranging SiblingsDTO
        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        //Arranging AreSiblingsDTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        //Arranging Mockito:
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);

        //Act
        SiblingsDTO siblingsDTOresult = controller.areSiblings(emailPersonOne, emailPersonTwo);

        //Assert
        assertEquals(siblingsDTOexpected, siblingsDTOresult);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        String emailPersonOne = "hugo.azevedo@gmail.com";
        String emailPersonTwo = "maria.cardoso_1@gmail.com";
        String emailPersonThree = "marge@hotmail.com";
        String emailPersonFour = "homer@hotmail.com";

        //Arranging SiblingsDTO
        SiblingsDTO notSiblingsDTO = new SiblingsDTO(false);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(false);

        //Act
        SiblingsDTO areSiblings1 = controller.areSiblings(emailPersonOne, emailPersonTwo);
        SiblingsDTO areSiblings2 = controller.areSiblings(emailPersonThree, emailPersonFour);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(notSiblingsDTO, areSiblings1),
                () -> assertEquals(notSiblingsDTO, areSiblings2)
        );
    }

/*
    @Test
    @DisplayName("Test if two individuals are siblings - Person does't exist on Person Repository")
    void AreSiblingsInvalidEmail() {

        //Arrange
        String emailPersonOne = "father@isep.ipp.pt";
        String emailPersonTwo = "child3@isep.ipp.pt";

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true).thenThrow(new IllegalArgumentException("No person found with that email"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.areSiblings(emailPersonOne, emailPersonTwo);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

 */
}
