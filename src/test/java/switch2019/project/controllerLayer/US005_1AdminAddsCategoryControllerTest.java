package switch2019.project.controllerLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.controllerLayer.controllersCli.US005_1AdminAddsCategoryController;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.repositories.CategoryRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;

import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryControllerTest {

    //initialize repositories and service for the tests as attributes:
    private static GroupsRepository groupsRepository;
    private static CategoryRepository categoryRepository;
    private static PersonRepository personRepository;
    private static US005_1AdminAddsCategoryToGroupService service;

    //initialize service:
    private static US005_1AdminAddsCategoryController controller;

    //using before each for the arrangements before the tests:
    @BeforeEach
    void universeSetUp() {

        //arrangement of repositories:
        groupsRepository = new GroupsRepository();
        categoryRepository = new CategoryRepository();
        personRepository = new PersonRepository();

        //arrangement of the service:
        service = new US005_1AdminAddsCategoryToGroupService(groupsRepository,categoryRepository,personRepository);

        //arrangement of the controller:
        controller = new US005_1AdminAddsCategoryController(service);


        //arrangement of the people:
        Person personFrancisco = personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        Person personJoao = personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));

        Person personAna = personRepository.createPerson("Ana", new DateAndTime(1993,05,25), new Address("Lisboa"),
                new Address("Rua Y","Lisboa","4200-000"), new Email("Ana@hotmail.com"));

        //arrangement of the groups:
        groupsRepository.createGroup(new Description("FRIENDS"), personFrancisco);
        groupsRepository.createGroup(new Description("HOCKEY TEAM"), personAna);

        //add members to each group:
        Group friendsGroup = (groupsRepository.findGroupByDescription(new Description("FRIENDS")));
        friendsGroup.addMember(personJoao);

        Group hockeyTeamGroup = (groupsRepository.findGroupByDescription(new Description("HOCKEY TEAM")));
        hockeyTeamGroup.addMember(personFrancisco);
        hockeyTeamGroup.setAdmin(personFrancisco);
        hockeyTeamGroup.addMember(personJoao);
    }

    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupServiceTestEqual() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";

            //Arrangement of the output DTO:
        CategoryID catID = new CategoryID(new Denomination(categoryDenomination), new GroupID(new Description(groupDescription)));
        CategoryDTO outputDtoExpected = new CategoryDTO(categoryDenomination, groupDescription);

        //Act:
        CategoryDTO outputActual = controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);

        //Assert:
        assertEquals(outputDtoExpected, outputActual);
    }

    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupControllerTest() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";

            //Check the number of categories before creating the new Category:
        int expectedCategoriesBefore = 0;
        int actualCategoriesBefore = categoryRepository.repositorySize();

            //Arrangement of the output DTO:

        CategoryDTO outputDtoExpected = new CategoryDTO(categoryDenomination, groupDescription);

        //Act:
        CategoryDTO outputActual = controller.addCategoryToGroupController(creatorEmail, groupDescription, categoryDenomination);

            //Check the number of categories in the repository after creating the new Category
        int expectedCategoriesAfter = 1;
        int actualCategoriesAfter = categoryRepository.repositorySize();

        //Assert
              /*this assertion verifies three conditions to check if our Controller worked as intended:
             * - The created CategoryDTO is compared to the Expected CategoryDTO.
             * - The Repository size before adding the Category is checked against the expected (0).
             * - The Repository size after adding the Category is checked against the expected (1).
              */
        Assertions.assertAll(
                () -> assertEquals(outputDtoExpected, outputActual),
                () -> assertEquals(expectedCategoriesBefore, actualCategoriesBefore),
                () -> assertEquals(expectedCategoriesAfter, actualCategoriesAfter)
        );
    }

    @Test
    @DisplayName("Test False for the creation of the account using the Controller -Different DTOs")
    void addCategoryToGroupServiceTestNotEqual() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";
        String categoryDenomination2 = "CINEMA"; // category denomination will be different on the dtos

            //Arrangement of the output DTO:

        CategoryDTO outputDtoExpected = new CategoryDTO(categoryDenomination2, groupDescription);

        //Act:
        CategoryDTO outputActual = controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);

        //Assert:
        assertNotEquals(outputDtoExpected, outputActual);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void adminAddsCategoryToCategoryListNotAMember() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Ana@hotmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";

            //expected number of categories after we try to add it with a non-member (0):
        int expectedCategories = 0;

        //Act:
        try {
            controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);
        }
        catch (IllegalArgumentException notGroupMember){
            int actualCategories = categoryRepository.repositorySize();

            //Assert
            /*   This assertion verifies two conditions to check if our Controller worked as intended:
             * - Verifies if the output is not present since a category wasnt created (the controller wont return a CategoryDTO);
             * - The Repository size after adding the Category is checked against the expected (0).
             */

            Assertions.assertAll(
                    () -> assertEquals("This person is not a group admin or member and could not add the category.", notGroupMember.getMessage()),
                    () -> assertEquals(expectedCategories, actualCategories)
            );
        }
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void adminAddsCategoryToCategoryListNotAnAdmin() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Joao@gmail.com"; // Member but not an admin.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";


            //expected number of categories after we try to add it with a non-member (0):
        int expectedCategories = 0;

        //Act:
        try {
            controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);
        }
        catch (IllegalArgumentException notGroupAdmin) {
            int actualCategories = categoryRepository.repositorySize();

            //Assert
            /*   This assertion verifies two conditions to check if our Controller worked as intended:
             * - Verifies if the output is not present since a category wasnt created (the controller wont return a CategoryDTO);
             * - The Repository size after adding the Category is checked against the expected (0).
             */

            Assertions.assertAll(
                    () -> assertEquals("This person is not a group admin or member and could not add the category.", notGroupAdmin.getMessage()),
                    () -> assertEquals(expectedCategories, actualCategories)
            );
        }
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListTwoCategories() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
            //Two Denominations (one for each Dto)
        String categoryDenomination1 = "COMPRAS";
        String categoryDenomination2 = "CINEMA";

            //Check the number of categories before creating the new Category:
        int expectedCategoriesBefore = 0;
        int actualCategoriesBefore = categoryRepository.repositorySize();

            //Arrangement of the expected output DTOs:

        CategoryDTO outputDtoExpected1 = new CategoryDTO(categoryDenomination1, groupDescription);
        CategoryDTO outputDtoExpected2 = new CategoryDTO(categoryDenomination2, groupDescription);

        //Act:
            //We then run our controller for each input DTO:
        CategoryDTO outputActual1 = controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination1);
        CategoryDTO outputActual2 = controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination2);

            //Check the number of categories in the repository after creating the new Category (2 categories created):
        int expectedCategoriesAfter = 2;
        int actualCategoriesAfter = categoryRepository.repositorySize();

            //Assert
            /*this assertion verifies four conditions to check if our Controller worked as intended:
            * - The first created CategoryDTO is compared to the first Expected CategoryDTO.
            * - The second created CategoryDTO is compared to the second Expected CategoryDTO.
            * - The Repository size before adding the Categories is checked against the expected (0).
            * - The Repository size after adding the Categories is checked against the expected (2).
            */
        Assertions.assertAll(
                () -> assertEquals(outputDtoExpected1, outputActual1),
                () -> assertEquals(outputDtoExpected2, outputActual2),
                () -> assertEquals(expectedCategoriesBefore, actualCategoriesBefore),
                () -> assertEquals(expectedCategoriesAfter, actualCategoriesAfter)
        );
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by more than one admin")
    void adminAddsCategoryToCategoryListTwoAdmins() {
        //Arrange:
            //Arrangement of the entry DTO for the service:
        String creatorEmail1 = "Ana@hotmail.com";
        String creatorEmail2 = "Francisco@gmail.com";
        String groupDescription = "HOCKEY TEAM";
            //Two Denominations (one for each Dto)
        String categoryDenomination1 = "COMPRAS";
        String categoryDenomination2 = "CINEMA";

            //Check the number of categories before creating the new Category:
        int expectedCategoriesBefore = 0;
        int actualCategoriesBefore = categoryRepository.repositorySize();

            //Arrangement of the expected output DTOs:
        CategoryID catID1 = new CategoryID(new Denomination(categoryDenomination1), new GroupID(new Description(groupDescription)));
        CategoryDTO outputDtoExpected1 = new CategoryDTO(categoryDenomination1, groupDescription);

        CategoryID catID2 = new CategoryID(new Denomination(categoryDenomination2), new GroupID(new Description(groupDescription)));
        CategoryDTO outputDtoExpected2 = new CategoryDTO(categoryDenomination2, groupDescription);

        //Act:
            //We then run our controller for each input DTO:
        CategoryDTO outputActual1 = controller.addCategoryToGroupController(creatorEmail1,groupDescription,categoryDenomination1);
        CategoryDTO outputActual2 = controller.addCategoryToGroupController(creatorEmail2,groupDescription,categoryDenomination2);

            //Check the number of categories in the repository after creating the new Category (2 categories created):
        int expectedCategoriesAfter = 2;
        int actualCategoriesAfter = categoryRepository.repositorySize();

            //Assert
            /*this assertion verifies four conditions to check if our Controller worked as intended:
            * - The first created CategoryDTO is compared to the first Expected CategoryDTO.
            * - The second created CategoryDTO is compared to the second Expected CategoryDTO.
            * - The Repository size before adding the Categories is checked against the expected (0).
            * - The Repository size after adding the Categories is checked against the expected (2).
            */
        Assertions.assertAll(
                () -> assertEquals(outputDtoExpected1, outputActual1),
                () -> assertEquals(outputDtoExpected2, outputActual2),
                () -> assertEquals(expectedCategoriesBefore, actualCategoriesBefore),
                () -> assertEquals(expectedCategoriesAfter, actualCategoriesAfter)
        );
    }


    @Test
    @DisplayName("Illegal exception caused by null category")
    void adminAddsCategoryToCategoryListNullCategory() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
        String categoryDenomination = null;

        //Act:
        try {
            controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("The denomination can´t be null or empty.", nullParameter.getMessage());
        }
    }

    @Test
    @DisplayName("Illegal exception caused by category already existing in the CategoryRepository")
    void adminAddsDuplicateCategoryToCategoryListTest() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDescription = "FRIENDS";
        String categoryDenomination = "compras";
        CreateGroupCategoryDTO dto = new CreateGroupCategoryDTO(creatorEmail,groupDescription,categoryDenomination);

        //Act:
        controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);
        try {
            controller.addCategoryToGroupController(creatorEmail,groupDescription,categoryDenomination);
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("This category already exists and it could not be created.", nullParameter.getMessage());
        }
    }
}
