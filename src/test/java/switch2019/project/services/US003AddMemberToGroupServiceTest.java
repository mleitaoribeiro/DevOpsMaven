package switch2019.project.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

public class US003AddMemberToGroupServiceTest {


    private static PersonRepository personRepository;
    private static GroupsRepository groupsRepository;
    private static US003AddMemberToGroupService service;

    @BeforeAll

    static void universeSetUp() {
        personRepository = new PersonRepository();
        groupsRepository = new GroupsRepository();
        service = new US003AddMemberToGroupService(personRepository, groupsRepository);


        //Add people to Repository
        Person person = personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        Person person2 = personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jo.cardoso@hotmail.com"));
        Person person3 = personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));

        //Add groups to Repository

        groupsRepository.createGroup(new Description("familia"), person);
        groupsRepository.createGroup(new Description("canto"), person3);
    }

    /**
     * Test to add member to group
     */

    @Test
    @DisplayName("Test if a member was added to group")
    void addMemberToGroup() {
        //Arrange
        String personEmail = "jo.cardoso@hotmail.com";
        String groupDescription = "familia";

        //Act

        boolean memberAdded = service.addMemberToGroup(personEmail, groupDescription);

        //Assert
        assertTrue(memberAdded);
    }

    @Test
    @DisplayName("Test if a member was added to group-Same Person")
    void addMemberToGroupAlreadyIn() {
        //Arrange
        String personEmail = "jose.cardoso@hotmail.com";
        String groupDescription = "familia";

        //Act

        boolean memberAdded = service.addMemberToGroup(personEmail, groupDescription);

        //Assert
        assertFalse(memberAdded);
    }

    @Test
    @DisplayName("Test if a member was added to group-Invalid Person ID")
    void addMemberToGroupInvalidPersonID() {
        //Arrange
        String personEmail = "jp@ip.pt";
        String groupDescription = "familia";

        //Act
        try {
            service.addMemberToGroup(personEmail, groupDescription);
        }

        //Assert
        catch (IllegalArgumentException email) {
            assertEquals("No person found with that email.", email.getMessage());
        }
    }
}
