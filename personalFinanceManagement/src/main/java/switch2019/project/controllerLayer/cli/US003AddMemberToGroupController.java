package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.serializationDTO.AddedMemberDTO;
import switch2019.project.DTO.serviceDTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

@Controller
public class US003AddMemberToGroupController {

    @Autowired
    private US003AddMemberToGroupService service;

    /***
     * US003
     * Add member to Group
     *
     * @param personEmail for personEmail
     * @return AddedMemberDTO
     */
    public AddedMemberDTO addMemberToGroup(String personEmail, String groupDescription) {
        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail, groupDescription);
        return service.addMemberToGroup(addMemberDTO);
    }
}
