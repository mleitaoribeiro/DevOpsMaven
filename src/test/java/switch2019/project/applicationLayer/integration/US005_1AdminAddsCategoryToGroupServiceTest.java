package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serializationDTO.CategoryDenominationDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class US005_1AdminAddsCategoryToGroupServiceTest {

    @Autowired
    private US005_1AdminAddsCategoryToGroupService service;

    /**
     * Test if Group Category is created
     */
    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupServiceTestEqual() {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = "MONTADITOS";

        CreateGroupCategoryDTO inputDto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Arrangement of the output DTO:
        CategoryDTO outputDtoExpected = new CategoryDTO(inputDto.getCategoryDenomination(), inputDto.getGroupDescription());

        //Act:
        CategoryDTO outputActual = service.addCategoryToGroup(inputDto);

        //Assert:
        assertEquals(outputDtoExpected, outputActual);
    }


    @Test
    @DisplayName("Test False for the creation of the category using the Controller -Different DTOs")
    void addCategoryToGroupServiceTestNotEqual() {
        //Arrange:
        //Arrangement of the entry DTO for the service:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = "RIDES";
        String categoryDenomination2 = "CINEMA";

        CreateGroupCategoryDTO inputDto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Arrangement of the output DTO:
        CategoryID catID = new CategoryID(new Denomination(categoryDenomination), new GroupID(new Description(groupDescription)));
        CategoryDTO outputDtoExpected = new CategoryDTO(categoryDenomination2, catID.toString());

        //Act:
        CategoryDTO outputActual = service.addCategoryToGroup(inputDto);

        //Assert:
        assertNotEquals(outputDtoExpected, outputActual);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void adminAddsCategoryToCategoryListNotAMember() {
        //Arrange:
        //Arrangement of the DTO:
        String creatorEmail = "bart.simpson@gmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "GYM";

        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Act:
        try {
            service.addCategoryToGroup(dto);
        }

        //Assert:
        catch (NoPermissionException notGroupMember) {
            assertEquals("This person is not member of this group.", notGroupMember.getMessage());
        }
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void adminAddsCategoryToCategoryListNotAnAdmin() {
        //Arrange:
        //Arrangement of the DTO:
        String creatorEmail = "bart.simpson@gmail.com";
        String groupDescription = "FAMILY SIMPSON";
        String categoryDenomination = "vacations";
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Act:
        try {
            service.addCategoryToGroup(dto);
        }

        //Assert:
        catch (NoPermissionException notGroupAdmin) {
            assertEquals("This person is not admin of this group.", notGroupAdmin.getMessage());
        }
    }


    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListTwoCategories() {
        //Arrange:
        //Arrangement of the entry DTO for the service:
        String creatorEmail = "bart.simpson@gmail.com";
        String groupDescription = "SPLIT EXPENSES";
        //Two Denominations (one for each Dto)
        String categoryDenomination1 = "CANDIES";
        String categoryDenomination2 = "BOWLING";
        CreateGroupCategoryDTO inputDto1 = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination1);
        CreateGroupCategoryDTO inputDto2 = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination2);


        //Arrangement of the expected output DTOs:
        CategoryDTO outputDtoExpected1 = new CategoryDTO(categoryDenomination1, groupDescription);
        CategoryDTO outputDtoExpected2 = new CategoryDTO(categoryDenomination2, groupDescription);

        //Act:
        CategoryDTO outputActual1 = service.addCategoryToGroup(inputDto1);
        CategoryDTO outputActual2 = service.addCategoryToGroup(inputDto2);

        //Assert
        /*this assertion verifies four conditions to check if our Controller worked as intended:
         * - The first created CategoryDTO is compared to the first Expected CategoryDTO.
         * - The second created CategoryDTO is compared to the second Expected CategoryDTO.
         */
        Assertions.assertAll(
                () -> assertEquals(outputDtoExpected1, outputActual1),
                () -> assertEquals(outputDtoExpected2, outputActual2)

        );
    }

    @Test
    @DisplayName("Illegal exception caused by null category")
    void adminAddsCategoryToCategoryListNullCategory() {
        //Arrange:
        //Arrangement of the DTO:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = null;
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Act:
        try {
            service.addCategoryToGroup(dto);
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("The denomination can't be null or empty.", nullParameter.getMessage());
        }
    }

    @Test
    @DisplayName("Illegal exception caused by category already existing in the CategoryRepository")
    void adminAddsDuplicateCategoryToCategoryListTest() {
        //Arrange:
        //Arrangement of the DTO:
        String creatorEmail = "1191743@isep.ipp.pt"; // Not a Group admin.
        String groupDescription = "SWITCH";
        String categoryDenomination = "GYM";
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Act:
        try {
            service.addCategoryToGroup(dto);
        }
        //Assert:
        catch (ResourceAlreadyExistsException nullParameter) {
            assertEquals("This category already exists.", nullParameter.getMessage());
        }
    }

    /**
     * Test if a category can be found by the ID
     */
    @Test
    @DisplayName("Test if a category can be found by the ID - Happy Case")
    void getCategoryByCategoryID() {
        //Arrange:
        String groupDescription = "SWITCH";
        String categoryDescription = "GYM";

        //Arrangement of the output DTO:
        CategoryDTO outputExpected = new CategoryDTO(categoryDescription, groupDescription);

        //Act:
        CategoryDTO outputActual = service.getCategoryByCategoryID(categoryDescription, groupDescription);

        //Assert:
        assertEquals(outputExpected, outputActual);
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() {
        //Arrange:
        String groupDescription = "Just4Fun";
        String categoryDescription = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category not found")
    void getCategoryByCategoryIDCategoryNotFound() {
        //Arrange:
        String groupDescription = "SWITCH";
        String categoryDescription = "Dispenses";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category null")
    void getCategoryByCategoryIDCategoryNull() {
        //Arrange:
        String groupDescription = "SWITCH";
        String categoryDescription = null;

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category empty")
    void getCategoryByCategoryIDCategoryEmpty() {
        //Arrange:
        String groupDescription = "SWITCH";
        String categoryDescription = "";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group null")
    void getCategoryByCategoryIDGroupNull() {
        //Arrange:
        String groupDescription = null;
        String categoryDescription = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group empty")
    void getCategoryByCategoryIDGroupEmpty() {
        //Arrange:
        String groupDescription = "";
        String categoryDescription = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    /**
     * Test if an Category can be found by the Group ID
     */
    @Test
    @DisplayName("Test to get all categories by the GroupID-Main scenario")
    void getCategoriesByGroupID() {

        //Arrange
        String groupDescription = "Switch";
        Category category = new Category(new Denomination("GYM"), new GroupID(new Description("Switch")));
        Category category2 = new Category(new Denomination("Isep"), new GroupID(new Description("SWITCH")));

        //Act
        Set<CategoryDenominationDTO> categories = service.getCategoriesByGroupID(groupDescription);

        //int categoriesAdded = categories.size();


        Assertions.assertAll(
                () -> assertTrue(categories.contains(CategoryDTOAssembler.createCategoryDenominationDTO((category)))),
                () -> assertTrue(categories.contains(CategoryDTOAssembler.createCategoryDenominationDTO((category2))))
               // () -> assertEquals(2, categoriesAdded)

        );
    }

    @Test
    @DisplayName("Test to get all categories by the Group ID - GroupDescription does not exists")
    void getCategoriesByGroupIDInvalidGroupDescription() throws ArgumentNotFoundException {
        //Arrange:
        String groupDescription = "cantarolar";

        //Act:
        Throwable thrown = catchThrowable(() -> {
            service.getCategoriesByGroupID(groupDescription);
        });

        //Assert:
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test to get all categories by the Group ID - Null groupDescription")
    void getCategoriesByGroupIDNullDescription() {
        //Arrange:
        String groupDescription = null;

        //Act:
        Throwable thrown = catchThrowable(() -> {
            service.getCategoriesByGroupID(groupDescription);
        });

        //Assert:
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test to get all categories by the Group ID - Empty groupDescription")
    void getCategoriesByGroupIDEmptyGroupDescription() {
        //Arrange:
        String groupDescription = "";

        //Act:
        Throwable thrown = catchThrowable(() -> {
            service.getCategoriesByGroupID(groupDescription);
        });

        //Assert:
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

}
