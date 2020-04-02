package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.AreSiblingsDTO;
import switch2019.project.DTO.SiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;

@RestController
public class US001AreSiblingsControllerRest {

    @Autowired
    US001AreSiblingsService service;

    @GetMapping("/areSiblings")
    public ResponseEntity<SiblingsDTO> areSiblings(@RequestParam(value = "personOneEmail") String personEmail,
                                   @RequestParam(value = "personTwoEmail") String personEmail1){

        AreSiblingsDTO areSiblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(personEmail, personEmail1);

        SiblingsDTO result = PersonDTOAssembler.createSiblingsDTO(service.areSiblings(areSiblingsDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}