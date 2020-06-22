package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

@Service
public class US002_1CreateGroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private LedgerRepository ledgerRepository;

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

        if(!groupRepository.isIDOnRepository(new GroupID(groupDescription))){
            Group groupCreated = groupRepository.createGroup(groupDescription, admin.getID());
            ledgerRepository.createLedger(new GroupID(groupDescription));
            return GroupDTOAssembler.createGroupDTO(groupCreated.getID());

        } else throw new ResourceAlreadyExistsException("This group already exists.");
    }

    /**
     * method that finds a group by its description and returns a GroupDTO
     * @param groupDescription
     * @return
     */

    public GroupDTO getGroupByDescription(String groupDescription) {
     Group group = groupRepository.findGroupByDescription( new Description(groupDescription));
     return GroupDTOAssembler.createGroupDTO( group.getID());
    }
}