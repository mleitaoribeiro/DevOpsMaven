package switch2019.project.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serializationDTO.CategoryDenominationDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryDTOAssemblerTest {

    /**
     * Tests for the createCategoryDTOFromCategory method.
     */

    @DisplayName("DTO has the expected information about the category")
    @Test
    void testCreateCategoryDTOFromCategoryHappyCase() {
        //Arrange:
        Category categoryToDto = new Category(new Denomination("VIDEOGAMES"), new PersonID(new Email("Francisca@hotmail.com")));
        CategoryDTO expectedDto = new CategoryDTO("VIDEOGAMES", "francisca@hotmail.com");

        //Act:
        CategoryDTO actualDto = CategoryDTOAssembler.createCategoryDTOFromCategory(categoryToDto);

        //Assert:
        assertEquals(expectedDto, actualDto);
    }

    @DisplayName("DTO has the expected information about the category")
    @Test
    void testCreateCategoryDTOFromCategoryFail() {
        //Arrange:
        Category categoryToDto = new Category(new Denomination("VIDEOGAMES"), new PersonID(new Email("Francisca@hotmail.com")));
        CategoryDTO expectedDto = new CategoryDTO("GAMES", "francisca@hotmail.com");

        //Act:
        CategoryDTO actualDto = CategoryDTOAssembler.createCategoryDTOFromCategory(categoryToDto);

        //Assert:
        assertNotEquals(expectedDto, actualDto);
    }

    /**
     * Tests for the CreateGroupCategoryDTOFromStrings method.
     */

    @Test
    @DisplayName("createGroupCategoryDTO has the expected information")
    void testCreateGroupCategoryDTOFromStringsHappyCase() {
        //Arrange:
        String groupDescription = "Friends";
        String personEmail = "raquel@hotmail.com";
        String categoryDenomination = "Gym";

        //Act:
        CreateGroupCategoryDTO actualDto = CategoryDTOAssembler.createGroupCategoryDTOFromStrings(groupDescription, personEmail, categoryDenomination);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(categoryDenomination, actualDto.getCategoryDenomination()),
                () -> assertEquals(personEmail, actualDto.getPersonEmail()),
                () -> assertEquals(groupDescription, actualDto.getGroupDescription())
        );

    }

    @Test
    @DisplayName("createGroupCategoryDTO don't has the expected information")
    void testCreateGroupCategoryDTOFromStringsUnHappyCase() {
        //Arrange:
        String groupDescription = "Friends";
        String personEmail = "raquel@hotmail.com";
        String categoryDenomination = "Gym";

        //Act:
        CreateGroupCategoryDTO actualDto = CategoryDTOAssembler.createGroupCategoryDTOFromStrings(groupDescription, personEmail, categoryDenomination);

        //Assert:
        Assertions.assertAll(
                () -> assertNotEquals("House", actualDto.getCategoryDenomination()),
                () -> assertNotEquals("kelle@gmail.com", actualDto.getPersonEmail()),
                () -> assertNotEquals("Gym Buddies", actualDto.getGroupDescription())
        );
    }

    /**
     * Tests for the transformToCreateGroupCategoryDTO method.
     */

    @Test
    @DisplayName("createGroupCategoryDTO has the expected information")
    void testTransformToCreateGroupCategoryDTOHappyCase() {
        //Arrange:
        String groupDescription = "Friends";
        String personEmail = "raquel@hotmail.com";
        String categoryDenomination = "Gym";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);

        //Act:
        CreateGroupCategoryDTO actualDto = CategoryDTOAssembler.transformToCreateGroupCategoryDTO
                (groupDescription, createGroupCategoryInfoDTO);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(categoryDenomination, actualDto.getCategoryDenomination()),
                () -> assertEquals(personEmail, actualDto.getPersonEmail()),
                () -> assertEquals(groupDescription, actualDto.getGroupDescription())
        );

    }

    @Test
    @DisplayName("createGroupCategoryDTO don't has the expected information")
    void testTransformToCreateGroupCategoryDTOUnHappyCase() {
        //Arrange:
        String groupDescription = "Friends";
        String personEmail = "raquel@hotmail.com";
        String categoryDenomination = "Gym";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);

        //Act:
        CreateGroupCategoryDTO actualDto = CategoryDTOAssembler.transformToCreateGroupCategoryDTO
                (groupDescription,createGroupCategoryInfoDTO);

        //Assert:
        Assertions.assertAll(
                () -> assertNotEquals("House", actualDto.getCategoryDenomination()),
                () -> assertNotEquals("kelle@gmail.com", actualDto.getPersonEmail()),
                () -> assertNotEquals("Gym Buddies", actualDto.getGroupDescription())
        );

    }

    @Test
    @DisplayName("Test for createCategoryDenominationDTO")
    void createCategoryDenominationDTO(){
        Category cat = new Category(new Denomination("CategoryName"),
                new PersonID(new Email("email@email.com")));

        CategoryDenominationDTO categoryDenominationDTO1 = new CategoryDenominationDTO("CategoryName".toUpperCase());
        CategoryDenominationDTO categoryDenominationDTO2 = new CategoryDenominationDTO("CategoryNameNot".toUpperCase());

        CategoryDenominationDTO categoryDenominationDTO =
                CategoryDTOAssembler.createCategoryDenominationDTO(cat);

        assertEquals(categoryDenominationDTO1 ,categoryDenominationDTO);
        assertNotEquals(categoryDenominationDTO1,categoryDenominationDTO2);

    }


}
