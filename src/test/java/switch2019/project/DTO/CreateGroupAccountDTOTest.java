package switch2019.project.DTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupAccountDTOTest {
    private static CreateGroupAccountDTO createGroupAccountDTO;

    @BeforeAll
    static void setup () {
        createGroupAccountDTO = new CreateGroupAccountDTO("marta.cardoso@gmail.com", "Family", "Revolut", "Online Expenses");
    }

    @Test
    @DisplayName("Get administrator email")
    void getPersonEmail() {
        //Arrange
        String expected = "marta.cardoso@gmail.com";

        //Act
        String actual = createGroupAccountDTO.getPersonEmail();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get group description")
    void getGroupDescription() {
        //Arrange
        String expected = "Family";

        //Act
        String actual = createGroupAccountDTO.getGroupDescription();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get account denomination")
    void getAccountDenomination() {
        //Arrange
        String expected = "Revolut";

        //Act
        String actual = createGroupAccountDTO.getAccountDenomination();

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Get account description")
    void getAccountDescription() {
        //Assert
        String expected = "Online Expenses";

        //Act
        String actual = createGroupAccountDTO.getAccountDescription();

        //Assert
        assertEquals(expected, actual);
    }
}