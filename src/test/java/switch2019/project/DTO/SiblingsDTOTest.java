package switch2019.project.DTO;

import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.SiblingsDTO;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsDTOTest {

    @Test
    void getSiblings() {
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(false);

        String expected = "They are siblings.";
        String expected1 = "They are not siblings.";

        assertEquals(expected, siblingsDTO.getSiblings());
        assertEquals(expected1, siblingsDTO1.getSiblings());
    }

    @Test
    void testEquals() {
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);

        SiblingsDTO siblingsDTO1 = new SiblingsDTO(true);

        assertEquals(siblingsDTO, siblingsDTO1);
    }

    @Test
    void testHashCode() {
        SiblingsDTO siblingsDTO = new SiblingsDTO(true);

        SiblingsDTO siblingsDTO1 = new SiblingsDTO(true);

        assertEquals(siblingsDTO, siblingsDTO1);
    }
}