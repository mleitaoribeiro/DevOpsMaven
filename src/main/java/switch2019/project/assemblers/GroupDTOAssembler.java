package switch2019.project.assemblers;

import switch2019.project.DTO.AddMemberDTO;
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
     * @return groupDTO0
     */
    public static GroupDTO createGroupDTO(GroupID groupID) {
        return new GroupDTO(groupID.getDescription());
    }

    /**
     * method to create a DTO for group creating from primitives parameters
     * @param groupDescription
     * @param personEmail
     * @return AdminCreateGroupDTO
     */
    public static AdminCreateGroupDTO createAdminCreateGroupDTO(String groupDescription, String personEmail) {
        return new AdminCreateGroupDTO(groupDescription, personEmail);
    }

    /**
     * method to create a DTO to Add a Member from primitives parameters
     * @param personEmail
     * @param groupDescription
     * @return AddMemberDTO
     */

    public static AddMemberDTO createAddMemberDTO(String personEmail, String groupDescription) {
        return new AddMemberDTO(personEmail, groupDescription);
    }
}