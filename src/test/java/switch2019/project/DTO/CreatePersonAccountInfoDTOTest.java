package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountInfoDTOTest {

    @Test
    @DisplayName("Test personEmail Getter")
    void getPersonEmailTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String personEmail = dto.getPersonEmail();

        //Assert:
        assertEquals("1191762@isep.ipp.pt", personEmail);

    }


    @Test
    @DisplayName("Test accountDenomination getter")
    void getAccountDenominationTest() {

        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String denomination = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", denomination);

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
    @DisplayName("Test Person Email setter")
    void setPersonEmail() {
        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");
        String expected = "1191762@isep.ipp.pt";

        //Act:
        dto.setPersonEmail("1191762@isep.ipp.pt");
        String result = dto.getPersonEmail();

        //Assert:
        assertEquals("1191762@isep.ipp.pt", result);

    }


    @Test
    @DisplayName("Test accountDenomination setter")
    void setAccountDenomination() {
        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");
        String expected = "Revolut";

        //Act:
        dto.setAccountDenomination("Revolut");
        String result = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", result);

    }

    @Test
    @DisplayName("Test accountDescription setter")
    void setAccountDescription() {
        //Assert
        CreatePersonAccountInfoDTO dto = new CreatePersonAccountInfoDTO("1191762@isep.ipp.pt", "Revolut", "Online Shopping");
        String expected = "Online Shopping";

        //Act:
        dto.setAccountDescription("Online Shopping");
        String result = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", result);

    }
    }
