package switch2019.project.controllerLayer.controllersCli;

import switch2019.project.DTO.AdminCreateGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupAndBecomeAdminService;
import switch2019.project.assemblers.GroupDTOAssembler;


public class US002_1CreateGroupAndBecomeAdminController {

    private US002_1CreateGroupAndBecomeAdminService service;

    public US002_1CreateGroupAndBecomeAdminController(US002_1CreateGroupAndBecomeAdminService service) {
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
       AdminCreateGroupDTO adminCreateGroupDTO = GroupDTOAssembler.createAdminCreateGroupDTO(groupDescription,personEmail);
       return service.createGroupAndBecomeAdmin(adminCreateGroupDTO);
    }
}

