package switch2019.project.DTO;

import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;

import static org.junit.jupiter.api.Assertions.*;

class AreSiblingsDTOTest {

    @Test
    void getEmailPersonOne() {
        //Arrange
        AreSiblingsDTO dto = new AreSiblingsDTO("email1", "email2");
        String expected = "email1";

        //Act
        String actual = dto.getEmailPersonOne();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEmailPersonTwo() {
        //Arrange
        AreSiblingsDTO dto = new AreSiblingsDTO("email1", "email2");
        String expected = "email2";

        //Act
        String actual = dto.getEmailPersonTwo();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void testHashCode() {
        AreSiblingsDTO dto = new AreSiblingsDTO("email", "emaill");
        AreSiblingsDTO dto1 = new AreSiblingsDTO(dto.getEmailPersonOne(), dto.getEmailPersonTwo());

        assertEquals(dto.hashCode(), dto1.hashCode());
    }
}