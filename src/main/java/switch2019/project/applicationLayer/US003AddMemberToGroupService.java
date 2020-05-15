package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.AddedMemberDTO;
import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serviceDTO.AddMemberDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class US003AddMemberToGroupService {

    @Autowired
    @Qualifier("PersonDbRepository")
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("GroupDbRepository")
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
        boolean wasMemberAdded = groupsRepository.addMember(group, person.getID().toString());

        if (wasMemberAdded)
            return GroupDTOAssembler.createAddedMemberDTO(true, person, group);
        else throw new ResourceAlreadyExistsException
                (GroupDTOAssembler.createAddedMemberDTO(false, person, group).toString());
    }

    public PersonIDDTO getPersonByEmail(String personEmail, String groupDescription){
        Group group = groupsRepository.findGroupByDescription(new Description(groupDescription));
        Person person = personRepository.findPersonByEmail(new Email(personEmail));
        PersonID personID = person.getID();

        if (group.isGroupMember(personID)) {
            return PersonDTOAssembler.createPersonIDDTO(personID);
        } throw new NoPermissionException("This person is not member of this group.");
    }

    public Set<PersonIDDTO> getMembersByGroupDescription(String groupDescription) {
        Group group = groupsRepository.findGroupByDescription(new Description(groupDescription));
        Set<PersonID> members = group.getMembers();

        Set<PersonIDDTO> membersDTO = new LinkedHashSet<>();
        for(PersonID person : members) {
            membersDTO.add(PersonDTOAssembler.createPersonIDDTO(person));
        } return membersDTO;
    }

    public Set<PersonIDDTO> getAdminsByGroupDescription(String groupDescription) {
        Group group = groupsRepository.findGroupByDescription(new Description(groupDescription));
        Set<PersonID> admins = group.getAdmins();

        Set<PersonIDDTO> adminsDTO = new LinkedHashSet<>();
        for(PersonID person : admins) {
            adminsDTO.add(PersonDTOAssembler.createPersonIDDTO(person));
        } return adminsDTO;
    }
}