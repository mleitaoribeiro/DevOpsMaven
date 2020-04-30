package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;

import java.util.HashSet;
import java.util.Set;

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

    public Set<PersonIDDTO> getSiblings(String personEmail) {
        Person person = repository.findPersonByEmail(new Email(personEmail));
        Set<PersonID> siblingsIDs = person.getSiblingsIDList();

        Set<PersonIDDTO> aux = new HashSet<>();
        for (PersonID id : siblingsIDs)
            aux.add(PersonDTOAssembler.createPersonIDDTO(id));
        return aux;
    }

    public PersonIDDTO getPersonByEmail(String personEmail) {
        Person person = repository.findPersonByEmail(new Email(personEmail));
        return PersonDTOAssembler.createPersonIDDTO(person.getID());
    }
}

