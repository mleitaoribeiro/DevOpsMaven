package switch2019.project.assemblers;

import switch2019.project.DTO.GroupAndFirstAdminDTO;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

public class GroupDTOAssembler {

    /**
     * method for creating the DTO for the Group
     * @param groupID
     * @return
     */
    public static GroupAndFirstAdminDTO createGroupDTO(GroupID groupID, PersonID adminID) {
        return new GroupAndFirstAdminDTO(groupID.getDescription(), adminID.getEmail());
    }
}
