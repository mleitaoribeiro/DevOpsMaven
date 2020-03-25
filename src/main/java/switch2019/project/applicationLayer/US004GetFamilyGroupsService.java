package switch2019.project.applicationLayer;

import switch2019.project.DTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;

import java.util.HashSet;
import java.util.Set;

public class US004GetFamilyGroupsService {

    private GroupsRepository groupsRepository;

    public US004GetFamilyGroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    /**
     * US004 -  As system manager I want to know which groups are families
     *
     * @return set of families
     */

    public Set <GroupDTO> getFamilyGroups (){

        Set <String> familyGroups = groupsRepository.returnOnlyFamilies();

        Set <GroupDTO> familyGroupDTO = new HashSet<>();

        if (!familyGroups.isEmpty()) {
            for (String family : familyGroups) {
                GroupDTO familyDTO = GroupDTOAssembler.createGroupDescriptionDTO(new Description(family));
                familyGroupDTO.add(familyDTO);
            }
        }
       return familyGroupDTO;
    }
}
