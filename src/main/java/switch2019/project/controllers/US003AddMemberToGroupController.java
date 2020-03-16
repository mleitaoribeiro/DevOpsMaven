package switch2019.project.controllers;

import switch2019.project.model.person.Address;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US003AddMemberToGroupService;

import java.time.LocalDateTime;

public class US003AddMemberToGroupController {

    public boolean addMemberToGroup(GroupsRepository groupsRepository, PersonRepository personRepository, US003AddMemberToGroupService service, PersonID personID,
                                    GroupID groupID) {
        return service.addMemberToGroup(groupID, personID, groupsRepository, personRepository);
    }
}
