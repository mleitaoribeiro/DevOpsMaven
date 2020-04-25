package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryToGroupServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private GroupRepository groupsRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private US005_1AdminAddsCategoryToGroupService service;


    private String creatorEmail;
    private String groupDescription;
    private String categoryDescription;
    private Person creator;
    private Group group;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        creatorEmail = "rick@gmail.com";
        groupDescription = "SMITH FAMILY";
        categoryDescription = "ONLINE";

        creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));

        group = new Group(new Description(groupDescription), creator);
    }

    /**
     * Test if Group Category is created
     */
    @Test
    @DisplayName("Test If Group Category is created - Happy Case - Main Scenario")
    void testIfGroupCategoryWasCreatedHappyCase() {

        //ARRANGE
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDescription);

        Category category = new Category(new Denomination(categoryDescription), group.getID());

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDescription),
                group.getID())).thenReturn(category);

        CategoryDTO expected = new CategoryDTO(categoryDescription, groupDescription);

        //Act
        CategoryDTO categoryCreated = service.addCategoryToGroup(createGroupCategoryDTO);

        //Assert
        assertEquals(expected, categoryCreated);
    }

    @Test
    @DisplayName("Test If Group Category is created - Not equals - different DTOs")
    void testIfGroupCategoryWasCreatedNotEquals() {

        //ARRANGE
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDescription);

        Category category = new Category(new Denomination(categoryDescription), group.getID());

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDescription),
                group.getID())).thenReturn(category);

        CategoryDTO expected = new CategoryDTO(categoryDescription, groupDescription);

        //Act
        CategoryDTO categoryCreated = service.addCategoryToGroup(createGroupCategoryDTO);

        //Assert
        assertEquals(expected, categoryCreated);
    }

    @Test
    @DisplayName("testIfGroupCategoryWasCreatedNotAdmin")
    void testIfGroupCategoryWasCreatedNotAdmin() {
        //Arrange

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDescription);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDescription),
                group.getID())).thenThrow(new IllegalArgumentException("This person is not member or admin of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addCategoryToGroup(createGroupCategoryDTO);
        });
        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not member or admin of this group.");


    }

    @Test
    @DisplayName("testIfGroupCategoryWasCreatedNotAdmin")
    void testIfGroupCategoryWasCreatedNullCategory() {
        //Arrange

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, null);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(null,
                group.getID())).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addCategoryToGroup(createGroupCategoryDTO);
        });
        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }

    @Test
    @DisplayName("testIfGroupCategoryWasCreatedRepeated")
    void testIfGroupCategoryWasCreatedCategoryRepeated() {
        //Arrange

        Category category = new Category(new Denomination(categoryDescription), group.getID());

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDescription);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDescription),
                group.getID())).thenThrow(new IllegalArgumentException("This category already exists."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addCategoryToGroup(createGroupCategoryDTO);
        });
        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This category already exists.");

    }


    /**
     * Test if a category can be found by the ID
     */
    @Test
    @DisplayName("Test if a category can be found by the ID - Happy Case")
    void getCategoryByCategoryID() {

        //Arrange
        Category category = new Category(new Denomination(categoryDescription), group.getID());

        //arranging mockitos
        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(categoryRepository.getByID(category.getID())).thenReturn(category);

        //DTO expected
        CategoryDTO categoryDTOExpected = new CategoryDTO(categoryDescription, groupDescription);

        //Act
        CategoryDTO categoryDTOResult = service.getCategoryByCategoryID(categoryDescription, groupDescription);

        //Assert
        assertEquals(categoryDTOExpected, categoryDTOResult);
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() {

        //Arrange
        String groupDescription2 = "Just4Fun";

        Category category = new Category(new Denomination(categoryDescription), group.getID());

        //arranging mockitos
        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription2)))
                .thenThrow(new IllegalArgumentException("No group found with that description."));

        Mockito.when(categoryRepository.getByID(category.getID())).thenReturn(category);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription2);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category not found")
    void getCategoryByCategoryIDCategoryNotFound() {

        //Arrange
        String categoryDescription2 = "Dispenses";

        Category category = new Category(new Denomination(categoryDescription2), group.getID());

        //arranging mockitos
        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(categoryRepository.getByID(category.getID())).
                thenThrow(new IllegalArgumentException("No category found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription2, groupDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category null")
    void getCategoryByCategoryIDCategoryNull() {

        //Arrange
        String categoryDescription2 = null;

        //arranging mockitos
        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(categoryRepository.getByID(null)).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription2, groupDescription);
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
        String groupDescription2 = null;

        //arranging mockitos
        Mockito.when(groupsRepository.findGroupByDescription(null))
                .thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        Mockito.when(categoryRepository.getByID(null)).thenReturn(null);

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getCategoryByCategoryID(categoryDescription, groupDescription2);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

}



