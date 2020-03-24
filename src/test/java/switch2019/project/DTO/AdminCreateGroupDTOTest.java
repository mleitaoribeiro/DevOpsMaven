package switch2019.project.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminCreateGroupDTOTest {

    @Test
    void getGroupDescription() {
        //Arrange
        AdminCreateGroupDTO dto = new AdminCreateGroupDTO("Futebol", "maria@isep.ipp.pt");
        String expected = "Futebol";

        //Act
        String actual = dto.getGroupDescription();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void getPersonEmail() {
        //Arrange
        AdminCreateGroupDTO dto = new AdminCreateGroupDTO("Futebol", "maria@isep.ipp.pt");
        String expected = "maria@isep.ipp.pt";

        //Act
        String actual = dto.getPersonEmail();

        //Assert
        assertEquals(expected,actual);
    }
}