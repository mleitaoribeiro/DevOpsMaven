package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US002_1CreateGroupAndBecomeAdminService;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminControllerTest {

    @Test
    void createGroupAndBecomeAdmin() {

        //Arrange
        US002_1CreateGroupAndBecomeAdminController us002_1C = new US002_1CreateGroupAndBecomeAdminController();
        US002_1CreateGroupAndBecomeAdminService us002_1S = new US002_1CreateGroupAndBecomeAdminService();

        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        GroupsRepository groupsRepository = new GroupsRepository();
        PersonRepository personRepository = new PersonRepository();
        personRepository.createPerson("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto", "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        //Act
        boolean result = us002_1C.createGroupAndBecomeAdmin(groupsRepository, personRepository, us002_1S, groupDescription, personID);

        //Assert
        assertTrue(result);
    }
}