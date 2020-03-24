package switch2019.project.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsDTOTest {

    @Test
    void getEmailPersonOne() {
        //Arrange
        SiblingsDTO dto = new SiblingsDTO("email1", "email2");
        String expected = "email1";

        //Act
        String actual = dto.getEmailPersonOne();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEmailPersonTwo() {
        //Arrange
        SiblingsDTO dto = new SiblingsDTO("email1", "email2");
        String expected = "email2";

        //Act
        String actual = dto.getEmailPersonTwo();

        //Assert
        assertEquals(expected, actual);
    }
}