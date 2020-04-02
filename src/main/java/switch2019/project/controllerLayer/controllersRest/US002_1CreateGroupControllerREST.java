package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.createGroupDTO;
import switch2019.project.DTO.createGroupInfoDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

@RestController
public class US002_1CreateGroupControllerREST {

    @Autowired
    US002_1CreateGroupService service;

    /**
     * method that sends an HTTTP responsive for a POST request
     *
     * @param info
     * @return dto groupCreated
     */
    @PostMapping("/createGroup")
    public ResponseEntity<Object> createGroup(@RequestBody createGroupInfoDTO info){

        createGroupDTO createGroupDTO = GroupDTOAssembler.transformOfCreationOfGroupDTO(info);

        GroupDTO groupCreated = service.createGroup(createGroupDTO);

        return new ResponseEntity<>(groupCreated, HttpStatus.CREATED);
    }

}
