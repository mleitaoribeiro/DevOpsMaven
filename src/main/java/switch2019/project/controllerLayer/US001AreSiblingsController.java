package switch2019.project.controllerLayer;

import switch2019.project.DTO.SiblingsDTO;
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
     * @param
     * @return true if two people are siblings
     */
    public boolean areSiblings(SiblingsDTO siblingsDTO) {
        return service.areSiblings(siblingsDTO);
    }
}
