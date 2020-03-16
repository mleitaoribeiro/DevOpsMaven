package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

import java.time.LocalDateTime;

public class US003AddMemberToGroupService {

    public boolean addMemberToGroup(GroupID groupID, PersonID personID, GroupsRepository groupsRepository, PersonRepository personRepository) {

        Person person = personRepository.findPersonByID(personID);
        Group group = groupsRepository.findGroupByID(groupID);
        return group.addMember(person);
    }
}
