package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.repositories.PersonRepository;

@Service
public class US001AreSiblingsService {

    @Autowired
    private PersonRepository repository;

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
