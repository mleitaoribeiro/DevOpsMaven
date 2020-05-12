package switch2019.project.infrastructure;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.infrastructure.GroupsInMemoryRepository;
import switch2019.project.infrastructure.PersonInMemoryRepository;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

import java.util.*;

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
        assertEquals(expected,groupCreated);

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
        }
        catch (IllegalArgumentException ex) {
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
        assertEquals(expected,groupCreated);
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
        Group group1 = new Group(new Description("Amigos"),person.getID());
        Group group2 = new Group(new Description("Pokémons"),person.getID());
        GroupRepository groupList = new GroupsInMemoryRepository();

        //Act
        groupList.addGroupToRepository(group1);
        groupList.addGroupToRepository(group2);
        long result = groupList.repositorySize();

        //Assert
        assertEquals(2, result);
    }

    /**
     * Test if a group was added to the groupList
     */
    @Test
    @DisplayName("Test if the group added is not in the list")
    public void testGroupIsInList() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Switchieees"), person.getID());
        GroupRepository groupList = new GroupsInMemoryRepository();

        //Act
        boolean groupAdded = groupList.addGroupToRepository(group1);

        //Assert
        assertTrue(groupAdded);
    }

    @Test
    @DisplayName("Test if a null group is not added to the list")
    public void testAddGroupIsNull() {
        //Arrange
        GroupRepository groupList = new GroupsInMemoryRepository();

        //Act
        boolean groupAdded = groupList.addGroupToRepository(null);

        //Assert
        assertFalse(groupAdded);
    }

    @Test
    @DisplayName("Test if more than one group was added is in the list")
    public void testGroupIsInList_MoreThanOne() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("Switchieees"),person.getID());
        Group group2 = new Group(new Description("Clube da Costura"),person.getID());
        Group group3 = new Group(new Description("Clube dos Livros"),person.getID());

        GroupRepository groupList = new GroupsInMemoryRepository();

        //Act
        boolean group1added = groupList.addGroupToRepository(group1);
        boolean group2added = groupList.addGroupToRepository(group2);
        boolean group3added = groupList.addGroupToRepository(group3);

        //Assert
        assertTrue(group1added && group2added && group3added);
    }

    /**
     * US004 - Check which groups are family
     * If the list of groups consists exclusively in families.
     */
    @Test
    @DisplayName("Validate if only groups of families are being returned")
    public void ifGroupListIsOnlyFamilies_SuccessCase() {

        //Arrange
        // Global Groups List
        GroupRepository globalGroupsRepository = new GroupsInMemoryRepository();

        // 1 _________________________________________________________________________________________________________
        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", new DateAndTime(1960, 10, 10), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));
        Person carlosDAD = new Person("Carlos", new DateAndTime(1950, 12, 12), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("123@isep.pt"));
        Person oscar = new Person("Oscar", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM.getID(), carlosDAD.getID(), new Email("12@isep.pt"));
        Person marta = new Person("Marta", new DateAndTime(1995, 11, 5), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM.getID(), carlosDAD.getID(), new Email("1@isep.pt"));
        Person joao = new Person("Joao", new DateAndTime(2000, 1, 12), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM.getID(), carlosDAD.getID(), new Email("12345@isep.pt"));

        // Group
        Set<PersonID> familyMembersToAdd = new LinkedHashSet<>(Arrays.asList(oscar.getID(), marta.getID(), joao.getID(), carlosDAD.getID()));
        Group family = new Group(new Description("Family"),manuelaMOM.getID());
        family.addMember(manuelaMOM.getID());
        family.addMultipleMembers(familyMembersToAdd);
        globalGroupsRepository.addGroupToRepository(family);

        // 2 _________________________________________________________________________________________________________
        // Second global group - All Family 2
        Person homer = new Person("Homer", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail@isep.pt"));
        Person marge = new Person("Marge",new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail2@isep.pt"));
        Person bart = new Person("Bart", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge.getID(), homer.getID(), new Email("novoMail3@isep.pt"));
        Person lisa = new Person("Lisa", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge.getID(), homer.getID(), new Email("novoMail4@isep.pt"));
        Person maggie = new Person("Maggie", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge.getID(), homer.getID(), new Email("novoMail5@isep.pt"));

        // Group
        Set<PersonID> simpsonsMembersToAdd = new LinkedHashSet<>(Arrays.asList(marge.getID(), bart.getID(), lisa.getID(), maggie.getID()));
        Group simpsons = new Group(new Description("Simpsons"),homer.getID());
        simpsons.addMember(homer.getID());
        simpsons.addMultipleMembers(simpsonsMembersToAdd);
        globalGroupsRepository.addGroupToRepository(simpsons);

        // 3 _________________________________________________________________________________________________________
        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", new DateAndTime(1990, 12, 4), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("email@isep.pt"));
        Person diana = new Person("Diana", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD.getID(), new Email("email2@isep.pt"));
        Person elsa = new Person("Elsa",new DateAndTime(1990, 12, 4), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD.getID(), new Email("email3@isep.pt"));
        Person ines = new Person("Ines", new DateAndTime(1990, 12, 4), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), null, joaoDAD.getID(), new Email("email4@isep.pt"));

        // Group
        Set<PersonID> noMomMembersToAdd = new LinkedHashSet<>(Arrays.asList(diana.getID(), elsa.getID(), ines.getID()));
        Group familyWithNoMom = new Group(new Description("Family with no Mom"),joaoDAD.getID());
        familyWithNoMom.addMember(joaoDAD.getID());
        familyWithNoMom.addMultipleMembers(noMomMembersToAdd);
        globalGroupsRepository.addGroupToRepository(familyWithNoMom);

        // 4 _________________________________________________________________________________________________________
        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", new DateAndTime(1990, 12, 04), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail@isep.pt"));
        Person martaC = new Person("Marta Cardoso", new DateAndTime(1990, 12, 04), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail2@isep.pt"));
        Person martaP = new Person("Marta Pinheiro", new DateAndTime(1990, 12, 04), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail3@isep.pt"));

        // Group
        Set<PersonID> martasGroupMembersToAdd = new LinkedHashSet<>(Arrays.asList(martaC.getID(), martaP.getID()));
        Group martaGroup = new Group(new Description("Marta's group"),martaR.getID());
        martaGroup.addMember(martaR.getID());
        martaGroup.addMultipleMembers(martasGroupMembersToAdd);
        globalGroupsRepository.addGroupToRepository(martaGroup);

        // 5 _________________________________________________________________________________________________________
        // Fifth global group - Bojack's Gang ( no relationships )
        Person bojack = new Person("Bojack", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Porto", "4520-233"), new Email("new@isep.pt"));
        Person carolyn = new Person("Princess Carolyn", new DateAndTime(1990, 12, 4),
                new Address("Lisboa"), new Address("Rua B", "Porto", "4520-233"), new Email("new2@isep.pt"));
        Person todd = new Person("Todd Chavez", new DateAndTime(1990, 12, 4),
                new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"), new Email("new3@isep.pt"));
        Person diane = new Person("Diane Nguyen", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Porto", "4520-233"), new Email("new4@isep.pt"));

        // Group
        Set<PersonID> bojackGangMembersToAdd = new LinkedHashSet<>(Arrays.asList(carolyn.getID(), todd.getID(), diane.getID()));
        Group bojackGang = new Group(new Description("Bojack's Gang"),bojack.getID());
        bojackGang.addMember(bojack.getID());
        bojackGang.addMultipleMembers(bojackGangMembersToAdd);
        globalGroupsRepository.addGroupToRepository(bojackGang);

        //Act
        //Set<Group> realResult = globalGroupsRepository. //.returnOnlyFamilies();
        //HashSet<Group> expectedResult = new LinkedHashSet<>(Arrays.asList(family, simpsons));

        //Assert
        //assertEquals(expectedResult, realResult);
    }


  /*  @Test
    @DisplayName("getting group by its description")
    void getGroupByDescriptionTestException(){
        //Arrange:
        GroupRepository groupsRepository = new GroupsInMemoryRepository();
        PersonRepository personRepository = new PersonInMemoryRepository();

        personRepository.createPerson("Homer", new DateAndTime(1996, 3, 4),
                new Address("Porto"), new Address("Porto", "Rua de Santana", "4465-740"), new Email("1234@isep.pt"));


        groupsRepository.createGroup(new Description("BLA BLA"),personRepository.findPersonByEmail(new Email("1234@isep.pt")).getID());


        //Act:
        try {
            groupsRepository.findGroupByDescription(new Description("BLA BLA"));
        }

        //Assert:
        catch (IllegalArgumentException error){
            assertEquals("No group was found with the given description", error.getMessage());
        }
    }
*/
    @Test
    void findGroupByID() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository= new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());
        Group expected= new Group(new Description("Familia"),person.getID());

        //Act
        Group actual=groupsRepository.getByID(new GroupID(new Description("Familia")));

        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void findGroupByIDException() {
        //Arrange
        Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        GroupRepository groupsRepository= new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());
        Group expected= new Group(new Description("Familia"),person.getID());

        //Act102

        try{groupsRepository.getByID(new GroupID(new Description("Familia")));}



        catch(IllegalArgumentException error) {    //Assert
            assertEquals("No group found with that ID.", error);
        }
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

        GroupRepository groupsRepository= new GroupsInMemoryRepository();
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

        GroupRepository groupsRepository= new GroupsInMemoryRepository();
        groupsRepository.createGroup(new Description("Familia"), person.getID());

        //Act
        boolean groupIDexists = groupsRepository.isIDOnRepository(new GroupID(new Description("Familia")));

        //Assert
        assertTrue(groupIDexists);
    }

}