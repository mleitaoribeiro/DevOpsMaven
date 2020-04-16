package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.infrastructure.repositories.GroupsRepository;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class US004GetFamilyGroupsService {

    @Autowired
    private GroupsRepository groupsRepository;

    /**
     * US004 -  As system manager I want to know which groups are families
     *
     * @return set of families
     */

    public Set <GroupDTO> getFamilyGroups () {

        Set <Group> familyGroups = groupsRepository.returnOnlyFamilies();

        //DTO conversion
        Set<GroupDTO> familyGroupDTO = new LinkedHashSet<>();

        if (!familyGroups.isEmpty())
            for (Group family : familyGroups)
                familyGroupDTO.add(GroupDTOAssembler.createGroupDTO(family.getID()));

        return familyGroupDTO;
    }

}
