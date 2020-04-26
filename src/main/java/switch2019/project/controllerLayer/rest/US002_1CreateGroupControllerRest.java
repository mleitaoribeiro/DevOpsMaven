package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.DTO.DeserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

@RestController
public class US002_1CreateGroupControllerRest {

    @Autowired
    US002_1CreateGroupService service;

    /**
     * US002.1
     * method that sends an HTTP responsive for a POST request to create a group and become Admin
     *
     * @param info
     * @return dto groupCreated and 201 CREATED status
     */

    @PostMapping("/createGroup")
    public ResponseEntity<Object> createGroup(@RequestBody CreateGroupInfoDTO info) {

        CreateGroupDTO createGroupDTO = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        GroupDTO groupCreated = service.createGroup(createGroupDTO);

        return new ResponseEntity<>(groupCreated, HttpStatus.CREATED);
    }

    @GetMapping(value = "groups/{groupDescription}")
    public ResponseEntity<Object> getGroupByDescription(@PathVariable final String groupDescription) {
        GroupDTO newGroupDTO = service.getGroupByDescription(groupDescription);
        return new ResponseEntity<>(newGroupDTO, HttpStatus.CREATED);
    }
}