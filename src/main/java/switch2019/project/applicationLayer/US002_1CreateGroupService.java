package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

@Service
public class US002_1CreateGroupService {

    @Autowired
    private GroupRepository groupsRepository;
    @Autowired
    private PersonRepository personRepository;

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param createGroupDTO
     * @return groupDTO
     */
    public GroupDTO createGroup(CreateGroupDTO createGroupDTO) {

        Person admin = personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()));
        Description groupDescription = new Description(createGroupDTO.getGroupDescription());

        Group groupCreated = groupsRepository.createGroup(groupDescription, admin);

        return GroupDTOAssembler.createGroupDTO(groupCreated.getID());
    }

    /**
     * method that finds a group by its description and returns a GroupDTO
     * @param groupDescription
     * @return
     */

    public GroupDTO getGroupByDescription(String groupDescription) {
     Group group = groupsRepository.findGroupByDescription( new Description(groupDescription));
     return GroupDTOAssembler.createGroupDTO( group.getID());
    }
}