package switch2019.project.springBoot.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.controllerLayer.controllersCli.US005_1AdminAddsCategoryController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class US005_1AdminAddsCategoryControllerRestUnitTest {
    @Mock
    @Autowired
    private US005_1AdminAddsCategoryToGroupService service;
    @Autowired
    private US005_1AdminAddsCategoryController controller;

    //INCOMPLETE

/*
    @Test
   @DisplayName("Happy Case-  one category is added to Group categories by an admin")
    void addsCategoryToCategoryListAdmin() throws Exception {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination1 = "GYM";
        CategoryDTO CategoryDTOExpected = new CategoryDTO(categoryDenomination1, creatorEmail);
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination1);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).thenReturn(CategoryDTOExpected);

        //Act:
        CategoryDTO result = controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination1);

        //Assert:
        assertEquals(CategoryDTOExpected, result);
    }

    */


    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void addCategoryToCategoryListNotAMember() {
        //Arrange:
        String creatorEmail = "bart.simpson@gmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "GYM";

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addCategoryToGroupController(groupDescription, creatorEmail, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not a group admin or member and could not add the category.");

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
            controller.addCategoryToGroupController(groupDescription, creatorEmail, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not a group admin or member and could not add the category.");

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
            controller.addCategoryToGroupController(groupDescription, creatorEmail, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can´t be null or empty!");

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
            controller.addCategoryToGroupController(groupDescription, creatorEmail, categoryDenomination);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This category already exists and it could not be created");

    }


}
