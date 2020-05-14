package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupDbRepositoryTest {

    @Autowired
    PersonDbRepository personDbRepository;

    @Autowired
    GroupDbRepository groupDbRepository;

    /**
     * Validate if a member was added to a group
     */
    @Test
    @DisplayName("Test if a member was added to a group")
    void addMember() {

        //Arrange
        Person person1 = personDbRepository.createPerson("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("marta@isep.ipp.pt"));
        Group group1 = groupDbRepository.createGroup(new Description("OsMaisFixes"), person1.getID());
        String personID = "ricardo@sapo.pt";

        //Act
        boolean addedMember = groupDbRepository.addMember(group1, personID);

        //Assert
        assertTrue(addedMember);
    }

    @Test
    @DisplayName("Test if a member was not added to a group - same person")
    void addMemberFalse() {

        //Arrange
        Person person1 = personDbRepository.createPerson("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("marta@isep.ipp.pt"));
        Group group1 = groupDbRepository.createGroup(new Description("OsMaisFixes"), person1.getID());
        String personID = "marta@isep.ipp.pt";

        //Act
        boolean addedMember = groupDbRepository.addMember(group1, personID);

        //Assert
        assertFalse(addedMember);
    }

    @Test
    @DisplayName("Test if a member was not added to a group - null personID")
    void addMemberNull() {

        //Arrange
        Person person1 = personDbRepository.createPerson("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("marta@isep.ipp.pt"));
        Group group1 = groupDbRepository.createGroup(new Description("OsMaisFixes"), person1.getID());
        String personID = null;

        //Act
        boolean addedMember = groupDbRepository.addMember(group1, personID);

        //Assert
        assertFalse(addedMember);
    }

    @Test
    void findMembersByGroupId() {
    }

    /**
     * Validate if an admin was added to a group
     */
    @Test
    @DisplayName("Test if am admin was added to a group")
    void setAdmin() {

        //Arrange
        Person person1 = personDbRepository.createPerson("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("marta@isep.ipp.pt"));
        Group group1 = groupDbRepository.createGroup(new Description("OsMaisFixes"), person1.getID());
        String personID = "ricardo@sapo.pt";
        groupDbRepository.addMember(group1, personID);

        //Act
        boolean addedAdmin = groupDbRepository.setAdmin(group1, personID);

        //Assert
        assertTrue(addedAdmin);
    }

    @Test
    @DisplayName("Test if am admin was not added to a group - already admin")
    void setAdminFalse() {

        //Arrange
        Person person1 = personDbRepository.createPerson("Marta", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"), new Email("marta@isep.ipp.pt"));
        Group group1 = groupDbRepository.createGroup(new Description("OsMaisFixes"), person1.getID());
        String personID = "marta@isep.ipp.pt";

        //Act
        boolean addedAdmin = groupDbRepository.setAdmin(group1, personID);

        //Assert
        assertFalse(addedAdmin);
    }

    @Test
    void findAdminsByGroupId() {
    }
}