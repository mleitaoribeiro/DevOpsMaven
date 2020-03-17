package switch2019.project.controllers;

import switch2019.project.model.person.Email;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US001AreSiblingsService;

public class US001AreSiblingsController {

    /**
     * US001
     * As system manager, I want to know if two people are siblings
     *
     * @param emailPerson1
     * @param emailPerson2
     * @return true if two people are siblings
     */
    public boolean AreSiblings (US001AreSiblingsService service, PersonRepository repository, Email emailPerson1, Email emailPerson2) {
        return service.AreSiblings(repository, emailPerson1, emailPerson2);
    }
}
