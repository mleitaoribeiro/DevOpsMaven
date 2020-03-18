package switch2019.project.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US004GetFamilyGroupsService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class US004GetFamilyGroupsControllerTest {

    private static PersonRepository personRepository;
    private static GroupsRepository groupsRepository;
    private static GroupsRepository groupsRepository2;
    private static US004GetFamilyGroupsService service;
    private static US004GetFamilyGroupsController controller;

    @BeforeAll
    static void universe() {
        personRepository = new PersonRepository();
        groupsRepository = new GroupsRepository();
        groupsRepository2 = new GroupsRepository();
        service = new US004GetFamilyGroupsService(groupsRepository);
        service = new US004GetFamilyGroupsService(groupsRepository2);
        controller = new US004GetFamilyGroupsController(service);

        // First global group - All Family
        Person manuelaMOM = new Person("Manuela", new DateAndTime(1960, 10, 10), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("1234@isep.pt"));
        Person carlosDAD = new Person("Carlos", new DateAndTime(1950, 12, 12), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("123@isep.pt"));
        Person oscar = new Person("Oscar", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("12@isep.pt"));
        Person marta = new Person("Marta", new DateAndTime(1995, 11, 5), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("1@isep.pt"));
        Person joao = new Person("Joao", new DateAndTime(2000, 1, 12), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("12345@isep.pt"));

        // Second global group - All Family 2
        Person homer = new Person("Homer", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail@isep.pt"));
        Person marge = new Person("Marge", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail2@isep.pt"));
        Person bart = new Person("Bart", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail3@isep.pt"));
        Person lisa = new Person("Lisa", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail4@isep.pt"));
        Person maggie = new Person("Maggie", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("novoMail5@isep.pt"));

        // Third global group - No Mom
        Person joaoDAD = new Person("Joao", new DateAndTime(1990, 12, 4), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("email@isep.pt"));
        Person mariaMOM = new Person("Maria", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail5@isep.pt"));
        Person diana = new Person("Diana", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), mariaMOM, joaoDAD, new Email("email2@isep.pt"));
        Person elsa = new Person("Elsa", new DateAndTime(1990, 12, 4), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), mariaMOM, joaoDAD, new Email("email3@isep.pt"));
        Person ines = new Person("Ines", new DateAndTime(1990, 12, 4), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, joaoDAD, new Email("email4@isep.pt"));

        // Forth global group - Marta's group
        Person martaR = new Person("Marta Ribeiro", new DateAndTime(1990, 12, 04), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail@isep.pt"));
        Person martaC = new Person("Marta Cardoso", new DateAndTime(1990, 12, 04), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail2@isep.pt"));
        Person martaP = new Person("Marta Pinheiro", new DateAndTime(1990, 12, 04), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("newMail3@isep.pt"));

        // Fifth global group - Bojack's Gang ( No DAD )
        Person bojack = new Person("Bojack", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Porto", "4520-233"), new Email("new@isep.pt"));
        Person carolyn = new Person("Princess Carolyn", new DateAndTime(1990, 12, 4),
                new Address("Lisboa"), new Address("Rua B", "Porto", "4520-233"), new Email("new2@isep.pt"));
        Person todd = new Person("Todd Chavez", new DateAndTime(1990, 12, 4),
                new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"), carolyn, bojack, new Email("new3@isep.pt"));
        Person diane = new Person("Diane Nguyen", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Porto", "4520-233"), carolyn, bojack, new Email("new4@isep.pt"));

        Group group1 = new Group(new Description("Familia Santos"));
        group1.addMember(carlosDAD);
        group1.addMember(manuelaMOM);
        group1.addMember(oscar);
        group1.addMember(marta);
        group1.addMember(joao);

        Group group2 = new Group(new Description("Familia Simpson"));
        group2.addMember(homer);
        group2.addMember(marge);
        group2.addMember(maggie);
        group2.addMember(lisa);
        group2.addMember(bart);

        Group group3 = new Group(new Description("Familia Silva")); //No Mom Added
        group3.addMember(joaoDAD);
        group3.addMember(diana);
        group3.addMember(elsa);
        group3.addMember(ines);

        Group group4 = new Group(new Description("Grupo Das Martas")); //No Family
        group4.addMember(martaC);
        group4.addMember(martaP);
        group4.addMember(martaR);

        Group group5 = new Group(new Description("Familia Bojack")); //No Dad Added
        group5.addMember(carolyn);
        group5.addMember(diane);
        group5.addMember(todd);

        groupsRepository.addGroupToRepository(group1);
        groupsRepository.addGroupToRepository(group2);
        groupsRepository.addGroupToRepository(group3);
        groupsRepository.addGroupToRepository(group5);


        groupsRepository2.addGroupToRepository(group3);
        groupsRepository2.addGroupToRepository(group5);
        groupsRepository2.addGroupToRepository(group4);



    }

    @Test
    @DisplayName("Get all the families in the repository")
    void getFamilyGroups() {
        //Arrange
        Set<Group> expected = new HashSet<>();
        expected.add(groupsRepository.findGroupByID(new GroupID(new Description("Familia Santos"))));
        expected.add(groupsRepository.findGroupByID(new GroupID(new Description("Familia Simpson"))));

        //Act
        controller.getFamilyGroups(groupsRepository);

        //Assert
        assertEquals(expected, controller.getFamilyGroups(groupsRepository));
    }

    @Test
    @DisplayName("Get all the families in the repository - no families")
    void getRepositoryWithoutFamilies() {
        //Arrange
        Set<Group> expected = new HashSet<>();
        //Act
        controller.getFamilyGroups(groupsRepository2);

        //Assert
        assertEquals(expected,controller.getFamilyGroups(groupsRepository2));
    }

}