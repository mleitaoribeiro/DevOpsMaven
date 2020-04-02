package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountInfoDTOTest {

    @Test
    @DisplayName("Test personEmail Getter")
    void getPersonEmailTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getPersonEmail();

        //Assert:
        assertEquals("1191762@isep.ipp.pt", description);

    }


    @Test
    @DisplayName("Test accountDenomination getter")
    void getAccountDenominationTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", description);

    }

    @Test
    @DisplayName("Test accountDescription getter")
    void getAccountDescriptionTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", description);

    }


    @Test
    void setPersonEmail() {}


    @Test
    void setAccountDenomination() {
    }

    @Test
    void setAccountDescription() {
    }
}