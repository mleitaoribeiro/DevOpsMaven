package switch2019.project.controllers;

import switch2019.project.model.shared.PersonID;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US002_1CreateGroupAndBecomeAdminService;

public class US002_1CreateGroupAndBecomeAdminController {

    private US002_1CreateGroupAndBecomeAdminService service;

    public US002_1CreateGroupAndBecomeAdminController(US002_1CreateGroupAndBecomeAdminService service) {
        this.service = service;
    }

    public boolean createGroupAndBecomeAdmin(Description groupDescription, PersonID personID) {
        return service.createGroupAndBecomeAdmin(groupDescription, personID);
    }


}

