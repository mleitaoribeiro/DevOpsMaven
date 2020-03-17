package switch2019.project.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class US005_1AdminAddsCategoryToCategoryListServiceTest {

    @Test
    @DisplayName("Happy Case- Category is added to Group categories by an admin")
    void adminAddsCategoryToCategoryListHappyCase() {

        //Arrange:
            //Arrangement of the Service:
            US005_1AdminAddsCategoryToCategoryListService service = new US005_1AdminAddsCategoryToCategoryListService();

            //Arrangement of the repositories:
        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

            //Arrangement of the Person:
        personRepository.createPerson("Francisco", new DateAndTime(1994,04,16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        PersonID franciscoID = new PersonID(new Email("Francisco@gmail.com"));

            //Arrangement of the Group:
        groupsRepository.createGroup("FRIENDS", personRepository.findPersonByID(franciscoID));
        GroupID groupID = new GroupID(new Description("FRIENDS"));

        //Act:
        boolean result = service.addCategoryToGroup(groupID,franciscoID,categoryRepository,"compras",groupsRepository,personRepository);
        
        //Assert:
        assertTrue(result);
    }
}
