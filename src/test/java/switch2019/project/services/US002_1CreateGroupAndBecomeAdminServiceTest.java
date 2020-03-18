package switch2019.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.US002_1CreateGroupAndBecomeAdminController;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminServiceTest {

    private static GroupsRepository groupsRepository;
    private static PersonRepository personRepository;
    private static US002_1CreateGroupAndBecomeAdminService service;


    @BeforeEach
    void setUpUniverse() {
        groupsRepository = new GroupsRepository();
        personRepository = new PersonRepository();

        personRepository.createPerson("Alexandre", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto",
                        "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));

        service = new US002_1CreateGroupAndBecomeAdminService(groupsRepository, personRepository);

    }


    @Test
    void createGroupAndBecomeAdmin() {
        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("1234@isep.pt"));

        //Act
        boolean result = service.createGroupAndBecomeAdmin(groupDescription, personID);

        //Assert
        assertTrue(result);

    }

    @Test
    void createGroupAndBecomeAdminNoPersonID() {

        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("12345@isep.pt"));

        //Act
        try {
            service.createGroupAndBecomeAdmin(groupDescription, personID);
        }

        //Assert
        catch (IllegalArgumentException e) {
            assertEquals("No person found with that ID.", e.getMessage());
        }
    }

    @Test
    void createGroupAndGroupAlreadyExists() {
        //Arrange
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID(new Email("1234@isep.pt"));
        service.createGroupAndBecomeAdmin(groupDescription, personID);

        //Act
        boolean result = service.createGroupAndBecomeAdmin(groupDescription, personID);

        //Assert
        assertFalse(result);

    }
}