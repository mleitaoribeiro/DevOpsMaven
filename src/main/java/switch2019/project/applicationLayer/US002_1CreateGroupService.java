package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.ServiceDTO.CreateGroupDTO;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsInMemoryRepository;
import switch2019.project.infrastructure.repositories.PersonInMemoryRepository;

@Service
public class US002_1CreateGroupService {

    @Autowired
    private GroupsInMemoryRepository groupsRepository;
    @Autowired
    private PersonInMemoryRepository personRepository;

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

}