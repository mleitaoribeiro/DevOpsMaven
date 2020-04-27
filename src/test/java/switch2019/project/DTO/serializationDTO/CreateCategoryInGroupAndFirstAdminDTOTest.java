package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCategoryInGroupAndFirstAdminDTOTest {

    /**
     * Tests for the different getter methods on the CreateCategoryInGroupDTOTest
     */

    @DisplayName("Test groupDescription Getter")
    @Test
    public void getDescriptionTest(){
        //Arrange:
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String description = dto.getGroupDescription();

        //Assert:
        assertEquals("Friends", description);
    }

    @DisplayName("Test personalEmail Getter")
    @Test
    public void getPersonEmailTest(){
        //Arrange:
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String email = dto.getPersonEmail();

        //Assert:
        assertEquals("Johnny@gmail.com", email);
    }

    @DisplayName("Test Description Getter")
    @Test
    public void getCategoryDenominationTest(){
        //Arrange:
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String email = dto.getCategoryDenomination();

        //Assert:
        assertEquals("Movies", email);
    }
}
