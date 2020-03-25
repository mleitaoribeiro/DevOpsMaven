package switch2019.project.applicationLayer;

import switch2019.project.DTO.GroupsDTO;
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

    public Set <GroupsDTO> getFamilyGroups (){

        Set <String> familyGroups = groupsRepository.returnOnlyFamilies();

        Set <GroupsDTO> familyGroupsDTO = new HashSet<>();

        if (!familyGroups.isEmpty()) {
            for (String family : familyGroups) {
                GroupsDTO familyDTO = new GroupsDTO(family);
                familyGroupsDTO.add(familyDTO);
            }
        }
       return familyGroupsDTO;
    }


}
