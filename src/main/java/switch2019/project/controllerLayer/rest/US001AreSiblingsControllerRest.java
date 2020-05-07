package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US001AreSiblingsControllerRest {

    @Autowired
    US001AreSiblingsService service;

    @GetMapping("/persons/{personEmail1:.+}/siblings/{personEmail2:.+}")
    public ResponseEntity<SiblingsDTO> areSiblings(@PathVariable String personEmail1, @PathVariable String personEmail2) {

        AreSiblingsDTO areSiblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(personEmail1, personEmail2);

        SiblingsDTO result = PersonDTOAssembler.createSiblingsDTO(service.areSiblings(areSiblingsDTO));

        Link siblingsListLink = linkTo((methodOn(US001AreSiblingsControllerRest.class)
                .getSiblings(personEmail1)))
                .withRel("Siblings List:");

        result.add(siblingsListLink);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{personEmail:.+}/siblings")
    public ResponseEntity<Object> getSiblings(@PathVariable final String personEmail) {

        Set<PersonIDDTO> siblingsList = service.getSiblings(personEmail);

        for (PersonIDDTO id : siblingsList) {
            Link personDetailLink = linkTo(methodOn(US001AreSiblingsControllerRest.class)
                    .getPersonByEmail(id.getPersonID()))
                    .withSelfRel();
            id.add(personDetailLink);
        }
        return new ResponseEntity<>(siblingsList, HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{personEmail:.+}")
    public ResponseEntity<Object> getPersonByEmail(@PathVariable final String personEmail) {
        PersonIDDTO personIDDTO = service.getPersonByEmail(personEmail);
        return new ResponseEntity<>(personIDDTO, HttpStatus.OK);
    }
}