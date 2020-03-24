package switch2019.project.applicationLayer;

import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.infrastructure.repositories.PersonRepository;

public class US001AreSiblingsService {

    private PersonRepository repository;

    public US001AreSiblingsService (PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * US001
     * As system manager, I want to know if two people are siblings
     *
     * @param emailPerson1
     * @param emailPerson2
     * @return true if two people are siblings
     */
    public boolean areSiblings(String emailPerson1, String emailPerson2) {

        Person person1 = repository.findPersonByEmail(new Email(emailPerson1));
        Person person2 = repository.findPersonByEmail(new Email(emailPerson2));

        return person1.isSibling(person2);
    }
}