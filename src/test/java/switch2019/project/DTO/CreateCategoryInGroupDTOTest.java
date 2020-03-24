package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateCategoryInGroupDTOTest {

    /**
     * Tests for the different getter methods on the CreateCategoryInGroupDTOTest
     */

    @DisplayName("Test groupDescription Getter")
    @Test
    public void getDescriptionTest(){
        //Arrange:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String description = dto.getGroupDescription();

        //Assert:
        assertEquals("Friends", description);
    }

    @DisplayName("Test personalEmail Getter")
    @Test
    public void getPersonEmailTest(){
        //Arrange:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String email = dto.getPersonEmail();

        //Assert:
        assertEquals("Johnny@gmail.com", email);
    }

    @DisplayName("Test Description Getter")
    @Test
    public void getCategoryDenominationTest(){
        //Arrange:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO("Friends", "Johnny@gmail.com", "Movies");

        //Act:
        String email = dto.getCategoryDenomination();

        //Assert:
        assertEquals("Movies", email);
    }
}
