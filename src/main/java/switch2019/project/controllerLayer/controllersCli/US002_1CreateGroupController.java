package switch2019.project.controllerLayer.controllersCli;

import switch2019.project.DTO.createGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;


public class US002_1CreateGroupController {

    private switch2019.project.applicationLayer.US002_1CreateGroupService service;

    public US002_1CreateGroupController(switch2019.project.applicationLayer.US002_1CreateGroupService service) {
        this.service = service;
    }

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param groupDescription, personEmail
     * @return groupDTO
     */
    public GroupDTO createGroupAndBecomeAdmin(String groupDescription, String personEmail) {
       createGroupDTO createGroupDTO = GroupDTOAssembler.creationOfGroupDTO(groupDescription,personEmail);
       return service.createGroup(createGroupDTO);
    }
}

