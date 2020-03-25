package switch2019.project.assemblers;

import switch2019.project.DTO.GroupAndFirstAdminDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

public class GroupDTOAssembler {

    /**
     * method for creating the DTO for the Group
     * @param groupID
     * @return
     */
    public static GroupAndFirstAdminDTO createGroupAndFirstAdminDTO(GroupID groupID, PersonID adminID) {
        return new GroupAndFirstAdminDTO(groupID.getDescription(), adminID.getEmail());
    }

    /**
     * method for creating the DTO for the Group
     * @param groupDescription
     * @return
     */
    public static GroupDTO createGroupDescriptionDTO (Description groupDescription) {
        return new GroupDTO (groupDescription.getDescription());
    }
}
