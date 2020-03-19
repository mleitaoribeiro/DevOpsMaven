package switch2019.project.controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.*;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService;

import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryControllerTest {

    //initialize repositories and service for the tests as attributes:
    private static GroupsRepository groupsRepository;
    private static CategoryRepository categoryRepository;
    private static PersonRepository personRepository;
    private static US005_1AdminAddsCategoryToCategoryListService service;

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
        service = new US005_1AdminAddsCategoryToCategoryListService(groupsRepository,categoryRepository);

        //arrangement of the controller:
        controller = new US005_1AdminAddsCategoryController(service);


        //arrangement of the people:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));
    }



    @Test
    @DisplayName("Happy Case- Category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListHappyCase() {
        //Arrange
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        controller.addCategoryToGroupController(groupID, franciscoID, new Denomination("compras"));
        //verify if the category is in the repository
        boolean result = categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID());

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void adminAddsCategoryToCategoryListNotAMember() {

        //Arrange:
        //Arrangement of the Person:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        controller.addCategoryToGroupController(groupID, joaoID, new Denomination("compras"));

        //verify if the category is not in the repository:
        boolean result = categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID());

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void adminAddsCategoryToCategoryListNotAnAdmin() {

        //Arrange:

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));
        Group thisGroup = groupsRepository.findGroupByID(groupID);
        thisGroup.addMember(personRepository.findPersonByID(joaoID));

        //Act:
        controller.addCategoryToGroupController(groupID, joaoID, new Denomination("compras"));

        //verify if the category is in the repository:
        boolean result = categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID());

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListTwoCategories() {

        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));

        //Act:
        controller.addCategoryToGroupController(groupID, franciscoID, new Denomination("compras"));
        controller.addCategoryToGroupController(groupID,franciscoID,new Denomination("supermarket"));

        //verify if the both categories are in the repository:
        boolean result = (categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID())
                && categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID()));

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by more than one admin")
    void adminAddsCategoryToCategoryListTwoAdmins() {

        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));
        groupsRepository.findGroupByID(groupID).addMember(personRepository.findPersonByID(joaoID));
        groupsRepository.findGroupByID(groupID).setAdmin(personRepository.findPersonByID(joaoID));

        //Act:
        controller.addCategoryToGroupController(groupID, franciscoID, new Denomination("compras"));
        controller.addCategoryToGroupController(groupID,joaoID,new Denomination("supermarket"));

        //verify if the both categories are in the repository:
        boolean result = (categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID())
                && categoryRepository.isCategoryValid(new Category(new Denomination("compras"),groupID).getID()));

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Illegal exception caused by null parameter")
    void adminAddsCategoryToCategoryListNullParameter() {

        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        try {controller.addCategoryToGroupController(groupID, franciscoID, null);}

        //Assert:
        catch(IllegalArgumentException nullParameter) {
            assertEquals("Category could not be added to group because its Description is null", nullParameter.getMessage());
        }
    }

    @Test
    @DisplayName("Illegal exception caused by category already existing in the CategoryRepository")
    void adminAddsDuplicateCategoryToCategoryListTest() {

        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        controller.addCategoryToGroupController(groupID, franciscoID, new Denomination("compras"));
        try {
            controller.addCategoryToGroupController(groupID, franciscoID, new Denomination("compras"));
        }

        //Assert:
        catch (IllegalArgumentException nullParameter) {
            assertEquals("This category already exists and it could not be created", nullParameter.getMessage());
        }
    }
}