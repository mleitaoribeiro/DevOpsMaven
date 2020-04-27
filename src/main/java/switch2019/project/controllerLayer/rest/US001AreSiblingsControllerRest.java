package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;

@RestController
public class US001AreSiblingsControllerRest {

    @Autowired
    US001AreSiblingsService service;

    @GetMapping("/persons/{personEmail1}/siblings/{personEmail2}")
    public ResponseEntity<SiblingsDTO> areSiblings(@PathVariable String personEmail1, @PathVariable String personEmail2) {

        AreSiblingsDTO areSiblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(personEmail1, personEmail2);

        SiblingsDTO result = PersonDTOAssembler.createSiblingsDTO(service.areSiblings(areSiblingsDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}