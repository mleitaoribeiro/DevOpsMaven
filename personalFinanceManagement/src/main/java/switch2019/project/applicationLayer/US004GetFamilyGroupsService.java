package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class US004GetFamilyGroupsService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PersonRepository personRepository;

    public boolean isFamily(Group isFamilyGroup) {
        Set<PersonID> members = isFamilyGroup.getMembers();

        PersonID dadPerson = null;
        PersonID momPerson = null;

        for (PersonID personID1 : members) {
            for (PersonID personID2 : members) {
                Person person = personRepository.getByID(personID1);
                Person person2 = personRepository.getByID(personID2);

                if (!person.equals(person2)) {
                    if (person.isFather(person2.getID()))
                        dadPerson = person2.getID();
                    else if (person.isMother(person2.getID()))
                        momPerson = person2.getID();
                }
            }
        }
        if (dadPerson == null || momPerson == null) return false;

        for (PersonID personID1 : members) {
            Person person = personRepository.getByID(personID1);
            if (!person.getID().equals(dadPerson) && !person.getID().equals(momPerson) &&
                    (!person.isMother(momPerson) || !person.isFather(dadPerson)))
                return false;
        }
        return true;
    }

    public Set<GroupDTO> getFamilyGroups() {
        List<Group> allGroups = groupRepository.getAllGroups();
        Set<GroupDTO> familyGroups = new LinkedHashSet<>();

        for (Group group : allGroups) {
            if (isFamily(group)) {
                familyGroups.add(GroupDTOAssembler.createGroupDTO(group.getID()));
            }
        }

        return familyGroups;
    }
}
