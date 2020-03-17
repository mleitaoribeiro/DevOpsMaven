package switch2019.project.services;

import org.junit.jupiter.api.BeforeAll;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

//public class US003AddMemberToGroupServiceTest {
//
//
//    private static PersonRepository personRepository;
//    private static GroupsRepository groupsRepository;
//    private static US003AddMemberToGroupService service;
//
//    BeforeAll
//    static void universeSetUp () {
//
//        service = new US003AddMemberToGroupService(personRepository,groupsRepository);
//
//        personRepository = new PersonRepository();
//    //Add people to Repository
//        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
//                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
//        personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
//                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));
//        Person father2 = new Person("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
//                new Address("Rua X", "Portimão", "4520-266"), new Email("father2@isep.ipp.pt"));
//        Person mother = new Person("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
//                new Address("Rua X", "Porto", "4520-266"), new Email("mother@isep.ipp.pt"));
//        Person mother2 = new Person("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
//                new Address("Rua X", "Fafe", "4520-266"), new Email("mother2@isep.ipp.pt"));
//
//        groupsRepository =new GroupsRepository();
//
//        Group group = new Group("Família",person);
//
//        personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
//                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));
//
//
//}
