package switch2019.project.controllerLayer.controllersCli;

import org.springframework.stereotype.Controller;
import switch2019.project.DTO.AddMemberDTO;
import switch2019.project.DTO.AddedMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

@Controller
public class US003AddMemberToGroupController {

    private US003AddMemberToGroupService service;

    public US003AddMemberToGroupController(US003AddMemberToGroupService service) {
        this.service = service;
    }

    /***
     * US003
     * Add member to Group
     *
     * @param personEmail
     * @param groupDescription
     * @return AddedMemberDTO
     */
    public AddedMemberDTO addMemberToGroup(String personEmail, String groupDescription) {
        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail, groupDescription);
        return service.addMemberToGroup(addMemberDTO);
    }
}
