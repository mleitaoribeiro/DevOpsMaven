package switch2019.project.controllerLayer.controllerCLI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.controllerLayer.controllersCli.US004GetFamilyGroupsController;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class US004GetFamilyGroupsControllerTest {

    private PersonRepository personRepository;
    private GroupsRepository groupsRepository;
    private US004GetFamilyGroupsService service;
    private US004GetFamilyGroupsController controller;

    private Group group1;
    private Group group2;
    private Group group3;
    private Group group4;
    private Group group5;

    @BeforeEach
    void universe() {
        personRepository = new PersonRepository();
        groupsRepository = new GroupsRepository();
        service = new US004GetFamilyGroupsService(groupsRepository);
        controller = new US004GetFamilyGroupsController(service);


        // First global group - All Family
        Person manuelaMOM = personRepository.createPerson("Manuela", new DateAndTime(1960, 10, 10), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("manuela@isep.pt"));
        Person carlosDAD = personRepository.createPerson("Carlos", new DateAndTime(1950, 12, 12), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("carlos@isep.pt"));
        Person oscar = personRepository.createPerson("Oscar", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("oscar@isep.pt"));
        Person marta = personRepository.createPerson("Marta", new DateAndTime(1995, 11, 5), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("marta@isep.pt"));
        Person joao = personRepository.createPerson("Joao", new DateAndTime(2000, 1, 12), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, carlosDAD, new Email("joao@isep.pt"));


        // Second global group - All Family 2
        Person homer = personRepository.createPerson("Homer", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("homer@isep.pt"));
        Person marge = personRepository.createPerson("Marge", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("marge@isep.pt"));
        Person bart = personRepository.createPerson("Bart", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("bart@isep.pt"));
        Person lisa = personRepository.createPerson("Lisa", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("lisa@isep.pt"));
        Person maggie = personRepository.createPerson("Maggie", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), marge, homer, new Email("maggie@isep.pt"));


        // Third global group - No Mom
        Person joaoDAD = personRepository.createPerson("Joao", new DateAndTime(1990, 12, 4), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("joao.dad@isep.pt"));
        Person mariaMOM = personRepository.createPerson("Maria", new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("maria.mom@isep.pt"));
        Person diana = personRepository.createPerson("Diana", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), mariaMOM, joaoDAD, new Email("diana@isep.pt"));
        Person elsa = personRepository.createPerson("Elsa", new DateAndTime(1990, 12, 4), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), mariaMOM, joaoDAD, new Email("elsa@isep.pt"));
        Person ines = personRepository.createPerson("Ines", new DateAndTime(1990, 12, 4), new Address("Paranhos"),
                new Address("Rua B", "Gaia", "4520-233"), manuelaMOM, joaoDAD, new Email("ines@isep.pt"));


        // Forth global group - Marta's group
        Person martaR = personRepository.createPerson("Marta Ribeiro", new DateAndTime(1990, 12, 04), new Address("Miragaia"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("marta.r@isep.pt"));
        Person martaC = personRepository.createPerson("Marta Cardoso", new DateAndTime(1990, 12, 04), new Address("Matosinhos"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("marta.c@isep.pt"));
        Person martaP = personRepository.createPerson("Marta Pinheiro", new DateAndTime(1990, 12, 04), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), new Email("marta.p@isep.pt"));


        // Fifth global group - Bojack's Gang ( No DAD )
        Person bojack = personRepository.createPerson("Bojack", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Porto", "4520-233"), new Email("bojack@isep.pt"));
        Person carolyn = personRepository.createPerson("Princess Carolyn", new DateAndTime(1990, 12, 4),
                new Address("Lisboa"), new Address("Rua B", "Porto", "4520-233"), new Email("carolyn@isep.pt"));
        Person todd = personRepository.createPerson("Todd Chavez", new DateAndTime(1990, 12, 4),
                new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"), carolyn, bojack, new Email("todd@isep.pt"));
        Person diane = personRepository.createPerson("Diane Nguyen", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Porto", "4520-233"), carolyn, bojack, new Email("diane@isep.pt"));

        group1 = new Group(new Description("Familia Santos"),manuelaMOM);
        group1.addMember(carlosDAD);
        group1.addMember(oscar);
        group1.addMember(marta);
        group1.addMember(joao);

        group2 = new Group(new Description("Familia Simpson"),homer);
        group2.addMember(marge);
        group2.addMember(maggie);
        group2.addMember(lisa);
        group2.addMember(bart);

        group3 = new Group(new Description("Familia Silva"),joaoDAD); //No Mom Added
        group3.addMember(diana);
        group3.addMember(elsa);
        group3.addMember(ines);

        group4 = new Group(new Description("Grupo Das Martas"),martaC); //No Family
        group4.addMember(martaP);
        group4.addMember(martaR);

        group5 = new Group(new Description("Familia Bojack"),carolyn); //No Dad Added
        group5.addMember(diane);
        group5.addMember(todd);
    }

    @Test
    @DisplayName("Get all the groups who are families in the repository")
    void getFamilyGroups() {

        //Arrange
        groupsRepository.addGroupToRepository(group1);
        groupsRepository.addGroupToRepository(group2);
        groupsRepository.addGroupToRepository(group3);
        groupsRepository.addGroupToRepository(group4);
        groupsRepository.addGroupToRepository(group5);

        Set <GroupDTO> expected = new HashSet<>();
        expected.add(new GroupDTO("FAMILIA SANTOS"));
        expected.add(new GroupDTO("FAMILIA SIMPSON"));

        //Act
        Set <GroupDTO> real = controller.getFamilyGroups();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Only families without a mother/a father")
    void notFamilyGroups() {

        //Arrange
        groupsRepository.addGroupToRepository(group3);
        groupsRepository.addGroupToRepository(group4);
        groupsRepository.addGroupToRepository(group5);

        Set <GroupDTO> expected = new HashSet<>();

        //Act
        Set <GroupDTO> real = controller.getFamilyGroups();

        //Assert
        assertEquals(expected, real);
    }
}