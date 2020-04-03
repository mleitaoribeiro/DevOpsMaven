package switch2019.project.DTO.DeserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupAccountInfoDTOTest {

    @Test
    @DisplayName("Test do getPersonEmail method")
    void getPersonEmail() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String personEmailExpected = "raquel@xpto.pt";

        //Act:
        dto.setPersonEmail("raquel@xpto.pt");

        //Assert:
        assertEquals(personEmailExpected, dto.getPersonEmail());
    }

    @Test
    @DisplayName("Test do get GroupDescription method")
    void getGroupDescription() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String groupDescriptionExpected = "Runners";

        //Act:
        dto.setGroupDescription("Runners");

        //Assert:
        assertEquals(groupDescriptionExpected, dto.getGroupDescription());
    }

    @Test
    @DisplayName("Test do getAccountDenomination method")
    void getAccountDenomination() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String accountDenominationExpected = "Fitness";

        //Act:
        dto.setAccountDenomination("Fitness");

        //Assert:
        assertEquals(accountDenominationExpected, dto.getAccountDenomination());
    }

    @Test
    @DisplayName("Test do getAccountDescription method")
    void getAccountDescription() {
        //Assert
        CreateGroupAccountInfoDTO dto = new CreateGroupAccountInfoDTO();
        String accountDescriptionExpected = "Gym Expenses";

        //Act:
        dto.setAccountDescription("Gym Expenses");

        //Assert:
        assertEquals(accountDescriptionExpected, dto.getAccountDescription());
    }


}