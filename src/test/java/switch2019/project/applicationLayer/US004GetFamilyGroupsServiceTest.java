package switch2019.project.applicationLayer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class US004GetFamilyGroupsServiceTest {
    private PersonRepository personRepository;
    private  GroupsRepository groupsRepository;
    private  US004GetFamilyGroupsService service;

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
        Person marge = new Person("Marge",new DateAndTime(1990, 12, 4), new Address("Springfield"),
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
        Person mariaMOM = new Person("Maria",new DateAndTime(1990, 12, 4), new Address("Springfield"),
                new Address("Rua B", "Porto", "4520-233"), new Email("novoMail5@isep.pt"));
        Person diana = new Person("Diana", new DateAndTime(1990, 12, 4), new Address("Porto"),
                new Address("Rua B", "Gaia", "4520-233"), mariaMOM, joaoDAD, new Email("email2@isep.pt"));
        Person elsa = new Person("Elsa",new DateAndTime(1990, 12, 4), new Address("Matosinhos"),
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
                new Address("Matosinhos"), new Address("Rua B", "Porto", "4520-233"),carolyn,bojack, new Email("new3@isep.pt"));
        Person diane = new Person("Diane Nguyen", new DateAndTime(1990, 12, 4), new Address("Espinho"),
                new Address("Rua B", "Porto", "4520-233"),carolyn,bojack, new Email("new4@isep.pt"));

        group1 = new Group(new Description("Familia Santos"),carlosDAD);
        group1.addMember(manuelaMOM);
        group1.addMember(oscar);
        group1.addMember(marta);
        group1.addMember(joao);

        group2 = new Group (new Description("Familia Simpson"),homer);
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

    /**
     * US004
     * As system manager I want to know which groups are families
     */
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
        Set <GroupDTO> real = service.getFamilyGroups();

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
        Set <GroupDTO> real = service.getFamilyGroups();

        //Assert
        assertEquals(expected, real);
    }
}