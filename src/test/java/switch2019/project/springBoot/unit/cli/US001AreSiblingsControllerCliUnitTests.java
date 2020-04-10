package switch2019.project.springBoot.unit.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.SiblingsDTO;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.controllerLayer.controllersCli.US001AreSiblingsController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class US001AreSiblingsControllerCliUnitTests {
    @Mock
    @Autowired
    private US001AreSiblingsService service;
    @Autowired
    private US001AreSiblingsController controller;

    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String emailPersonOne = "antonio@isep.ipp.pt";
        String emailPersonTwo = "manuel@isep.ipp.pt";

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
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

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
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

        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
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

        SiblingsDTO notSiblingsDTO = new SiblingsDTO(false);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);


        MockitoAnnotations.initMocks(this);
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
    @Test
    @DisplayName("Test if two individuals are siblings - Person does't exist on Person Repository")
    void AreSiblingsInvalidEmail() {
        //Arrange
        String emailPersonOne = "father@isep.ipp.pt";
        String emailPersonTwo = "child3@isep.ipp.pt";
        SiblingsDTO siblingsDTOexpected = new SiblingsDTO(true);

        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.areSiblings(siblingsDTO)).thenReturn(true);
        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.areSiblings(emailPersonOne,emailPersonOne);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }
}
