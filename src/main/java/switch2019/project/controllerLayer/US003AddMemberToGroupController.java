package switch2019.project.controllerLayer;

import switch2019.project.DTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;


public class US003AddMemberToGroupController {

    private US003AddMemberToGroupService service;

    public US003AddMemberToGroupController(US003AddMemberToGroupService service ){
        this.service=service;
    }

    /***
     * US003
     * Add member to Group
     *
     * @param personEmail, groupDescription
     * @return
     */
    public boolean addMemberToGroup(String personEmail, String groupDescription) {
        AddMemberDTO addMemberDTO = GroupDTOAssembler.createAddMemberDTO(personEmail, groupDescription);
        return service.addMemberToGroup(addMemberDTO);
    }
}
