package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;

@Controller
public class US002_1CreateGroupController {

    @Autowired
    private switch2019.project.applicationLayer.US002_1CreateGroupService service;

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param groupDescription, personEmail
     * @return groupDTO
     */
    public GroupDTO createGroupAndBecomeAdmin(String groupDescription, String personEmail) {
       CreateGroupDTO createGroupDTO = GroupDTOAssembler.creationOfGroupDTO(groupDescription,personEmail);
       return service.createGroup(createGroupDTO);
    }
}
