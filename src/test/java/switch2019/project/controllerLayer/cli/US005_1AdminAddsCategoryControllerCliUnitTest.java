package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

class US005_1AdminAddsCategoryControllerCliUnitTest {
    @Mock
    private US005_1AdminAddsCategoryToGroupService service;
    @Autowired
    private US005_1AdminAddsCategoryController controller;


    @Test
    @DisplayName("Happy Case - one category is added to Group categories by an admin")
    void addsCategoryToCategoryListAdmin() throws Exception {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination1 = "TRAIL";
        CategoryDTO CategoryDTOExpected = new CategoryDTO(categoryDenomination1, groupDescription);
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination1);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).thenReturn(CategoryDTOExpected);

        //Act:
        CategoryDTO result = controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination1);

        //Assert:
        assertEquals(CategoryDTOExpected, result);
    }

    @Test
    @DisplayName("Happy Case - several categories added to Group categories by an admin")
    void addsCategoriesToCategoryListAdmin() throws Exception {
        //Arrange Category 1:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = "DINNERTIME";
        CategoryDTO CategoryDTOExpected1 = new CategoryDTO(categoryDenomination, groupDescription);
        CreateGroupCategoryDTO createGroupCategoryDTO1 = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Arrange Category 2:
        String creatorEmail2 = "1191743@isep.ipp.pt";
        String groupDescription2 = "SWITCH";
        String categoryDenomination2 = "FOOD";
        CategoryDTO CategoryDTOExpected2 = new CategoryDTO(categoryDenomination2, groupDescription2);
        CreateGroupCategoryDTO createGroupCategoryDTO2 = new CreateGroupCategoryDTO(groupDescription2, creatorEmail2, categoryDenomination2);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO1)).thenReturn(CategoryDTOExpected1);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO2)).thenReturn(CategoryDTOExpected2);

        //Act:
        CategoryDTO result1 = controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);
        CategoryDTO result2 = controller.addCategoryToGroupController(creatorEmail2, groupDescription2, categoryDenomination2);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(CategoryDTOExpected1, result1),
                () -> assertEquals(CategoryDTOExpected2, result2)
        );

    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void addCategoryToCategoryListNotAMember() {
        //Arrange:
        String creatorEmail = "bart.simpson@gmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not member or admin of this group.");

    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void addCategoryToCategoryListNotAdmin() {
        //Arrange:
        String creatorEmail = "maria@gmail.com"; // Not a Group admin.
        String groupDescription = "Family Azevedo";
        String categoryDenomination = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not member or admin of this group.");

    }

    @Test
    @DisplayName("Category is not added to Group categories - Null Category")
    void addCategoryToCategoryListNullCategory() {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = null;

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }

    @Test
    @DisplayName("Category is not added to Group categories - Category already existing in the CategoryRepository")
    void addCategoryToCategoryListCategoryAlreadyExists() {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt"; // Not a Group admin.
        String groupDescription = "SWITCH";
        String categoryDenomination = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This category already exists.");

    }


}
