package switch2019.project.services;

import switch2019.project.model.person.Person;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.PersonRepository;

public class US001AreSiblingsService {

    private PersonRepository repository;

    public US001AreSiblingsService (PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * US001
     * As system manager, I want to know if two people are siblings
     *
     * @param personId1
     * @param personId2
     * @return true if two people are siblings
     */
    public boolean AreSiblings (PersonID personId1, PersonID personId2) {

        Person person1 = repository.findPersonByID(personId1);
        Person person2 = repository.findPersonByID(personId2);

        return person1.isSibling(person2);
    }
}
