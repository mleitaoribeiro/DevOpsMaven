package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US004GetFamilyGroupsControllerRest {

    @Autowired
    US004GetFamilyGroupsService service;

    @GetMapping("/groups")
    public ResponseEntity <Object> getFamilyGroups(@RequestParam(value = "type") String type) {

        Set<GroupDTO> groups;

        if(type.equals(""))
            throw new IllegalArgumentException("The type can't be empty.");

        else if(!type.equals("family"))
            throw new ArgumentNotFoundException("No groups found with that type.");

        else {
            groups = service.getFamilyGroups();

            for (GroupDTO groupDTO : groups) {
                Link selfLink = linkTo(methodOn(US002_1CreateGroupControllerRest.class)
                        .getGroupByDescription(groupDTO.getGroupDescription()))
                        .withSelfRel();
                groupDTO.add(selfLink);
            }
        } return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
