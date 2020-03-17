package switch2019.project.services;

import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.repository.PersonRepository;

public class US001AreSiblingsService {

    /**
     * US001
     * As system manager, I want to know if two people are siblings
     *
     * @param emailPerson1
     * @param emailPerson2
     * @return true if two people are siblings
     */
    public boolean AreSiblings (PersonRepository repository, Email emailPerson1, Email emailPerson2) {

        Person person1 = repository.findPersonByEmail(emailPerson1);
        Person person2 = repository.findPersonByEmail(emailPerson2);

        return person1.isSibling(person2);
    }
}
