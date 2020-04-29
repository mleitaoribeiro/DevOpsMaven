package switch2019.project.assemblers;

import switch2019.project.DTO.deserializationDTO.AddMemberInfoDTO;
import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.serializationDTO.AddedMemberDTO;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.DTO.serviceDTO.AddMemberDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Person;
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
     *
     * @param groupDescription
     * @param personEmail
     * @return AdminCreateGroupDTO
     */
    public static CreateGroupDTO creationOfGroupDTO(String groupDescription, String personEmail) {
        return new CreateGroupDTO(groupDescription, personEmail);
    }

    /**
     * method to create a DTO from createGroupControllerREST to service
     *
     * @param dto
     * @return
     */
    public static CreateGroupDTO transformOfCreationOfGroupDTO(CreateGroupInfoDTO dto) {
        return new CreateGroupDTO(dto.getGroupDescription(), dto.getPersonEmail());
    }

    /**
     * method to create a DTO to Add a Member from primitives parameters
     *
     * @param personEmail
     * @param groupDescription
     * @return AddMemberDTO
     */

    public static AddMemberDTO createAddMemberDTO(String personEmail, String groupDescription) {
        return new AddMemberDTO(personEmail, groupDescription);
    }

    /**
     * This method transformes a AddMemberInfoDTO into a AddMemberDTO
     * @param addMemberInfoDTO
     * @return addMemberDTO
     */
    public static AddMemberDTO transformIntoAddMemberDTO(AddMemberInfoDTO addMemberInfoDTO, String groupDescription) {
        return new AddMemberDTO(addMemberInfoDTO.getPersonEmail(), groupDescription);
    }

    /**
     * method to create a DTO for a Group an added member
     *
     * @param wasMemberAdded
     * @param person
     * @param group
     * @return AddedMemberDTO
     */
    public static AddedMemberDTO createAddedMemberDTO(boolean wasMemberAdded, Person person, Group group) {
        return new AddedMemberDTO(wasMemberAdded, person.getID().getEmail(), group.getID().getDescription());
    }
}