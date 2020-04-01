package switch2019.project.controllerLayer.controllersCli;

import switch2019.project.DTO.AreSiblingsDTO;
import switch2019.project.DTO.SiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;

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
    public SiblingsDTO areSiblings(String emailPersonOne, String emailPersonTwo) {
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonOne, emailPersonTwo);

        return PersonDTOAssembler.createSiblingsDTO(service.areSiblings(siblingsDTO));
    }
}
