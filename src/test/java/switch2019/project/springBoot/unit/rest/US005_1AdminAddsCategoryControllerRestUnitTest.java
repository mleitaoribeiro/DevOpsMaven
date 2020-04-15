package switch2019.project.springBoot.unit.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.DeserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.controllerLayer.controllersRest.US005_1AdminAddsCategoryControllerRest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")

class US005_1AdminAddsCategoryControllerRestUnitTest {
    @Mock
    private US005_1AdminAddsCategoryToGroupService service;
    @Autowired
    private US005_1AdminAddsCategoryControllerRest controllerRest;


    @Test
    @DisplayName("Happy Case-  one category is added to Group categories by an admin")
    void addsCategoryToCategoryListAdmin() throws Exception {
        //Arrange:
        String creatorEmail = "1191743@isep.ipp.pt";
        String groupDescription = "SWITCH";
        String categoryDenomination = "HEALTH";

        //Arrange - CreateGroupCategoryInfoDTO & CreateGroupCategoryDTO:
        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(creatorEmail);
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        //Arrange - Expected Result
        CategoryDTO categoryDTOExpected = new CategoryDTO(categoryDenomination, groupDescription);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(categoryDTOExpected, HttpStatus.CREATED);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).thenReturn(categoryDTOExpected);

        //Act:
        ResponseEntity<CategoryDTO> responseEntityResult = controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);

        //Assert:
        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    @DisplayName("Happy Case - several categories added to Group categories by an admin")
    void addsCategoriesToCategoryListAdmin() throws Exception {
        //Arrange - Category 1:
        String creatorEmail1 = "1191743@isep.ipp.pt";
        String groupDescription1 = "SWITCH";
        String categoryDenomination1 = "GAMES";

        CategoryDTO CategoryDTOExpected1 = new CategoryDTO(categoryDenomination1, groupDescription1);
        CreateGroupCategoryDTO createGroupCategoryDTO1 = new CreateGroupCategoryDTO(groupDescription1, creatorEmail1, categoryDenomination1);

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO1 = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO1.setPersonEmail(creatorEmail1);
        createGroupCategoryInfoDTO1.setGroupDescription(groupDescription1);
        createGroupCategoryInfoDTO1.setCategoryDenomination(categoryDenomination1);

        CategoryDTO categoryDTOExpected = new CategoryDTO(categoryDenomination1, groupDescription1);
        ResponseEntity responseEntityExpected1 = new ResponseEntity<>(categoryDTOExpected, HttpStatus.CREATED);

        //Arrange - Category 2:
        String creatorEmail2 = "1191743@isep.ipp.pt";
        String groupDescription2 = "SWITCH";
        String categoryDenomination2 = "BARISEP";

        CategoryDTO CategoryDTOExpected2 = new CategoryDTO(categoryDenomination2, groupDescription2);
        CreateGroupCategoryDTO createGroupCategoryDTO2 = new CreateGroupCategoryDTO(groupDescription2, creatorEmail2, categoryDenomination2);

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO2.setPersonEmail(creatorEmail2);
        createGroupCategoryInfoDTO2.setGroupDescription(groupDescription2);
        createGroupCategoryInfoDTO2.setCategoryDenomination(categoryDenomination2);

        CategoryDTO categoryDTOExpected2 = new CategoryDTO(categoryDenomination2, groupDescription2);
        ResponseEntity responseEntityExpected2 = new ResponseEntity<>(categoryDTOExpected2, HttpStatus.CREATED);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO1)).thenReturn(CategoryDTOExpected1);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO2)).thenReturn(CategoryDTOExpected2);

        //Act:
        ResponseEntity<CategoryDTO> responseEntityResult1 = controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO1);
        ResponseEntity<CategoryDTO> responseEntityResult2 = controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO2);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected1, responseEntityResult1),
                () -> assertEquals(responseEntityExpected2, responseEntityResult2)
        );

    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void addCategoryToCategoryListNotAMember() {
        //Arrange:
        String creatorEmail = "bart.simpson@gmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "GYM";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(creatorEmail);
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
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

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(creatorEmail);
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
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

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(creatorEmail);
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
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

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(creatorEmail);
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This category already exists.");

    }

}
