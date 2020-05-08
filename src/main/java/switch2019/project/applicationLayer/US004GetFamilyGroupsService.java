package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;

import java.util.*;

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

        //SUBSTITUIR PELO CODIGO DO ISFAMILY
        Set <Group> familyGroups = new LinkedHashSet<>();

        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Family Simpson"))));
        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Family Cardoso"))));
        familyGroups.add(groupsRepository.getByID(new GroupID(new Description("Family Azevedo"))));

        //DTO conversion
        Set<GroupDTO> familyGroupDTO = new LinkedHashSet<>();

        if (!familyGroups.isEmpty())
            for (Group family : familyGroups)
                familyGroupDTO.add(GroupDTOAssembler.createGroupDTO(family.getID()));

        return familyGroupDTO;
    }

}
