package switch2019.project.applicationLayer;

import switch2019.project.DTO.AreSiblingsDTO;
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
     * @param
     * @return true if two people are siblings
     */
    public boolean areSiblings(AreSiblingsDTO siblingsDTO) {

        Person person1 = repository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonOne()));
        Person person2 = repository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonTwo()));

        return person1.isSibling(person2);
    }

}
