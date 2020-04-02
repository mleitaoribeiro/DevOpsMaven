package switch2019.project.applicationLayer;

import org.springframework.stereotype.Service;
import switch2019.project.DTO.createGroupDTO;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

@Service
public class US002_1CreateGroupService {

    private GroupsRepository groupsRepository;
    private PersonRepository personRepository;

    public US002_1CreateGroupService(GroupsRepository groupsRepository, PersonRepository personRepository) {
        this.groupsRepository = groupsRepository;
        this.personRepository = personRepository;
    }

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param createGroupDTO
     * @return groupDTO
     */
    public GroupDTO createGroup(createGroupDTO createGroupDTO) {

        Person admin = personRepository.findPersonByEmail(new Email(createGroupDTO.getPersonEmail()));
        Description groupDescription = new Description(createGroupDTO.getGroupDescription());

        Group groupCreated = groupsRepository.createGroup(groupDescription, admin);

        return GroupDTOAssembler.createGroupDTO(groupCreated.getID());
    }

}
