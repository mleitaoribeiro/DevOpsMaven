package switch2019.project.assemblers;

import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;
import switch2019.project.DTO.deserializationDTO.MemberInfoDTO;
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

    public static GroupDTO createGroupDTO(GroupID groupID) {
        return new GroupDTO(groupID.getDescription());
    }

    public static CreateGroupDTO creationOfGroupDTO(String groupDescription, String personEmail) {
        return new CreateGroupDTO(groupDescription, personEmail);
    }

    public static CreateGroupDTO transformOfCreationOfGroupDTO(CreateGroupInfoDTO dto) {
        return new CreateGroupDTO(dto.getGroupDescription(), dto.getPersonEmail());
    }

    public static AddMemberDTO createAddMemberDTO(String personEmail, String groupDescription) {
        return new AddMemberDTO(personEmail, groupDescription);
    }

    public static AddMemberDTO transformIntoAddMemberDTO(MemberInfoDTO memberInfoDTO, String groupDescription) {
        return new AddMemberDTO(memberInfoDTO.getPersonEmail(), groupDescription);
    }

    public static AddedMemberDTO createAddedMemberDTO(boolean wasMemberAdded, Person person, Group group) {
        return new AddedMemberDTO(wasMemberAdded, person.getID().getEmail(), group.getID().getDescription());
    }
}