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
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
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


    String creatorEmail;
    String groupDescription;
    String categoryDenomination;
    Person creator;
    Group group;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        creatorEmail = "rick@gmail.com";
        groupDescription = "SMITH FAMILY";
        categoryDenomination = "ONLINE";

        creator = new Person("Marta", new DateAndTime(1996, 4, 27), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("rick@gmail.com"));

        group = new Group(new Description(groupDescription), creator);
    }

    @Test
    @DisplayName("Test If Group Category is created - Happy Case - Main Scenario")
    void testIfGroupCategoryWasCreatedHappyCase() {

        //ARRANGE
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDenomination);

        Category category = new Category(new Denomination(categoryDenomination), group.getID());

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDenomination),
                group.getID())).thenReturn(category);

        CategoryDTO expected = new CategoryDTO(categoryDenomination, groupDescription);

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
                groupDescription, creatorEmail, categoryDenomination);

        Category category = new Category(new Denomination(categoryDenomination), group.getID());

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDenomination),
                group.getID())).thenReturn(category);

        CategoryDTO expected = new CategoryDTO(categoryDenomination, groupDescription);

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
                groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDenomination),
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

        Category category = new Category(new Denomination(categoryDenomination), group.getID());

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                groupDescription, creatorEmail, categoryDenomination);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(groupDescription)))
                .thenReturn(group);

        Mockito.when(personRepository.findPersonByEmail(new Email(creatorEmail)))
                .thenReturn(creator);

        Mockito.when(categoryRepository.createCategory(new Denomination(categoryDenomination),
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
}



