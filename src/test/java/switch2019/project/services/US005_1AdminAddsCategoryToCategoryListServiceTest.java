package switch2019.project.services;

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


import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryToCategoryListServiceTest {

    //initialize repositories and service for the tests as attributes:
    private static GroupsRepository groupsRepository;
    private static CategoryRepository categoryRepository;
    private static PersonRepository personRepository;
    private static US005_1AdminAddsCategoryToCategoryListService service;

    //using before each for the arrangements before the tests:
    @BeforeEach
    void universeSetUp() {

        //arrangement of repositories:
        groupsRepository = new GroupsRepository();
        categoryRepository = new CategoryRepository();
        personRepository = new PersonRepository();

        //arrangement of the service:
        service = new US005_1AdminAddsCategoryToCategoryListService(groupsRepository,categoryRepository,personRepository);

        //arrangement of the people:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));
    }

    @Test
    @DisplayName("Test True for the creation of the account using the Service")
    void addCategoryToGroupServiceTestTrue() {
        //Arrange
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));

        //Act:
        boolean result = service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", "compras");

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test False for the creation of the account using the Service")
    void addCategoryToGroupServiceTestFalse() {
        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));
        PersonID joaoID = new PersonID(new Email("joao@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));

        //Act:
        boolean result = service.addCategoryToGroup("FRIENDS", "joao@gmail.com", "compras");

        //Assert:
        assertFalse(result);
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
        service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com","compras");
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

            //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        service.addCategoryToGroup("FRIENDS", "Joao@gmail.com","compras");

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
        service.addCategoryToGroup("FRIENDS", "Joao@gmail.com","compras");

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
        service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", "compras");
        service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", "supermarket");

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
        service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", "compras");
        service.addCategoryToGroup("FRIENDS", "Joao@gmail.com","supermarket");

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


        //Act:
        try {service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", null);}

        //Assert:
        catch(IllegalArgumentException nullParameter) {
            assertEquals("The denomination can´t be null or empty!", nullParameter.getMessage());
        }
    }

    @Test
    @DisplayName("Illegal exception caused by category already existing in the CategoryRepository")
    void adminAddsDuplicateCategoryToCategoryListTest() {

        //Arrange:
        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        //Arrangement of the Group:
        groupsRepository.createGroup(new Description("FRIENDS"), personRepository.findPersonByID(franciscoID));


        //Act:
        service.addCategoryToGroup("FRIENDS","Francisco@gmail.com", "compras");
        try {service.addCategoryToGroup("FRIENDS", "Francisco@gmail.com", "compras");}

        //Assert:
        catch(IllegalArgumentException nullParameter) {
            assertEquals("This category already exists and it could not be created", nullParameter.getMessage());
        }
    }
}
