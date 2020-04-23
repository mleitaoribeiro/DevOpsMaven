package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

@Service
public class US003AddMemberToGroupService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GroupRepository groupsRepository;

    /**
     * US003
     * Add Member To Group
     *
     * @param addMemberDTO
     * @return AddedMemberDTO
     */

    public AddedMemberDTO addMemberToGroup(AddMemberDTO addMemberDTO) {
        Person person = personRepository.findPersonByEmail(new Email(addMemberDTO.getPersonEmail()));
        Group group = groupsRepository.findGroupByDescription(new Description(addMemberDTO.getGroupDescription()));
        boolean wasMemberAdded = group.addMember(person);
        return GroupDTOAssembler.createAddedMemberDTO(wasMemberAdded, person, group);
    }
}