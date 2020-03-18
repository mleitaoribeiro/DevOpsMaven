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

    private US003AddMemberToGroupService service;

    public US003AddMemberToGroupController(US003AddMemberToGroupService service ){
        this.service=service;
    }

    /***
     * Add member to Group
     * @param personID
     * @param groupID
     * @return
     */
    public boolean addMemberToGroup( PersonID personID,
                                    GroupID groupID) {
        return service.addMemberToGroup(personID, groupID);
    }
}
