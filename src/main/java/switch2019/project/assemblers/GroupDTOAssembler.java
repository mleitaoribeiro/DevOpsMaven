package switch2019.project.assemblers;

import switch2019.project.DTO.AdminCreateGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;

public class GroupDTOAssembler {

    private GroupDTOAssembler() {
    }

    /**
     * method for creating the DTO for the Group
     *
     * @param groupID
     * @return groupDTO
     */
    public static GroupDTO createGroupDTO(GroupID groupID) {
        return new GroupDTO(groupID.getDescription());
    }


    public static AdminCreateGroupDTO createAdminCreateGroupDTO(String groupDescription, String personEmail) {
        return new AdminCreateGroupDTO(groupDescription, personEmail);
    }
}