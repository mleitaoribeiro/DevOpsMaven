package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US002_1CreateGroupControllerRest {

    @Autowired
    US002_1CreateGroupService service;

    @PostMapping("/groups")
    public ResponseEntity<Object> createGroup(@RequestBody CreateGroupInfoDTO info) {

        CreateGroupDTO createGroupDTO = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        GroupDTO groupCreated = service.createGroup(createGroupDTO);

        Link selfLink = linkTo(methodOn(US002_1CreateGroupControllerRest.class)
                .getGroupByDescription(groupCreated.getGroupDescription()))
                .withSelfRel();

        groupCreated.add(selfLink);

        return new ResponseEntity<>(groupCreated, HttpStatus.CREATED);
    }

    @GetMapping(value = "groups/{groupDescription}")
    public ResponseEntity<Object> getGroupByDescription(@PathVariable final String groupDescription) {
        GroupDTO newGroupDTO = service.getGroupByDescription(groupDescription);

        Link members = linkTo(methodOn(US003AddMemberToGroupControllerRest.class)
                .getMembersByGroupDescription(newGroupDTO.getGroupDescription()))
                .withRel("Members");
        newGroupDTO.add(members);

        Link admins = linkTo(methodOn(US003AddMemberToGroupControllerRest.class)
                .getAdminsByGroupDescription(newGroupDTO.getGroupDescription()))
                .withRel("Admins");
        newGroupDTO.add(admins);

        return new ResponseEntity<>(newGroupDTO, HttpStatus.OK);
    }
}