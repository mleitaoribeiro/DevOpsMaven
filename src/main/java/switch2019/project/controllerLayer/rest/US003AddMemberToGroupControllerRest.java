package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.SerializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AddMemberDTO;
import switch2019.project.DTO.deserializationDTO.AddMemberInfoDTO;
import switch2019.project.DTO.serializationDTO.AddedMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US003AddMemberToGroupControllerRest {

    @Autowired
    US003AddMemberToGroupService service;

    /**
     * US003
     * method that sends an HTTP responsive for a POST request to add a Member to a group
     *
     * @param info
     * @return dto addedMember and 201 CREATED status
     */

    @PostMapping("/groups/{groupDescription}/members")
    public ResponseEntity<Object> addMemberToGroup(@RequestBody AddMemberInfoDTO info){

        AddMemberDTO addMemberDTO = GroupDTOAssembler.transformIntoAddMemberDTO(info);

        AddedMemberDTO addedMemberDTO = service.addMemberToGroup(addMemberDTO);

        Link selfLink = linkTo(methodOn(US003AddMemberToGroupControllerRest.class)
                .getPersonByEmail(addMemberDTO.getGroupDescription(), addMemberDTO.getPersonEmail()))
                .withSelfRel();

        addedMemberDTO.add(selfLink);

        return new ResponseEntity<>(addedMemberDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "groups/{groupDescription}/members/{personEmail}")
    public ResponseEntity<Object> getPersonByEmail
            (@PathVariable final String groupDescription, @PathVariable final String personEmail) {

        PersonIDDTO result = service.getPersonByEmail(personEmail, groupDescription);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{groupDescription}/members")
    public ResponseEntity<Object> getMembersByGroupDescription(@PathVariable final String groupDescription) {

        Set<PersonIDDTO> members = service.getMembersByGroupDescription(groupDescription);

        for(PersonIDDTO member : members) {
            Link selfLink = linkTo(methodOn(US003AddMemberToGroupControllerRest.class)
                    .getPersonByEmail(groupDescription, member.getPersonID()))
                    .withSelfRel();
            member.add(selfLink);
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{groupDescription}/admins")
    public ResponseEntity<Object> getAdminsByGroupDescription(@PathVariable final String groupDescription) {

        Set<PersonIDDTO> admins = service.getAdminsByGroupDescription(groupDescription);

        for(PersonIDDTO admin : admins) {
            Link selfLink = linkTo(methodOn(US003AddMemberToGroupControllerRest.class)
                    .getPersonByEmail(groupDescription, admin.getPersonID()))
                    .withSelfRel();
            admin.add(selfLink);
        }

        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}