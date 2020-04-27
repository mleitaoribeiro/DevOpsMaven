package switch2019.project.assemblers;

import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOAssemblerTest {

    @Test
    void createAreSiblingsDTO() {
        AreSiblingsDTO areSiblingsDTO = PersonDTOAssembler.createAreSiblingsDTO("email1", "email2");

        AreSiblingsDTO areSiblingsDTO1 = new AreSiblingsDTO(areSiblingsDTO.getEmailPersonOne(), areSiblingsDTO.getEmailPersonTwo());

        assertEquals(areSiblingsDTO, areSiblingsDTO1);
    }

    @Test
    void createSiblingsDTO() {
        SiblingsDTO siblingsDTO = PersonDTOAssembler.createSiblingsDTO(true);

        SiblingsDTO siblingsDTO1 = new SiblingsDTO(true);

        assertEquals(siblingsDTO, siblingsDTO1);
    }
}