package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupDbRepositoryTest {

    @Autowired
    PersonRepository personDbRepository;

    @Autowired
    GroupRepository groupDbRepository;

    private Group switchG1;
    private PersonID swtichG1Admin;
    private Group switchG2;
    private PersonID switchG2Admin;
    private Group switchG3;
    private PersonID switchG3Admin;

    @BeforeEach
    public void setup() {
        swtichG1Admin = new PersonID(new Email("rita@isep.ip.pt"));
        switchG2Admin = new PersonID(new Email("elsa@isep.ip.pt"));
        switchG3Admin = new PersonID(new Email("joao@isep.ip.pt"));

        switchG1 = groupDbRepository.createGroup(new Description("switch g1"), swtichG1Admin);
        switchG2 = groupDbRepository.createGroup(new Description("switch g2"), switchG2Admin);
        switchG3 = groupDbRepository.createGroup(new Description("switch g3"), switchG3Admin);

        groupDbRepository.addMember(switchG1, switchG2Admin.toString());
        groupDbRepository.addMember(switchG1, switchG3Admin.toString());
        groupDbRepository.setAdmin(switchG1, switchG2Admin.toString());
    }

    @Test
    @DisplayName("Test if group is created and saved - main scenario")
    public void createGroup() {
        //Arrange
        Long expectedSize = groupDbRepository.repositorySize() + 1;
        Group expectedGroup = new Group(new Description("TEAM ROCKET"), new PersonID(new Email("jessie@gmail.com")));

        //Act
        Group resultGroup = groupDbRepository.createGroup(new Description("TEAM ROCKET"), new PersonID(new Email("jessie@gmail.com")));

        //Assert
        Assertions.assertAll(
                () -> Assert.notNull(groupDbRepository.getByID(resultGroup.getID()), "Group saved is found."),
                () -> assertEquals(expectedSize, groupDbRepository.repositorySize()),
                () -> assertEquals(expectedGroup, resultGroup)
        );
    }

    @Test
    @DisplayName("Test if group is find in jpaRepository - Main Scenario")
    void findGroupByDescription() {
        //Arrange
        Group expectedGroup = switchG2;

        //Act
        Group resultGroup = groupDbRepository.findGroupByDescription(new Description("switch g2"));

        //Assert
        assertEquals(expectedGroup, resultGroup);

    }

    @Test
    @DisplayName("Test if group is find in jpaRepository - Exception")
    void findGroupByDescriptionException() {
        //Act
        Throwable thrown = catchThrowable(() -> {
            groupDbRepository.findGroupByDescription(new Description("team"));
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if group is returned from jpaRepository by its id - exception")
    void getByIDException() {
        //Act
        Throwable thrown = catchThrowable(() -> {
            groupDbRepository.getByID(new GroupID(new Description("TENNIS")));
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test if group is returned from jpaRepository by its id - main ")
    void getByID() {
        //Arrange
        Group expectedGroup = switchG3;

        //Act
        Group resultGroup = groupDbRepository.getByID(switchG3.getID());

        //Assert
        assertEquals(expectedGroup, resultGroup);
    }

    @Test
    @DisplayName("Test if all groups are returned")
    void getAllGroups() {
    /*   //Arrange
       //SWITCH, FRIENDS, SPLIT EXPENSES, RICK AND MORTY, INTERGALACTIC, SMITH FAMILY, FAMILY SIMPSON, FAMILY CARDOSO, FAMILY AZEVEDO, SWITCH G1, SWITCH G2, SWITCH G3]

        List<Group> expectedGroups = new LinkedList<>(Arrays.asList(switchG1, switchG2, switchG3));

        //Act
        List<Group> resultGroups = groupDbRepository.getAllGroups();

        //Assert
        assertEquals(expectedGroups, resultGroups);*/
    }


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

    /**
     * Validate if an admin was added to a group
     */
    @Test
    @DisplayName("Test if an admin was added to a group")
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
}