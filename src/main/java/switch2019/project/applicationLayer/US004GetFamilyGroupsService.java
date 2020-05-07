package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class US004GetFamilyGroupsService {

    @Autowired
    private GroupRepository groupsRepository;

    /**
     * US004 -  As system manager I want to know which groups are families
     *
     * @return set of families
     */

    public Set <GroupDTO> getFamilyGroups () {

        Set <Group> familyGroups = Collections.emptySet();
        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Simpson"))));
        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Azevedo"))));
        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Cardoso"))));

        //DTO conversion
        Set<GroupDTO> familyGroupDTO = new LinkedHashSet<>();

        if (!familyGroups.isEmpty())
            for (Group family : familyGroups)
                familyGroupDTO.add(GroupDTOAssembler.createGroupDTO(family.getID()));

        return familyGroupDTO;
    }

}
