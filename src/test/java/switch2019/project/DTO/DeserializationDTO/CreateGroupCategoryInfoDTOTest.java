package switch2019.project.DTO.DeserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.DeserializationDTO.CreateGroupCategoryInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateGroupCategoryInfoDTOTest {

    @DisplayName("Test getGroupDescription Getter")
    @Test
    public void getGroupDescriptionTest(){
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Friends");
        dto.setPersonEmail("maria@gmail.com");
        dto.setCategoryDenomination("Netflix");

        String expectedDescription = "Friends";

        //Act
        String realGroupDescription = dto.getGroupDescription();

        //Assert
        assertEquals(expectedDescription, realGroupDescription);
    }

    @DisplayName("Test getPersonEmail")
    @Test
    public void getPersonEmailTest(){
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Footbal Team");
        dto.setPersonEmail("jorge@gmail.com");
        dto.setCategoryDenomination("Sport");

        String expectedPersonEmail = "jorge@gmail.com";

        //Act
        String realPersonEmail = dto.getPersonEmail();

        //Assert
        assertEquals(expectedPersonEmail, realPersonEmail);
    }

    @DisplayName("Test getCategoryDenomination")
    @Test
    public void getCategoryDenominationTest(){
        //Arrange
        CreateGroupCategoryInfoDTO dto = new CreateGroupCategoryInfoDTO();

        dto.setGroupDescription("Sport");
        dto.setPersonEmail("10203@isep.ipp.pt");
        dto.setCategoryDenomination("Gasoline");

        String expectedCategoryDenomination = "Gasoline";

        //Act
        String realCategoryDenomination = dto.getCategoryDenomination();

        //Assert
        assertEquals(expectedCategoryDenomination, realCategoryDenomination);
    }
}
