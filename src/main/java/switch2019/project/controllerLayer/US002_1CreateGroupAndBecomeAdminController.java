package switch2019.project.controllerLayer;

import switch2019.project.DTO.AdminCreateGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.applicationLayer.US002_1CreateGroupAndBecomeAdminService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;

import java.util.Optional;

public class US002_1CreateGroupAndBecomeAdminController {

    private US002_1CreateGroupAndBecomeAdminService service;

    public US002_1CreateGroupAndBecomeAdminController(US002_1CreateGroupAndBecomeAdminService service) {
        this.service = service;
    }

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param adminCreateGroupDTO
     * @return true the group was created and person is now Admin
     */

    public Optional<GroupDTO> createGroupAndBecomeAdmin(AdminCreateGroupDTO adminCreateGroupDTO) {
        Optional<Group> optionalGroup = service.createGroupAndBecomeAdmin(adminCreateGroupDTO);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            GroupDTO groupDTO = GroupDTOAssembler.createGroupDTO((group.getID()));
            return Optional.of(groupDTO);
        } else return Optional.empty();
    }
}

