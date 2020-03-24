package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountDTOTest {

    /**
     * Tests for the different getter methods on the CreatePersonAccountDTO Class
     */

    @Test
    @DisplayName("Test personEmail Getter")
    void getPersonEmailTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Family", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getPersonEmail();

        //Assert:
        assertEquals("joao.cardoso_12@isep.ipp.pt", description);

    }

    @Test
    @DisplayName("Test groupDescription Getter")
    void getGroupDescriptionTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Family", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getGroupDescription();

        //Assert:
        assertEquals("Family", description);

    }

    @Test
    @DisplayName("Test accountDenomination getter")
    void getAccountDenominationTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Family", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", description);

    }

    @Test
    @DisplayName("Test accountDescription getter")
    void getAccountDescriptionTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Family", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", description);

    }

}