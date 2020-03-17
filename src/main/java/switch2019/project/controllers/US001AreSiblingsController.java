package switch2019.project.controllers;

import switch2019.project.model.shared.PersonID;
import switch2019.project.services.US001AreSiblingsService;

public class US001AreSiblingsController {

    private US001AreSiblingsService service;

    public US001AreSiblingsController (US001AreSiblingsService service) {
        this.service = service;
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
        return service.AreSiblings(personId1, personId2);
    }
}
