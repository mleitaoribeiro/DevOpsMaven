package switch2019.project.controllers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService;

import static org.junit.jupiter.api.Assertions.*;

public class US005_1AdminAddsCategoryControllerTest {
    @Test
    @DisplayName("Happy Case- Category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListHappyCase() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        boolean result = controller.addCategoryToGroupController(groupID, franciscoID, categoryRepository,
                "compras", groupsRepository, personRepository,service);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group member")
    void adminAddsCategoryToCategoryListNotAMember() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //person not in group:
        personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        boolean result = controller.addCategoryToGroupController(groupID, joaoID, categoryRepository,
                "compras", groupsRepository, personRepository,service);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is not a group admin")
    void adminAddsCategoryToCategoryListNotAnAdmin() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //person not in group:
        personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));
        Group thisGroup = groupsRepository.findGroupByID(groupID);
        thisGroup.addMember(personRepository.findPersonByID(joaoID));

        //Act:
        boolean result = controller.addCategoryToGroupController(groupID, joaoID, categoryRepository,
                "compras", groupsRepository, personRepository, service);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListTwoCategories() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        boolean result = (controller.addCategoryToGroupController(groupID, franciscoID, categoryRepository,
                "compras", groupsRepository, personRepository,service)
                && (controller.addCategoryToGroupController(groupID,franciscoID,categoryRepository,
                "supermarket", groupsRepository,personRepository, service)));

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Happy Case- more than one category is added to Group categories by more than one admin")
    void adminAddsCategoryToCategoryListTwoAdmins() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

        //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

        personRepository.createPerson("João", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Joao@gmail.com"));
        PersonID joaoID = new PersonID(new Email("Joao@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));
        groupsRepository.findGroupByID(groupID).setAdmin(personRepository.findPersonByID(joaoID));

        //Act:
        boolean result = (controller.addCategoryToGroupController(groupID, franciscoID, categoryRepository,
                "compras", groupsRepository, personRepository,service)
                && (controller.addCategoryToGroupController(groupID,joaoID,categoryRepository,
                "supermarket", groupsRepository,personRepository,service)));

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Illegal exception caused by null parameter")
    void adminAddsCategoryToCategoryListNullParameter() {

        //Arrange:
            //Arrangement of the Service:
        US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the Controller:
        US005_1AdminAddsCategoryController controller = new US005_1AdminAddsCategoryController();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        try {controller.addCategoryToGroupController(groupID, franciscoID, categoryRepository,
                null, groupsRepository, personRepository,service);}

        //Assert:
        catch(IllegalArgumentException nullParameter) {
            assertEquals("Category could not be added to group because a null object was given as parameter", nullParameter.getMessage());
        }
    }
}

