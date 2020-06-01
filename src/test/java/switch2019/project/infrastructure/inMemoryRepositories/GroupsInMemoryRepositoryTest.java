package switch2019.project.infrastructure.inMemoryRepositories;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class GroupsInMemoryRepositoryTest {

    /**
     * As user , I Want to Creat a group and be admin (US002.1)
     */

    @Test
    @DisplayName("Test if Group was Created")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Person person1 = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group expected = new Group(new Description("Test Person"), person1.getID());

        //Act
        Group groupCreated = groupsRepository.createGroup(new Description("Test Person"), person1.getID());

        //Assert
        assertEquals(expected, groupCreated);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Person person1 = new Person("Francis", new DateAndTime(2001, 4, 12), new Address("Dublin"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));

        //Act
        try {
            groupsRepository.createGroup(null, person1.getID());
        } catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group was not created when it is already contained in the repository")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Person person1 = new Person("Amy", new DateAndTime(1990, 12, 04), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));

        //Act
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1.getID());
        try {
            groupsRepository.createGroup(new Description("Grupo de Teste"), person1.getID());
        } catch (ResourceAlreadyExistsException ex) {
            assertEquals("This group description already exists.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group is created even it has the same name but different members")
    public void createGroupWithSameDescriptionAndDifferentMembers() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Person person1 = new Person("Amy", new DateAndTime(1990, 12, 4), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));
        Person person2 = new Person("Marshall", new DateAndTime(1990, 12, 4), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("123@isep.pt"));
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1.getID());
        //Act
        try {
            groupsRepository.createGroup(new Description("Grupo de Teste"), person2.getID());
        }
        //Assert
        catch (ResourceAlreadyExistsException ex) {
            assertEquals("This group description already exists.", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group is created with different description but same person")
    public void createGroupWithDifferentDescriptionAndDifferentMembers() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Person person1 = new Person("Amy", new DateAndTime(1999, 5, 13), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));

        Group expected = new Group(new Description("Grupo Diferente"), person1.getID());

        //Act
        groupsRepository.createGroup(new Description("Grupo de Teste"), person1.getID());

        Group groupCreated = groupsRepository.createGroup(new Description("Grupo Diferente"), person1.getID());

        //Assert
        assertEquals(expected, groupCreated);
    }

    @Test
    @DisplayName("Test if group was not created when its null")
    public void testIfWasCreatedWhenNull() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        PersonID person1 = null;

        //Act
        try {
            groupsRepository.createGroup(null, person1);
        } catch (IllegalArgumentException ex) {
            assertEquals("The description can't be null.", ex.getMessage());
        }
    }

    /**
     * Check the number of Groups inside the groupList
     */
    @Test
    @DisplayName("Test if the number of groups on the list was increased")
    public void howManyGroupsTest() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        GroupRepository groupList = new GroupsInMemoryRepository();

        //Act
        groupList.createGroup(new Description("Amigos"), person.getID());
        groupList.createGroup(new Description("Pokémons"), person.getID());
        long result = groupList.repositorySize();

        //Assert
        assertEquals(2, result);
    }


    @Test
    void findGroupByID() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());
        Group expected = new Group(new Description("Familia"), person.getID());

        //Act
        Group actual = groupsRepository.getByID(new GroupID(new Description("Familia")));

        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void findGroupByIDException() {
        //Arrange
        GroupRepository groupsRepository = new GroupsInMemoryRepository();

        //Act
        Throwable thrown = catchThrowable(() -> {
            groupsRepository.getByID(new GroupID(new Description("Familia")));
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that ID.");
    }


    /**
     * Test if GroupID is in the Repository
     */

    @Test
    @DisplayName("Validate if GroupID is the repository - True")
    void isGroupIDOnRepositoryTrue() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());

        //Act
        boolean groupIDexists = groupsRepository.isIDOnRepository(new GroupID(new Description("Familia")));

        //Assert
        assertTrue(groupIDexists);
    }

    @Test
    @DisplayName("Validate if GroupID is the repository - false")
    void isGroupIDOnRepositoryFalse() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());

        //Act
        boolean groupIDexists = groupsRepository.isIDOnRepository(new GroupID(new Description("Familia")));

        //Assert
        assertTrue(groupIDexists);
    }

    /**
     * Validate if an admin was added to the group
     */
    @Test
    @DisplayName("Validate if an admin was added to the group - true")
    void setAdminTrue() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"),
                new Email("1234@isep.pt"));

        String personID = "maria@isep.ipp.pt";

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Group group = groupsRepository.createGroup(new Description("Familia"), person.getID());
        group.addMember(new PersonID(new Email("maria@isep.ipp.pt")));


        //Act:

        boolean result = groupsRepository.setAdmin(group, personID);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate if an admin was added to the group - false")
    void setAdminFalse() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"),
                new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Group group = groupsRepository.createGroup(new Description("Familia"), person.getID());
        String personID = "11234@isep.ipp.pt";

        //Act:

        boolean result = groupsRepository.setAdmin(group, personID);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate if a member was added to the group - true")
    void addMember() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Group group = groupsRepository.createGroup(new Description("Familia"), person.getID());
        String personID = "12345@isep.pt";
        //Act
        boolean memberAddded = groupsRepository.addMember(group, personID);

        //Assert
        assertTrue(memberAddded);
    }

    @Test
    @DisplayName("Validate if a member was added to the group - false")
    void addMemberFalse() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        Group group = groupsRepository.createGroup(new Description("Familia"), person.getID());
        String personID = "1234@isep.pt";

        //Act
        boolean memberAddded = groupsRepository.addMember(group, personID);

        //Assert
        assertFalse(memberAddded);
    }
}