package switch2019.project.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

@Component
public class Bootstrapper {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    GroupsRepository groupRepository;

    public void bootstrapping() {

        //Add people to Repository
        Person father = personRepository.createPerson("José", new DateAndTime(1995, 12, 13),
                new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"),
                new Email("father@isep.ipp.pt"));
        Person mother = personRepository.createPerson("Maria", new DateAndTime(1995, 12, 13),
                new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"),
                new Email("mother@isep.ipp.pt"));

        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13),
                new Address("Miragaia"), new Address("Rua das Flores", "Porto", "4000-189"),
                mother, father, new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José", new DateAndTime(1995, 12, 13),
                new Address("Miragaia"), new Address("Rua X", "Porto", "4520-266"),
                mother, father, new Email("father@isep.ipp.pt"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13),
                new Address("Miragaia"), new Address("Rua das Flores", "Porto", "4000-189"),
                mother, father, new Email("jo.cardoso@hotmail.com"));

        //Add groups to Repository
        groupRepository.createGroup(new Description("familia"),
                personRepository.findPersonByEmail(new Email("jose.cardoso@hotmail.com")));
        groupRepository.createGroup(new Description("canto"),
                personRepository.findPersonByEmail(new Email("father@isep.ipp.pt")));
    }
}
