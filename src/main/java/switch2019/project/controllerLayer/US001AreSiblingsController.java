package switch2019.project.controllerLayer;

import switch2019.project.applicationLayer.US001AreSiblingsService;

public class US001AreSiblingsController {

    private US001AreSiblingsService service;

    public US001AreSiblingsController (US001AreSiblingsService service) {
        this.service = service;
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
        return service.areSiblings(emailPerson1, emailPerson2);
    }
}
