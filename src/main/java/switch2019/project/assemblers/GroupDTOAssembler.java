package switch2019.project.assemblers;

import switch2019.project.DTO.GroupDTO;
import switch2019.project.domain.domainEntities.shared.Description;

public class GroupDTOAssembler {

    private GroupDTOAssembler () {}

    /**
     * method for creating the DTO for the Group
     *
     * @param groupDescription
     * @return
     */
    public static GroupDTO createGroupDTO(Description groupDescription) {
        return new GroupDTO(groupDescription.getDescription());
    }
}
