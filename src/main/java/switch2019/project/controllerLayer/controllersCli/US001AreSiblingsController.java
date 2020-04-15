package switch2019.project.controllerLayer.controllersCli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;
import switch2019.project.DTO.SerializationDTO.SiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
@Controller
public class US001AreSiblingsController {

    @Autowired
    private US001AreSiblingsService service;

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
