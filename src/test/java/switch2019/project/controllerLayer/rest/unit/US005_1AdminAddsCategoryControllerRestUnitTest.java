package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.controllerLayer.rest.US005_1AdminAddsCategoryControllerRest;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.NoPermissionException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
class US005_1AdminAddsCategoryControllerRestUnitTest {

    @Mock
    private US005_1AdminAddsCategoryToGroupService service;

    @InjectMocks
    private US005_1AdminAddsCategoryControllerRest controllerRest;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * US005.1
     * As a Group Administrator, I want to create a category and add it to the group.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Happy Case - one category is added to Group categories by an admin")
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

        CreateGroupCategoryDTO createGroupCategoryDTO1 = new CreateGroupCategoryDTO(groupDescription1, creatorEmail1, categoryDenomination1);

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO1 = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO1.setPersonEmail(creatorEmail1);
        createGroupCategoryInfoDTO1.setGroupDescription(groupDescription1);
        createGroupCategoryInfoDTO1.setCategoryDenomination(categoryDenomination1);

        CategoryDTO categoryDTOExpected1 = new CategoryDTO(categoryDenomination1, groupDescription1);
        ResponseEntity responseEntityExpected1 = new ResponseEntity<>(categoryDTOExpected1, HttpStatus.CREATED);

        //Arrange - Category 2:
        String creatorEmail2 = "1191743@isep.ipp.pt";
        String groupDescription2 = "SWITCH";
        String categoryDenomination2 = "BARISEP";

        CategoryDTO categoryDTOExpected2 = new CategoryDTO(categoryDenomination2, groupDescription2);
        CreateGroupCategoryDTO createGroupCategoryDTO2 = new CreateGroupCategoryDTO(groupDescription2, creatorEmail2, categoryDenomination2);

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO2 = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO2.setPersonEmail(creatorEmail2);
        createGroupCategoryInfoDTO2.setGroupDescription(groupDescription2);
        createGroupCategoryInfoDTO2.setCategoryDenomination(categoryDenomination2);

        ResponseEntity responseEntityExpected2 = new ResponseEntity<>(categoryDTOExpected2, HttpStatus.CREATED);

        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO1)).thenReturn(categoryDTOExpected1);
        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO2)).thenReturn(categoryDTOExpected2);

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

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).
                thenThrow(new NoPermissionException("This person is not member of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("This person is not member of this group.");

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

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).
                thenThrow(new NoPermissionException("This person is not admin of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NoPermissionException.class)
                .hasMessage("This person is not admin of this group.");

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

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

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

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(service.addCategoryToGroup(createGroupCategoryDTO)).
                thenThrow(new ResourceAlreadyExistsException("This category already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.addCategoryToGroup(createGroupCategoryInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This category already exists.");

    }

    /**
     * Test if a category can be found by the ID
     */
    @Test
    @DisplayName("Test if a category can be found by the ID - Happy Case")
    void getCategoryByCategoryID() {

        //Arrange
        String groupDescription = "SMITH FAMILY";
        String categoryDescription = "ONLINE";

        //DTO expected
        CategoryDTO categoryDTOExpected = new CategoryDTO(categoryDescription, groupDescription);

        //arranging mockitos
        Mockito.when(service.getCategoryByCategoryID(categoryDescription, groupDescription))
                .thenReturn(categoryDTOExpected);

        //Act
        CategoryDTO categoryDTOResult = service.getCategoryByCategoryID(categoryDescription, groupDescription);

        //Assert
        assertEquals(categoryDTOExpected, categoryDTOResult);
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() {

        //Arrange
        String groupDescription = "Just4Fun";
        String categoryDescription = "ONLINE";

        //arranging mockitos
        Mockito.when(service.getCategoryByCategoryID(categoryDescription, groupDescription))
                .thenThrow(new ArgumentNotFoundException("No group found with that description."));

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

        //Arrange
        String groupDescription = "SMITH FAMILY";
        String categoryDescription = "Dispenses";

        //arranging mockitos
        Mockito.when(service.getCategoryByCategoryID(categoryDescription, groupDescription))
                .thenThrow(new ArgumentNotFoundException("No category found with that ID."));

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

        //Arrange
        String groupDescription = "SMITH FAMILY";
        String categoryDescription = null;

        //arranging mockitos
        Mockito.when(service.getCategoryByCategoryID(categoryDescription, groupDescription))
                .thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

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

        //Arrange
        String groupDescription = null;
        String categoryDescription = "ONLINE";

        //arranging mockitos
        Mockito.when(service.getCategoryByCategoryID(categoryDescription, groupDescription))
                .thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

}
