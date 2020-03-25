package switch2019.project.applicationLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.repositories.CategoryRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryToCategoryListServiceTest {

    //initialize repositories and service for the tests as attributes:
    private static GroupsRepository groupsRepository;
    private static CategoryRepository categoryRepository;
    private static PersonRepository personRepository;
    private static switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService service;

    //using before each for the arrangements before the tests:
    @BeforeEach
    void universeSetUp() {

        //arrangement of repositories:
        groupsRepository = new GroupsRepository();
        categoryRepository = new CategoryRepository();
        personRepository = new PersonRepository();

        //arrangement of the service:
        service = new switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService(groupsRepository, categoryRepository, personRepository);

        //arrangement of the persons:
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
    @DisplayName("Check if the created Category is the expected")
    void addCategoryToGroupServiceTestTrue() {
        //Arrange
            //Arrangement of the entry DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

            //Category that will be created:
        Category categoryExpected = new Category(new Denomination(categoryDenomination), new GroupID(new Description(groupDesctiption)));

        //Act:
        Category categoryCreated = service.addCategoryToGroup(dto).get();

        //Assert:
        assertEquals(categoryExpected,categoryCreated);
    }

    @Test
    @DisplayName("Check if the created Category is not the expected")
    void addCategoryToGroupServiceTestFalse() {
        //Arrange:
            //Arrangement of the entry DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

            //Category that will be created:
        Category categoryExpected = new Category(new Denomination(categoryDenomination), new GroupID(new Description("HOCKEY TEAM")));

        //Act:
        Category categoryCreated = service.addCategoryToGroup(dto).get();

        //Assert:
        assertNotEquals(categoryExpected,categoryCreated);
    }


    @Test
    @DisplayName("Happy Case - Category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListHappyCase() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

            //Variabales used to check the categoryRepository size BEFORE adding the new Category:
        int expectedRepositoryBefore = 0;
        int actualRepositoryBefore = categoryRepository.numberOfCategoriesInRepository();

            //Arrangement of the Category:
        Category categoryExpected = new Category(new Denomination(categoryDenomination), new GroupID(new Description(groupDesctiption)));

        //Act:
        Category categoryActual = service.addCategoryToGroup(dto).get();

            //Variables used to check the categoryRepository size AFTER adding the new Category:
        int expectedRepositoryAfter = 1;
        int actualRepositoryAfter = categoryRepository.numberOfCategoriesInRepository();

        //Assert:
            /*this assertion verifies three conditions to check if our Service worked as intended:
            * - The created category is compared to the Expected Category.
            * - The Repository size before adding the Category is checked against the expected (0).
            * - The Repository size after adding the Category is checked against the expected (1).
            * */
        Assertions.assertAll(
                () -> assertEquals(categoryExpected, categoryActual),
                () -> assertEquals(expectedRepositoryBefore, actualRepositoryBefore),
                () -> assertEquals(expectedRepositoryAfter, actualRepositoryAfter)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void adminAddsCategoryToCategoryListNotAMember() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Ana@hotmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
            //this time the DTO has a creator Email that doesnt refer to a member of the group friendsGroup:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

            //Variabales used to check the categoryRepository size BEFORE adding the new Category:
        int expectedRepositoryBefore = 0;
        int actualRepositoryBefore = categoryRepository.numberOfCategoriesInRepository();

            //Arrangement of the Category:
        Optional<Category> expectedResult = Optional.empty(); //we now expect our method to return an Optional.empty()
                                                              //since our Category was not added to the Group.

        //Act:
        Optional<Category> categoryActual = service.addCategoryToGroup(dto);

            //Variables used to check the categoryRepository size AFTER adding the new Category:
        int expectedRepositoryAfter = 0; //we now expect 0 Categories to be added since personAna is not a group member.
        int actualRepositoryAfter = categoryRepository.numberOfCategoriesInRepository();

        //Assert:
            /*this assertion verifies three conditions to check if our Service worked as intended:
            * - The created category is compared to the Expected Category.
            * - The Repository size before adding the Category is checked against the expected (0).
            * - The Repository size after adding the Category is checked against the expected (1).
            * */
        Assertions.assertAll(
                () -> assertEquals(expectedResult, categoryActual),
                () -> assertEquals(expectedRepositoryBefore, actualRepositoryBefore),
                () -> assertEquals(expectedRepositoryAfter, actualRepositoryAfter)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void adminAddsCategoryToCategoryListNotAnAdmin() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Joao@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
            //this time the DTO has a creator Email that doesnt refer to an admin (personJoao is just a member) of the group friendsGroup:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

            //Variabales used to check the categoryRepository size BEFORE adding the new Category:
        int expectedRepositoryBefore = 0;
        int actualRepositoryBefore = categoryRepository.numberOfCategoriesInRepository();

            //Arrangement of the Category:
        Optional<Category> expectedResult = Optional.empty(); //we now expect our method to return an Optional.empty()
            //since our Category was not added to the Group.

        //Act:
        Optional<Category> categoryActual = service.addCategoryToGroup(dto);

            //Variables used to check the categoryRepository size AFTER adding the new Category:
        int expectedRepositoryAfter = 0; //we now expect 0 Categories to be added since personJoao is not a group member.
        int actualRepositoryAfter = categoryRepository.numberOfCategoriesInRepository();

        //Assert:
            /*this assertion verifies three conditions to check if our Service worked as intended:
            * - The created category is compared to the Expected Category.
            * - The Repository size before adding the Category is checked against the expected (0).
            * - The Repository size after adding the Category is checked against the expected (1).
             * */
        Assertions.assertAll(
                () -> assertEquals(expectedResult, categoryActual),
                () -> assertEquals(expectedRepositoryBefore, actualRepositoryBefore),
                () -> assertEquals(expectedRepositoryAfter, actualRepositoryAfter)
        );
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListTwoCategories() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination1 = "compras";
        String categoryDenomination2 = "cinema";
            //We now create 2 DTOs since we need to run the service two times to add both the Categories
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination1);
        CreateCategoryInGroupDTO dto2 = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination2);

            //Variabales used to check the categoryRepository size BEFORE adding the new Category:
        int expectedRepositoryBefore = 0;
        int actualRepositoryBefore = categoryRepository.numberOfCategoriesInRepository();

            //Arrangement of the Categories:
        Category categoryExpected1 = new Category(new Denomination(categoryDenomination1), new GroupID(new Description(groupDesctiption)));
        Category categoryExpected2 = new Category(new Denomination(categoryDenomination2), new GroupID(new Description(groupDesctiption)));

        //Act:
            //we now execute our service twice (once for each dto), to add both Categories:
        Category categoryActual1 = service.addCategoryToGroup(dto).get();
        Category categoryActual2 = service.addCategoryToGroup(dto2).get();

            //Variables used to check the categoryRepository size AFTER adding the new Category:
        int expectedRepositoryAfter = 2; //We now expect 2 Categories in the Repository after we add both with the service.
        int actualRepositoryAfter = categoryRepository.numberOfCategoriesInRepository();

        //Assert:
            /*this assertion verifies four conditions to check if our Service worked as intended:
            * - The first category is compared to the first Expected Category.
            * - The second category is compared to the second Expected Category.
            * - The Repository size before adding the Category is checked against the expected (0).
            * - The Repository size after adding the Category is checked against the expected (1).
             * */
        Assertions.assertAll(
                () -> assertEquals(categoryExpected1, categoryActual1),
                () -> assertEquals(categoryExpected2, categoryActual2),
                () -> assertEquals(expectedRepositoryBefore, actualRepositoryBefore),
                () -> assertEquals(expectedRepositoryAfter, actualRepositoryAfter)
        );
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by more than one admin")
    void adminAddsCategoryToCategoryListTwoAdmins() {
        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Ana@hotmail.com";
        String creatorEmail2 = "Francisco@gmail.com"; // creator of the second Category.
        String groupDesctiption = "HOCKEY TEAM";
        String categoryDenomination1 = "compras";
        String categoryDenomination2 = "cinema"; // denomination of the second Category.

            //this time we create a dto for each creator:
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination1);
        CreateCategoryInGroupDTO dto2 = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail2,categoryDenomination2);

            //Variabales used to check the categoryRepository size BEFORE adding the new Category:
        int expectedRepositoryBefore = 0;
        int actualRepositoryBefore = categoryRepository.numberOfCategoriesInRepository();

            //Arrangement of the Category:
                //We create one expected category for each category that will be added:
        Category categoryExpected1 = new Category(new Denomination(categoryDenomination1), new GroupID(new Description(groupDesctiption)));
        Category categoryExpected2 = new Category(new Denomination(categoryDenomination2), new GroupID(new Description(groupDesctiption)));

        //Act:
            //We now run the service method with both of the DTOs to create the 2 Categories
        Category categoryActual1 = service.addCategoryToGroup(dto).get();
        Category categoryActual2 = service.addCategoryToGroup(dto2).get();

            //Variables used to check the categoryRepository size AFTER adding the new Category:
        int expectedRepositoryAfter = 2; //we now expect 2 Categories to be added since personAna and personFrancisco are both admins.
        int actualRepositoryAfter = categoryRepository.numberOfCategoriesInRepository();

        //Assert:
            /*this assertion verifies four conditions to check if our Service worked as intended:
             * - The first category is compared to the first Expected Category (created by personAna).
             * - The second category is compared to the second Expected Category (created by personFrancisco)
             * - The Repository size before adding the Category is checked against the expected (0).
             * - The Repository size after adding the Category is checked against the expected (1).
            * */
        Assertions.assertAll(
                () -> assertEquals(categoryExpected1, categoryActual1),
                () -> assertEquals(categoryExpected2, categoryActual2),
                () -> assertEquals(expectedRepositoryBefore, actualRepositoryBefore),
                () -> assertEquals(expectedRepositoryAfter, actualRepositoryAfter)
        );
    }

    @Test
    @DisplayName("Illegal exception caused by null parameter")
    void adminAddsCategoryToCategoryListNullParameter() {

        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = null;
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

        //Act:
        try {
            service.addCategoryToGroup(dto);
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("The denomination can´t be null or empty!", nullParameter.getMessage());
        }
    }

    @Test
    @DisplayName("Illegal exception caused by category already existing in the CategoryRepository")
    void adminAddsDuplicateCategoryToCategoryListTest() {

        //Arrange:
            //Arrangement of the DTO:
        String creatorEmail = "Francisco@gmail.com";
        String groupDesctiption = "FRIENDS";
        String categoryDenomination = "compras";
        CreateCategoryInGroupDTO dto = new CreateCategoryInGroupDTO(groupDesctiption,creatorEmail,categoryDenomination);

        //Act:
        service.addCategoryToGroup(dto);
        try {
            service.addCategoryToGroup(dto);
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("This category already exists and it could not be created", nullParameter.getMessage());
        }
    }
}
